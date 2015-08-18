package org.bu.file.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

/**
 * 节点
 * 
 * @author jxs
 * 
 */
@Entity
@Table(name = "t_menu")
public class BuMenu extends BuModel {

	private static final long serialVersionUID = -8723011296503309114L;
	private String aliases = "";// 目录别名
	private String desc = "";// 目录描述

	@OneToOne
	private BuMenuType menuType;
	private long warnDiskSize = 0;// 磁盘预警大小
	private long delDiskSize = 0;

	private long warnChildSize = 0;// 子目录个数
	private long delChildSize = 0;// 子目录个数

	private int optionType = 0;//
	private String optionPath = "";// 操作路径

	private long optionChildTime = 0;// 超过限制时操作的时间

	// 时间取值格式：10000*年+1000*月 + 100*日+10小时+1*分钟
	// 一周以前：700

	public String getAliases() {
		return aliases;
	}

	public void setAliases(String aliases) {
		this.aliases = aliases;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public long getWarnDiskSize() {
		return warnDiskSize;
	}

	public void setWarnDiskSize(long warnDiskSize) {
		this.warnDiskSize = warnDiskSize;
	}

	public long getDelDiskSize() {
		return delDiskSize;
	}

	public void setDelDiskSize(long delDiskSize) {
		this.delDiskSize = delDiskSize;
	}

	public long getWarnChildSize() {
		return warnChildSize;
	}

	public void setWarnChildSize(long warnChildSize) {
		this.warnChildSize = warnChildSize;
	}

	public long getDelChildSize() {
		return delChildSize;
	}

	public void setDelChildSize(long delChildSize) {
		this.delChildSize = delChildSize;
	}

	public int getOptionType() {
		return optionType;
	}

	public void setOptionType(int optionType) {
		this.optionType = optionType;
	}

	public String getOptionPath() {
		return optionPath;
	}

	public void setOptionPath(String optionPath) {
		this.optionPath = optionPath;
	}

	public long getOptionChildTime() {
		return optionChildTime;
	}

	public void setOptionChildTime(long optionChildTime) {
		this.optionChildTime = optionChildTime;
	}

	public BuMenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(BuMenuType menuType) {
		this.menuType = menuType;
	}

}
