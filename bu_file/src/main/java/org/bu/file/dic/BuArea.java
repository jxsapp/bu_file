package org.bu.file.dic;

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
@Table(name = "t_acrea")
public class BuArea extends BuModel {

	private static final long serialVersionUID = 1272899045014748440L;

	public static final String ROOT_PARENT = "0";

	@Column(name = "_code")
	private String code = "";// 地区代码
	@Column(name = "_name")
	private String name = "";// 地区名称

	@Column(name = "_parent")
	private String parent = "";// 树结构，地区归属

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
