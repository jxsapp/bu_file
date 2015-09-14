package org.bu.file.model;

import org.bu.core.misc.BuError;
import org.bu.file.misc.Error;

public class BuRst {

	private BuError error = null;
	private Object rst = null;

	public BuRst(String key) {
		super();
		this.error = new BuError(key);
	}

	public BuRst(Error err, String key) {
		super();
		this.error = new BuError(err, key);
	}

	public static BuRst get(Error error, String secret_key) {
		return new BuRst(error, secret_key);
	}

	public static BuRst getNoPermissions(String secret_key) {
		return new BuRst(Error.NO_PERMISSIONS, secret_key);
	}

	public static BuRst getSuccess(String secret_key) {
		return new BuRst(Error.SUCCESS, secret_key);
	}

	public BuError getError() {
		return error;
	}

	public void setError(BuError error) {
		this.error = error;
	}

	public Object getRst() {
		return rst;
	}

	public void setRst(Object rst) {
		this.rst = rst;
	}

}
