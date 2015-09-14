package org.bu.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_mgr_subscribe")
public class BuMgrSubscribe extends BuModel {
	private static final long serialVersionUID = 2110330618846561394L;

	@Column(name = "publish_id")
	private BuMgrPublish mgrPublish;

	@Column(name = "save_path", length = 2000)
	private String savePath;

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

}
