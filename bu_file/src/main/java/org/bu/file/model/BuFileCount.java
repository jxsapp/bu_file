package org.bu.file.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_file_count")
public class BuFileCount extends BuModel {

	private static final long serialVersionUID = 5086579006775487410L;
	private String areaCode = "";
	private String menuTypeCode = "";
	private int count = 0;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getMenuTypeCode() {
		return menuTypeCode;
	}

	public void setMenuTypeCode(String menuTypeCode) {
		this.menuTypeCode = menuTypeCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}