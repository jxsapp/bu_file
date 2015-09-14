package org.bu.core.pact;

public class ErrorcodeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected int errorcode;
	private String message = "";

	public ErrorcodeException(int errorcode) {
		super();
		this.errorcode = errorcode;
		this.message = ErrorCode.getErrorMsg(errorcode);
	}

	public ErrorcodeException(int errorcode, String message) {
		super();
		this.errorcode = errorcode;
		this.message = message;
	}

	public ErrorcodeException(Exception e) {
		this(ErrorCode.SERVER_ERROR, e.getMessage());
	}

	/**
	 * @return the errorcode
	 */
	public int getErrorcode() {
		return errorcode;
	}

	public String getMessage() {
		return message;
	}
}
