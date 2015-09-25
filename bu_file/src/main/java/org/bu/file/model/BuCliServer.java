package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;
import org.bu.file.init.MgrServerData;

@Entity
@Table(name = "t_cli_server")
public class BuCliServer extends BuModel implements MgrServerData {

	private static final long serialVersionUID = -2959557692173646057L;

	@Column(name = "server_port")
	private int serverPort = SERVER_PORT;// PORT

	@Column(name = "root_path")
	private String rootPath;// 跟路径

	@Column(name = "user_name")
	private String username = SERVER_USERNAME;// 用户名

	@Column(name = "user_pwd")
	private String password = SERVER_PWD;// 访问密码

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

	@Override
	public String toString() {
		return toJson();
	}

}
