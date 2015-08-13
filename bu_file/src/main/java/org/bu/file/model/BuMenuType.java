package org.bu.file.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "t_menu_type")
public class BuMenuType extends BuModel {
	private static final long serialVersionUID = -2429106506753852726L;
	private String tpName;// 类型名称
	private String tpDesc;// 类型描述
	private String menuId;// 标示文件夹
	private String basePath = "";// 基本路径
	
	public File getFilePath(){
		return new File(basePath,menuId);
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

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

}
