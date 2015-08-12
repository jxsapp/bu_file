package org.bu.file.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BuModel implements Serializable {
	private static final long serialVersionUID = 4683146454086887498L;
	@javax.persistence.Id()
	@javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.AUTO)
	@javax.persistence.OrderBy(value = "row_id")
	@Column(name = "row_id")
	private java.lang.Integer id;

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

}
