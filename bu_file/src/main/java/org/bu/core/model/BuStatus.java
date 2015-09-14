package org.bu.core.model;

public enum BuStatus {

	NORMAL(0), //
	DELETED(1);

	private int status = 0;

	private BuStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

}
