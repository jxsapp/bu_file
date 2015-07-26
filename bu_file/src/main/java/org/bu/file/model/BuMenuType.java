package org.bu.file.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_menu_type")
public class BuMenuType {
	@javax.persistence.Id
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	@javax.persistence.OrderBy(value = "id")
	private java.lang.Integer id;

	private String tpName;// 类型名称
	private String tpDesc;// 类型描述
	private String basePath;
	private String menuId;// 标示文件夹

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public String getTpName() {
		return tpName;
	}

	public void setTpName(String tpName) {
		this.tpName = tpName;
	}

	public String getTpDesc() {
		return tpDesc;
	}

	public void setTpDesc(String tpDesc) {
		this.tpDesc = tpDesc;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
