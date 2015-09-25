package org.bu.core.misc;

import javax.servlet.http.HttpServletResponse;

import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;

import com.google.gson.annotations.Expose;

public class BuRst {

	@Expose
	private BuError error = null;
	@Expose
	private Object rst = null;
	@Expose
	private int count = 0;

	public BuRst(ErrorcodeException err) {
		super();
		this.error = BuError.get(err);
	}

	public static BuRst get(ErrorcodeException err) {
		return new BuRst(err);
	}

	public static BuRst get(ControllerSupport support, HttpServletResponse response, ErrorcodeException e) {
		support.setRespHeader(response, e);
		return get(e);
	}

	public static BuRst getSuccess() {
		return new BuRst(new ErrorcodeException(ErrorCode.SUCCESS, "success"));
	}

	public boolean isSuccess() {
		return error.isSuccess();
	}

	public static BuRst getUnAuthorized(ControllerSupport support, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		ErrorcodeException e = new ErrorcodeException(ErrorCode.UNAUTHENTICATED);
		support.setRespHeader(response, e);
		return get(e);
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String toJson() {
		return toJson(true);
	}

	public String toJson(boolean all) {
		return BuGsonHolder.getJson(this, all);
	}

}
