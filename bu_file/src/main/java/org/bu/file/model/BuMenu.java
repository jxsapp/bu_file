package org.bu.file.model;

import java.io.File;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;
import org.bu.file.dic.BuArea;

@Entity
@Table(name = "t_menu")
public class BuMenu extends BuModel {
	private static final long serialVersionUID = -2429106506753852726L;
	private static final String SCAN_SUFFIX = "_scan";// suffix
	private String tpName;// 类型名称
	private String tpDesc;// 类型描述
	private String menuId;// 标示文件夹
	private String basePath = "";// 基本路径

	public File buildRootFile() {
		return new File(basePath, menuId);
	}

	public String buildRootPath() {
		StringBuilder builder = new StringBuilder();
		builder.append(basePath);
		builder.append(menuId);
		builder.append(File.separator);
		return builder.toString();
	}

	public String buildScanRootPath() {
		StringBuilder builder = new StringBuilder();
		builder.append(basePath);
		builder.append(menuId);
		builder.append(SCAN_SUFFIX);
		builder.append(File.separator);
		return builder.toString();
	}

	public String buildRootPath(BuArea area) {
		return buildRootPath() + area.getCode() + File.separator;
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

	@Override
	public String toString() {
		return toJson();
	}

}
