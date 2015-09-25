package org.bu.core.model;

public enum BuStatus {

	//
	INVALID(-1), //
	NORMAL(0), //
	DELETED(1), //
	CANCELED(2), ;

	private int status = 0;

	private BuStatus(int status) {
		this.status = status;
	}

	public int getStatus() {
		return status;
	}

	public boolean isInvalid() {
		return status == INVALID.status;
	}

	public static BuStatus buildStatus(int status) {
		BuStatus buStatus = INVALID;
		if (status == NORMAL.status) {
			buStatus = NORMAL;
		} else if (status == DELETED.status) {
			buStatus = DELETED;
		} else if (status == CANCELED.status) {
			buStatus = CANCELED;
		}
		return buStatus;
	}

}
