package org.bu.file.misc;

import org.bu.core.pact.ErrorCode;

public enum Error {

	SUCCESS(ErrorCode.SUCCESS, "success"), //
	FAILURE(ErrorCode.FAILED, "failure"), //
	NO_PERMISSIONS(ErrorCode.UNAUTHENTICATED, "No permissions"), //

	FILE_NOT_FOUND(ErrorCode.FILE_NOT_FOUND, "File Not Found"), //
	PATH_NOT_DIRECTORY(ErrorCode.FILE_NOT_DIRECTORY, "File Not Directory"), //
	EXCEPTION_404(ErrorCode.PAGE_NOT_FOUND, "Page Not Found"), //
	EXCEPTION_500(ErrorCode.SERVER_ERROR, "Server error");//

	public int index = 0;
	public String desc = "";

	private Error(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

}
