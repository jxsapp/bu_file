package org.bu.file.scan;

import java.io.File;

import org.bu.file.model.BuMenu;
import org.bu.file.model.BuStoreFile;

public class BuFileScanor implements Runnable {
	private BuScanListener lister;
	private File root;
	private BuMenu menutype;

	public BuFileScanor(BuScanListener _lis, BuMenu menutype, File root) {
		super();
		this.lister = _lis;
		this.menutype = menutype;
		this.root = root;
	}

	public void run() {
		new Scanler(lister, root, menutype).doScan();
	}

	private class Scanler {

		private BuScanListener lister;
		private File root;
		private BuMenu menutype;

		public Scanler(BuScanListener lister, File root, BuMenu menutype) {
			super();
			this.lister = lister;
			this.root = root;
			this.menutype = menutype;
		}

		private void doScan() {

			BuStoreFile storeFile = new BuStoreFile();
			storeFile = BuStoreFile.build(root, menutype);
			if (root.isDirectory()) {
				storeFile.setType(BuStoreFile.TYPE_DIR);
				storeFile.setSize(0L);
				lister.onScaned(storeFile, menutype);
				File[] children = root.listFiles();
				if (children != null) {
					for (File child : children) {
						new Scanler(lister, child, menutype).doScan();
					}
				}
			} else {
				storeFile.setType(BuStoreFile.TYPE_FILE);
				storeFile.setSize(root.length());
				lister.onScaned(storeFile, menutype);
			}
		}
	}

}
