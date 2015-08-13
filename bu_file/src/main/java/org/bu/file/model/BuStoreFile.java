package org.bu.file.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 文件模型
 * 
 * @author jxs
 */
@Entity
@Table(name = "t_store_file")
public class BuStoreFile extends BuModel {
	private static final long serialVersionUID = 4179845654439671991L;
	
	
	public static final String TYPE_DIR="d";
	public static final String TYPE_FILE="f";
	
	
	private String prefix;// 类型
	private String areaEncode = "";// 地区编码
	private String path;// 相对路径
	private String type="";//文件类型
	private long size = 0;// 文件大小
	private long lastTime = 0;// 最后修改时间
	
	public static BuStoreFile build(File root){
		BuStoreFile storeFile = new BuStoreFile();
		storeFile.areaEncode = "";
		return storeFile;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getAreaEncode() {
		return areaEncode;
	}

	public void setAreaEncode(String areaEncode) {
		this.areaEncode = areaEncode;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public long getLastTime() {
		return lastTime;
	}

	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}