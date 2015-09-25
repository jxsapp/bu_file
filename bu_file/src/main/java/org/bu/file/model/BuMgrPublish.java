package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_mgr_publish")
public class BuMgrPublish extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@ManyToOne
	@JoinColumn(name = "server_id")
	private BuMgrServer mgrServer;

	@Column(name = "pub_path", length = 2000)
	private String path;//

	@Column(name = "pub_desc")
	private String desc;

	public static BuMgrPublish buildPublish(BuCliPublish cliPublish, BuMgrServer mgrServer) {
		BuMgrPublish buMgrPublish = new BuMgrPublish();
		buMgrPublish.setMgrServer(mgrServer);
		buMgrPublish.setPath(cliPublish.getPath());
		buMgrPublish.setDesc(cliPublish.getDesc());
		return buMgrPublish;
	}

	public BuMgrServer getMgrServer() {
		return mgrServer;
	}

	public void setMgrServer(BuMgrServer mgrServer) {
		this.mgrServer = mgrServer;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	@Override
	public String toString() {
		return toJson();
	}
}
