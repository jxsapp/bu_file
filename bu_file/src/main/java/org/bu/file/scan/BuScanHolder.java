package org.bu.file.scan;

import java.io.File;

import org.bu.file.misc.SpringContextUtil;
import org.bu.file.model.BuMenuType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class BuScanHolder {

	private static ThreadPoolTaskExecutor taskExecutor;

	private static BuScanHolder instance;

	public static BuScanHolder getInstance() {
		if (null == instance) {
			instance = new BuScanHolder();
		}
		return instance;
	}

	private BuScanHolder() {
		super();
		taskExecutor = (ThreadPoolTaskExecutor) SpringContextUtil.getBean("taskExecutor");
	}

	public void scan(BuScanListener lister, BuMenuType menutype) {
		scan(lister, menutype, menutype.buildRootFile());
	}

	public void scan(BuScanListener lister, BuMenuType menutype, File rootFile) {
		if (rootFile.exists()) {
			BuFileScanor buFileScanor = new BuFileScanor(lister, menutype, rootFile);
			taskExecutor.execute(buFileScanor);
		}
	}

	public boolean hasQueue() {
		int size = taskExecutor.getThreadPoolExecutor().getQueue().size();
		return size > 0;
	}

}
