package org.bu.core.misc;

import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.file.misc.Error;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class BuError {
	private int code;
	private String msg = "";
	private String key = "";

	public BuError(String key) {
		this(Error.SUCCESS, key);
	}

	public boolean isSuccess() {
		return ErrorCode.SUCCESS == getCode();
	}

	public BuError(ErrorcodeException error) {
		this.code = error.getErrorcode();
		this.msg = error.getMessage();
	}

	public BuError(Error error, String key) {
		this.code = error.index;
		this.msg = error.desc;
		this.key = key;
	}

	public static BuError get(Error error, String secret_key) {
		return new BuError(error, secret_key);
	}

	public static BuError get(ErrorcodeException error) {
		return new BuError(error);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public static BuError getObj(JSONObject json) {
		BuError list = null;
		java.lang.reflect.Type type = new TypeToken<BuError>() {
		}.getType();
		Gson gson = new Gson();
		list = gson.fromJson(json.toString(), type);
		return list;

	}

}
