package org.bu.file.scan;

import java.io.File;

import org.bu.file.model.BuMenuType;
import org.bu.file.model.BuStoreFile;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class BuFileScanor implements Runnable {
	private BuScanListener lister;
	private File root;
	private BuMenuType menutype;
	private ThreadPoolTaskExecutor taskExecutor;

	public BuFileScanor(BuScanListener _lis, BuMenuType menutype,File root, ThreadPoolTaskExecutor taskExecutor) {
		super();
		this.lister = _lis;
		this.menutype = menutype;
		this.root = root;
		this.taskExecutor = taskExecutor;
	}


	public void run() {
		System.out.println("在掃描中。。。"+this.toString());
		BuStoreFile storeFile = new BuStoreFile();
		storeFile = BuStoreFile.build(root);
		if (root.isDirectory()) {
			storeFile.setType(BuStoreFile.TYPE_DIR);
			storeFile.setSize(0L);
			lister.onScaned(storeFile,menutype);
			File[] children = root.listFiles();
			if (children != null) {
				for (File child : children) {
					BuFileScanor fileProcessor = new BuFileScanor(lister,menutype, child, taskExecutor);
					taskExecutor.execute(fileProcessor);
				}
			}
		} else {
			storeFile.setType(BuStoreFile.TYPE_FILE);
			storeFile.setSize(root.length());
			lister.onScaned(storeFile,menutype);
		}
	}
}
