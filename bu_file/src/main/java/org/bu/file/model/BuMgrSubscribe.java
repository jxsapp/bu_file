package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_mgr_subscribe")
public class BuMgrSubscribe extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@ManyToOne
	@JoinColumn(name = "server_id")
	private BuMgrServer mgrServer;

	@Column(name = "publish_id")
	private BuMgrPublish mgrPublish;

	@Column(name = "save_path", length = 2000)
	private String savePath;

	
	
	public static BuMgrSubscribe buildSubscribe(BuCliSubscribe cliSubscribe, BuMgrServer mgrServer) {
		BuMgrSubscribe buMgrPublish = new BuMgrSubscribe();
		buMgrPublish.setMgrServer(mgrServer);
//		buMgrPublish.setPath(cliPublish.getPath());
//		buMgrPublish.setDesc(cliPublish.getDesc());
		return buMgrPublish;
	}
	
	
	public BuMgrPublish getMgrPublish() {
		return mgrPublish;
	}

	public void setMgrPublish(BuMgrPublish mgrPublish) {
		this.mgrPublish = mgrPublish;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public BuMgrServer getMgrServer() {
		return mgrServer;
	}

	public void setMgrServer(BuMgrServer mgrServer) {
		this.mgrServer = mgrServer;
	}
	
	@Override
	public String toString() {
		return toJson();
	}

}
