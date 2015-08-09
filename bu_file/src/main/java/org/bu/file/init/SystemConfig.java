package org.bu.file.init;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.EntityManagerHolder;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSource;
import org.springframework.security.intercept.web.FilterInvocationDefinitionSourceEditor;
import org.springframework.security.intercept.web.FilterSecurityInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.jxs.sys.core.global.command.Command;
import com.jxs.sys.core.security.config.SecurityConfig;
import com.jxs.sys.core.security.dictionary.model.Type_Action;
import com.jxs.sys.core.security.dictionary.model.Type_Method;
import com.jxs.sys.core.security.dictionary.model.Type_Namespace;
import com.jxs.sys.core.security.dictionary.service.Type_ActionManager;
import com.jxs.sys.core.security.dictionary.service.Type_MethodManager;
import com.jxs.sys.core.security.dictionary.service.Type_NamespaceManager;
import com.jxs.sys.core.security.manager.UserManager;

@SuppressWarnings("unchecked")
@Service("systemConfig")
public class SystemConfig implements ApplicationListener {
	protected static final Logger log = LoggerFactory.getLogger(SystemConfig.class);

	private static boolean run = false;

	@Resource(name = "userManager")
	private UserManager userManager = null;

	@Resource(name = "dataImport")
	private Command dataImport = null;

	@Autowired
	private EntityManagerFactory emf;

	public void init() {
		if (needImportData()) {
			log.info("系统初次使用，初始化基本数据，" + SystemInfo.getVersion());
			this.dataImport.execute();
		} else {
			log.info("欢迎使用本系统，" + SystemInfo.getVersion());
		}

		if (SecurityConfig.isSecurity()) {
			log.info("当前系统启用安全子系统，初始化安全配置信息......");

			TransactionSynchronizationManager.bindResource(this.emf, new EntityManagerHolder(this.emf.createEntityManager()));

			initSecurityConfigInfo();

			EntityManagerHolder emHolder = (EntityManagerHolder) TransactionSynchronizationManager.unbindResource(this.emf);
			EntityManagerFactoryUtils.closeEntityManager(emHolder.getEntityManager());
		} else {
			log.info("当前系统禁用安全子系统");
		}
	}

	@SuppressWarnings("deprecation")
	public void initSecurityConfigInfo() {
		log.info("开始初始化权限子系统...");
		StringBuffer privilegeDefinition = new StringBuffer();
		privilegeDefinition.append("CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON\n");
		privilegeDefinition.append("PATTERN_TYPE_APACHE_ANT\n");
		privilegeDefinition.append("/**/backstage/admin/**=ROLE_MANAGER,ROLE_ADMIN\n");

		for (Type_Namespace namespace : this.type_NamespaceManager.queryAll().getResultlist()) {
			for (Type_Action action : type_ActionManager.queryAll(namespace)) {
				for (Type_Method method : type_MethodManager.queryAll(action)) {
					String namespaceLowerCase = namespace.getNamespace().toLowerCase();
					String namespaceUpperCase = namespace.getNamespace().toUpperCase();
					String actionLowerCase = action.getAction().toLowerCase();
					String actionUpperCase = action.getAction().toUpperCase();
					String methodLowerCase = method.getMethod().toLowerCase();
					String methodUpperCase = method.getMethod().toUpperCase();
					privilegeDefinition.append("/" + namespaceLowerCase + "/**/" + actionLowerCase + "/" + methodLowerCase + "*=ROLE_MANAGER_" + namespaceUpperCase + "_" + actionUpperCase + "_"
							+ methodUpperCase + ",ROLE_ADMIN\n");
				}
			}
		}
		// privilegeDefinition.append("/**/*.action*=ROLE_ADMIN\n");

		FilterInvocationDefinitionSourceEditor e = new FilterInvocationDefinitionSourceEditor();
		e.setAsText(privilegeDefinition.toString());

		this.securityInterceptor.setObjectDefinitionSource((FilterInvocationDefinitionSource) e.getValue());
		log.info("完成初始化权限子系统...");
	}

	public void closeSecurity() {
		log.info("开始关闭权限子系统...");
		StringBuffer privilegeDefinition = new StringBuffer();
		privilegeDefinition.append("CONVERT_URL_TO_LOWERCASE_BEFORE_COMPARISON\n");
		privilegeDefinition.append("PATTERN_TYPE_APACHE_ANT\n");
		FilterInvocationDefinitionSourceEditor e = new FilterInvocationDefinitionSourceEditor();
		e.setAsText(privilegeDefinition.toString());

		this.securityInterceptor.setObjectDefinitionSource((FilterInvocationDefinitionSource) e.getValue());
		log.info("关闭权限子系统结束...");
	}

	public boolean needImportData() {
		if (this.userManager.queryAll().getResultlist().size() == 0) {
			log.info("数据库里没有初始化数据......");
			return true;
		}
		log.info("数据库里有初始化数据......");

		return false;
	}

	public void onApplicationEvent(ApplicationEvent event) {
		if (!(run)) {
			init();
			run = true;
		}
	}
}
