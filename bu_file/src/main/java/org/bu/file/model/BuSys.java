package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

/**
 * 地区信息
 * 
 * @author jxs
 * 
 */
@Entity
@Table(name = "t_sys")
public class BuSys extends BuModel {

	private static final long serialVersionUID = 5575971014121800549L;

	@Column(name = "_name")
	private String name = "";// 地区名称

	@Column(name = "_version")
	private String version = "";// 版本

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
