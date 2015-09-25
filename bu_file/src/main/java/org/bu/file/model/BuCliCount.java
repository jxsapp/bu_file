package org.bu.file.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.bu.core.model.BuModel;

@Entity
@Table(name = "t_cli_count")
public class BuCliCount extends BuModel {

	private static final long serialVersionUID = 5086579006775487410L;
	private String areaCode = "";
	@ManyToOne
	@JoinColumn(name = "pub_id")
	private BuCliPublish cliPublish;
	private int count = 0;
	private long size = 0;

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public BuCliPublish getCliPublish() {
		return cliPublish;
	}

	public void setCliPublish(BuCliPublish cliPublish) {
		this.cliPublish = cliPublish;
	}

}