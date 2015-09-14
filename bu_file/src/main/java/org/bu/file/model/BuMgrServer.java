package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_mgr_server")
public class BuMgrServer extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "server_name")
	private String serverName;// 机器名称

	@Column(name = "server_ip")
	private String serverIp;// 机器IP

	@Column(name = "server_desc")
	private String serverDesc;// 机器描述

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getServerIp() {
		return serverIp;
	}

	public void setServerIp(String serverIp) {
		this.serverIp = serverIp;
	}

	public String getServerDesc() {
		return serverDesc;
	}

	public void setServerDesc(String serverDesc) {
		this.serverDesc = serverDesc;
	}

}
