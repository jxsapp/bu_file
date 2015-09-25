package org.bu.file.scan;

import org.bu.file.model.BuCliStore;

public class ScanToDBCommunication {

	private ScanToDBWorker messageWorker;
	private static ScanToDBCommunication instance = null;

	private ScanToDBCommunication() {
		messageWorker = new ScanToDBWorker();
		messageWorker.start();
	}

	public static ScanToDBCommunication getInstance() {
		if (instance == null)
			instance = new ScanToDBCommunication();
		return instance;
	}

	public void addRequestQueue(BuCliStore dataPack) {
		messageWorker.addRequestQueue(dataPack);
	}

	public boolean isNotEmptyQueue() {
		return messageWorker.isNotEmptyQueue();
	}
}
