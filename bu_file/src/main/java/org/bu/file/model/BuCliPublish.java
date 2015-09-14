package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_cli_publish")
public class BuCliPublish extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "pub_path", length = 2000)
	private String path;//

	@Column(name = "pub_desc")
	private String desc;

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
