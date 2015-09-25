package org.bu.file.scan;

import java.io.File;

import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuCliStore;

public class BuFileScanor implements Runnable {
	private BuScanListener lister;
	private File root;
	private BuCliPublish cliPublish;

	public BuFileScanor(BuScanListener _lis, BuCliPublish cliPublish, File root) {
		super();
		this.lister = _lis;
		this.cliPublish = cliPublish;
		this.root = root;
	}

	public void run() {
		new Scanler(lister, root, cliPublish).doScan();
	}

	private class Scanler {

		private BuScanListener lister;
		private File root;
		private BuCliPublish cliPublish;

		public Scanler(BuScanListener lister, File root, BuCliPublish cliPublish) {
			super();
			this.lister = lister;
			this.root = root;
			this.cliPublish = cliPublish;
		}

		private void doScan() {

			BuCliStore storeFile = new BuCliStore();
			storeFile = BuCliStore.build(root, cliPublish);
			if (root.isDirectory()) {
				storeFile.setType(BuCliStore.TYPE_DIR);
				storeFile.setSize(0L);
				lister.onScaned(storeFile, cliPublish);
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						new Scanler(lister, child, cliPublish).doScan();
					}
				}
			} else {
				long length = root.length();
				if (length == 0) {
					System.out.println("" + 0);
				}
				storeFile.setType(BuCliStore.TYPE_FILE);
				storeFile.setSize(length);
				lister.onScaned(storeFile, cliPublish);
			}
		}
	}

}
