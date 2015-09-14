package org.bu.core.pact;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author janson
 * 
 */
public class ErrorCode {
	public static int SUCCESS = 0;
	public static int FAILED = 1;

	public static String HEADER = "errorcode";
	public static String HEADER_MSG = "error_msg";

	public static int UNAUTHENTICATED = 1000;

	public static int SERVER_ERROR = 2000;
	public static int PARAM_ERROR = 2002;

	public static int CLINET_CONNET_ERROR = 3000;// 连接客户端异常
	public static int CLINET_PUBLISH_MENU_EXISTED = 3001;// 发布的目录不存在
	public static int CLINET_SUBSCRIBE_MENU_EXISTED = 3001;// 订阅存储目录不存在

	public static Map<Integer, String> revalue = new HashMap<Integer, String>();

	public static String getErrorMsg(int code) {
		return revalue.get(code);
	}

	static {
		revalue.put(SUCCESS, "SUCCESS");

		revalue.put(SERVER_ERROR, "server's error");
		revalue.put(PARAM_ERROR, "paramter's error");

		revalue.put(CLINET_CONNET_ERROR, "the server connect error");
		revalue.put(CLINET_PUBLISH_MENU_EXISTED, "the publish menu does not exist ");
		revalue.put(CLINET_SUBSCRIBE_MENU_EXISTED, "the subscribe menu does not exist ");

	}

}
