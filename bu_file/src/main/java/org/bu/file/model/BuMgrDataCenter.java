package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_data_center")
public class BuMgrDataCenter extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "center_name")
	private String centerName;// 名称

	@Column(name = "center_ip")
	private String centerIp;// IP

	@Column(name = "center_desc")
	private String centerDesc;// 描述

	@Column(name = "center_path")
	private String centerPath;// 数据中心基本路径

	public BuMgrServer buildMgrServer() {
		BuMgrServer mgrServer = new BuMgrServer();
		mgrServer.setServerName(centerName);
		mgrServer.setServerDesc(centerDesc);
		mgrServer.setServerIp(centerIp);
		mgrServer.setDataCenter(BuMgrServer.DATA_CENTER_YES);
		return mgrServer;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getCenterIp() {
		return centerIp;
	}

	public void setCenterIp(String centerIp) {
		this.centerIp = centerIp;
	}

	public String getCenterDesc() {
		return centerDesc;
	}

	public void setCenterDesc(String centerDesc) {
		this.centerDesc = centerDesc;
	}

	public String getCenterPath() {
		return centerPath;
	}

	public void setCenterPath(String centerPath) {
		this.centerPath = centerPath;
	}

}
