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
@Table(name = "t_acrea")
public class BuArea {

	public static final String ROOT_PARENT = "0";

	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	@javax.persistence.OrderBy(value = "id")
	private java.lang.Integer id;

	@Column(name = "_code")
	private String code = "";// 地区代码
	@Column(name = "_name")
	private String name = "";// 地区名称

	@Column(name = "_parent")
	private String parent = "";// 树结构，地区归属

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

}
