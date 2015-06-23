package org.bu.file.model;

import java.io.Serializable;

/**
 * 文件模型
 * 
 * @author jxs
 * 
 */

public class BuFile implements Serializable {

	private static final long serialVersionUID = 4179845654439671991L;

	private String fileName;

	private byte[] fileData;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

}
