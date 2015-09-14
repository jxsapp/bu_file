package org.bu.file.scan;

import org.bu.file.model.BuStoreFile;

public class ScanToDBHolder {

	/** 发送数据到服务器主要方法 */
	public static void saveStoreFile(final BuStoreFile dataPack) {
		ScanToDBCommunication.getInstance().addRequestQueue(dataPack);
	}

	public static boolean hasPersistencIng() {
		return ScanToDBCommunication.getInstance().isNotEmptyQueue();
	}

}
