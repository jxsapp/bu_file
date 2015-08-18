package org.bu.core.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.bu.core.misc.BuGsonHolder;
import org.hibernate.annotations.GenericGenerator;

import com.google.gson.annotations.Expose;

/**
 * abstract model.
 * 
 * @author Jiang XuSheng
 */
@MappedSuperclass
public abstract class BuModel implements Serializable {
	private static final long serialVersionUID = 2433713641939530097L;

	@Id
	@Column(name = "sys_id", length = 36)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@GeneratedValue(generator = "uuid")
	@Expose
	private String sys_id;

	@Column(name = "create_time", nullable = false)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Expose
	private Date createdTime = new Date();

	@Column(name = "updated_time", nullable = true)
	@Temporal(value = TemporalType.TIMESTAMP)
	@Expose
	private Date updatedTime = new Date();

	public BuModel() {
		super();
	}

	public String getSys_id() {
		return sys_id;
	}

	public void setSys_id(String id) {
		this.sys_id = id;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Date updatedTime) {
		this.updatedTime = updatedTime;
	}

	public boolean isNew() {
		return null == getSys_id();
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BuModel)) {
			return false;
		}
		BuModel that = (BuModel) obj;
		return null == this.getSys_id() ? false : this.getSys_id().equals(that.getSys_id());
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += null == getSys_id() ? 0 : getSys_id().hashCode() * 31;
		return hashCode;
	}

	public String toJson(boolean all) {
		return BuGsonHolder.getJson(this, all);
	}

	public String toJson() {
		return toJson(true);
	}

	@Override
	public String toString() {
		return toJson();
	}

}
