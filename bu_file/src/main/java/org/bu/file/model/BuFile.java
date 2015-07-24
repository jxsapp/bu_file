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

	private String fileKey;

	private byte[] fileData;

	public BuFile(String type, String path) {
		super();
		this.fileKey = getKey(type, path);
	}

	public static String getKey(String type, String path) {
		return type + path;
	}

	public String getFileKey() {
		return fileKey;
	}

	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

}
