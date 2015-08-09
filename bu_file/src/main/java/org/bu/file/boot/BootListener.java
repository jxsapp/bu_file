package org.bu.file.boot;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.bu.file.init.SystemConfig;
import org.bu.file.init.SystemInfo;
import org.bu.file.misc.SpringBeanHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootListener implements ServletContextListener {
	protected static final Logger log = LoggerFactory.getLogger(BootListener.class);

	private static SystemConfig systemConfig = null;

	public void contextInitialized(ServletContextEvent sce) {
		log.info("进入系统初始化监听器.....");
		systemConfig = (SystemConfig) SpringBeanHelper.getBean("systemConfig", sce.getServletContext());
		systemConfig.init();
		log.info("系统导入完成，进入系统.....");
		log.info("启动系统，" + SystemInfo.getVersion());
	}

	public void contextDestroyed(ServletContextEvent sce) {
	}
}
