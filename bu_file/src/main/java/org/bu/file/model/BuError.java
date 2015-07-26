package org.bu.file.model;

import org.bu.file.misc.Error;

public class BuError {
	private int rst;
	private String msg = "";
	private String key = "";

	public BuError(String key) {
		this(Error.SUCCESS, key);
	}

	public BuError(Error error, String key) {
		this.rst = error.index;
		this.msg = error.desc;
		this.key = key;
	}

	public static BuError get(Error error, String secret_key) {
		return new BuError(error, secret_key);
	}

	public int getRst() {
		return rst;
	}

	public void setRst(int rst) {
		this.rst = rst;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
