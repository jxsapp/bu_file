package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 地区信息
 * 
 * @author jxs
 * 
 */
@Entity
@Table(name = "t_sys")
public class BuSys {
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	@javax.persistence.OrderBy(value = "id")
	private java.lang.Integer id;

	@Column(name = "_name")
	private String name = "";// 地区名称

	@Column(name = "_version")
	private String version = "";// 版本

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

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
