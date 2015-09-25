package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.bu.core.model.BuModel;
import org.bu.file.init.MgrServerData;

@Entity
@Table(name = "t_mgr_server")
public class BuMgrServer extends BuModel implements MgrServerData {
	private static final long serialVersionUID = 2110330618846561394L;

	public static final int DATA_CENTER_FALSE = 0;
	public static final int DATA_CENTER_YES = 1;

	@Column(name = "server_name")
	private String serverName;// 机器名称

	@Column(name = "server_desc")
	private String serverDesc;// 机器描述

	@Column(name = "server_ip")
	private String serverIp;// 机器IP

	@Column(name = "server_port")
	private int serverPort = SERVER_PORT;// PORT

	@Column(name = "root_path")
	private String rootPath;// 跟路径

	@Column(name = "user_name")
	private String username = SERVER_USERNAME;// 用户名

	@Column(name = "user_pwd")
	private String password = SERVER_PWD;// 访问密码

	@Column(name = "is_center", nullable = true)
	private int dataCenter = 0;

	public boolean validateParams() {
		boolean rst = true;
		rst = !StringUtils.isEmpty(serverIp) //
				&& !StringUtils.isEmpty(serverName)//
				&& !StringUtils.isEmpty(rootPath);
		return rst;
	}

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

	public boolean isDataCenter() {
		return dataCenter == DATA_CENTER_YES;
	}

	public void setDataCenter(int dataCenter) {
		this.dataCenter = dataCenter;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getDataCenter() {
		return dataCenter;
	}

}
