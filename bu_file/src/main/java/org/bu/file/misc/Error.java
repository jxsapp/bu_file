package org.bu.file.misc;

public enum Error {

	SUCCESS(-1, "success"), //
	FAILURE(0, "failure"), //
	NO_PERMISSIONS(2, "No permissions"), //
	
	FILE_NOT_FOUND(1010, "File Not Found"), //
	PATH_NOT_DIRECTORY(1011, "File Not Directory"), //
	EXCEPTION_404(404, "Page Not Found"), //
	EXCEPTION_500(404, "Server error");//

	public int index = 0;
	public String desc = "";

	private Error(int index, String desc) {
		this.index = index;
		this.desc = desc;
	}

}
