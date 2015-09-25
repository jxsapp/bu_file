package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

/**
 * 文件下载队列
 * 
 * @author jxs
 */
@Entity
@Table(name = "t_cli_download")
public class BuCliDownload extends BuModel {

	private static final long serialVersionUID = 5229608719468148552L;

	@ManyToOne
	@JoinColumn(name = "sub_id")
	private BuCliSubscribe cliSubscribe;

	@Column(name = "save_path", length = 2000)
	private String path;// 相对路径

	public BuCliSubscribe getCliSubscribe() {
		return cliSubscribe;
	}

	public void setCliSubscribe(BuCliSubscribe cliSubscribe) {
		this.cliSubscribe = cliSubscribe;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Override
	public String toString() {
		return toJson();
	}

}