package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.bu.core.model.BuModel;
import org.bu.file.init.MgrServerData;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;

@Entity
@Table(name = "t_mgr_server")
@JsonIgnoreProperties(value = { "username", "password", "serverPort" })
public class BuMgrServer extends BuModel implements MgrServerData {
	private static final long serialVersionUID = 2110330618846561394L;

	public static final int DATA_CENTER_FALSE = 0;
	public static final int DATA_CENTER_YES = 1;

	@Column(name = "server_name")
	@Expose
	private String serverName;// 机器名称

	@Column(name = "server_desc")
	@Expose
	private String serverDesc;// 机器描述

	@Column(name = "server_ip")
	@Expose
	private String serverIp;// 机器IP

	@Column(name = "server_port")
	@JsonIgnore
	private int serverPort = SERVER_PORT;// PORT

	@Column(name = "root_path")
	@Expose
	private String rootPath;// 跟路径

	@Column(name = "user_name")
	@JsonIgnore
	private String username = SERVER_USERNAME;// 用户名

	@Column(name = "user_pwd")
	@JsonIgnore
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

	public BuCliServer buildCliServer() {
		BuCliServer buCliServer = new BuCliServer();
		buCliServer.setPassword(password);
		buCliServer.setRootPath(rootPath);
		buCliServer.setServerPort(serverPort);
		buCliServer.setStatus(status);
		buCliServer.setUsername(username);
		buCliServer.setSys_id(sys_id);
		return buCliServer;
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

	public boolean asDataCenter() {
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

	@Override
	public String toString() {
		return toJson(false);
	}

}
