package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_cli_subscribe")
public class BuCliSubscribe extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "src_ip")
	private String sourceIp;// 发布资源放ID

	@Column(name = "src_path", length = 2000)
	private String sourcePath;// 发布资源方路径

	@Column(name = "save_path", length = 2000)
	private String savePath;// 本订阅方接受存放地址

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

}
