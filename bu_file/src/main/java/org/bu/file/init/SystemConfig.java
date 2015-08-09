package org.bu.file.init;

import javax.annotation.Resource;

import org.bu.file.dao.BuSysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@SuppressWarnings("rawtypes")
@Service("systemConfig")
public class SystemConfig implements ApplicationListener {
	protected static final Logger log = LoggerFactory.getLogger(SystemConfig.class);

	private static boolean run = false;

	@Resource(name = "dataImport")
	private Command dataImport = null;

	@Resource(name = "buSysService")
	private BuSysService buSysService;

	public void init() {
		if (needImportData()) {
			log.info("系统初次使用，初始化基本数据，" + SystemInfo.getVersion());
			this.dataImport.execute();
		} else {
			log.info("欢迎使用本系统，" + SystemInfo.getVersion());
		}

	}

	public boolean needImportData() {
		if (buSysService.hasData()) {
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
