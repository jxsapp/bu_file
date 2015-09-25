package org.bu.file.model;

import java.io.File;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;
import org.bu.file.dic.BuArea;

@Entity
@Table(name = "t_cli_publish")
public class BuCliPublish extends BuModel {

	private static final String SCAN_SUFFIX = "_scan";// suffix

	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "pub_path", length = 2000)
	private String path;//

	@Column(name = "pub_desc")
	private String desc;

	public File buildRootFile() {
		return new File(path);
	}

	public String buildRootPath() {
		return path;
	}

	public String buildScanRootPath() {
		StringBuilder builder = new StringBuilder();
		builder.append(path);
		builder.append(SCAN_SUFFIX);
		builder.append(File.separator);
		return builder.toString();
	}

	public String buildRootPath(BuArea area) {
		return buildRootPath() + File.separator + area.getCode() + File.separator;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
