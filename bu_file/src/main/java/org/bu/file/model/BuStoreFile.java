package org.bu.file.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.misc.BuGsonHolder;
import org.bu.core.model.BuModel;
import org.bu.file.misc.SecretHolder;

/**
 * 文件模型
 * 
 * @author jxs
 */
@Entity
@Table(name = "t_store_file")
public class BuStoreFile extends BuModel {
	private static final long serialVersionUID = 4179845654439671991L;

	public static final String TYPE_DIR = "d";
	public static final String TYPE_FILE = "f";

	private String prefix;// 类型
	private String areaEncode = "";// 地区编码
	@Column(name = "path", length = 2000)
	private String path;// 相对路径
	private String type = "";// 文件类型
	private long size = 0;// 文件大小
	private long lastTime = 0;// 最后修改时间
	private String secret = "";// 加密标识符

	public static BuStoreFile build(File root, BuMenu type) {
		BuStoreFile storeFile = new BuStoreFile();//
		storeFile.setPrefix(type.getMenuId());

		String abRoot = root.getAbsolutePath();
		String path = abRoot.replaceAll(type.buildRootPath(), "");
		storeFile.setPath(path);// 相对路径
		if (path.indexOf(File.separator) > 0) {
			storeFile.setAreaEncode(path.substring(0, path.indexOf(File.separator)));
		}
		storeFile.setLastTime(root.lastModified());// 最后修改时间
		storeFile.setSize(root.length());// 文件大小
		storeFile.setSecret(storeFile.builderSecret());
		return storeFile;
	}

	public boolean isDir() {
		return TYPE_DIR.equals(type);
	}

	public String builderSecret() {
		return SecretHolder.getSecret(Long.toString(lastTime), Long.toString(size), path);
	}

	public String toJson() {
		return BuGsonHolder.getJson(this, true);
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

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}