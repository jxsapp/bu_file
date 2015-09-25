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
	public static int NOT_HAS_DATA_CENTER = 1001;
	public static int FILE_NOT_FOUND = 1010;
	public static int FILE_NOT_DIRECTORY = 1011;

	public static int SERVER_ERROR = 2000;
	public static int PARAM_ERROR = 2002;
	public static int PAGE_NOT_FOUND = 2004;

	public static int CLINET_CONNET_ERROR = 3000;// 连接客户端异常
	public static int CLINET_PUBLISH_MENU_EXISTED = 3001;// 发布的目录不存在
	public static int CLINET_SUBSCRIBE_MENU_EXISTED = 3002;// 订阅存储目录不存在
	public static int CLINET_SERVER_EXISTED = 3003;// 本机器不存在
	public static int CLINET_PUBLISH_MENU_ERROR = 3004;// 客户端创建目录失败
	public static int CLINET_MENU_EXISTED = 3005;// 客户端创建目录不存在

	public static Map<Integer, String> revalue = new HashMap<Integer, String>();

	public static String getErrorMsg(int code) {
		return revalue.get(code);
	}

	static {
		revalue.put(SUCCESS, "SUCCESS");

		revalue.put(SERVER_ERROR, "server's error");
		revalue.put(PARAM_ERROR, "paramter's error");

		revalue.put(UNAUTHENTICATED, " un auth");
		revalue.put(NOT_HAS_DATA_CENTER, "not has data center");
		revalue.put(FILE_NOT_FOUND, "file not found");
		revalue.put(FILE_NOT_DIRECTORY, "this is not dirrectory ");

		revalue.put(CLINET_CONNET_ERROR, "the server connect error");
		revalue.put(CLINET_PUBLISH_MENU_EXISTED, "the publish menu does not exist ");
		revalue.put(CLINET_SUBSCRIBE_MENU_EXISTED, "the subscribe menu does not exist ");
		revalue.put(CLINET_SERVER_EXISTED, "the server does not exist ");
		revalue.put(CLINET_PUBLISH_MENU_ERROR, "the server publish menu error ");
		revalue.put(CLINET_MENU_EXISTED, "the menu does not exist ");

	}

}
