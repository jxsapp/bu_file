package org.bu.core.pact;


public interface ResponseCode {
	static final String SESSIONID = "SESSIONID";// SESSIONID
	static final String STATUS = "STATUS";
	static final String SIZE = "SIZE";
	static final String UID = "UID";

	interface HeaderResponseCode {
		/** 成功 */
		static final String SUCCESS = "0000";
		/** 数据包格式错误 */
		static final String DATA_PACKET_FORMAT_ERROR = "0001";
		/** 运行时程序异常(系统处理错误) */
		static final String RUN_TIME_EXCEPTION = "0002";
		/** 服务繁忙不能处理 */
		static final String SERVER_BUSY = "0003";
		/** 不支持该协议版本 */
		static final String PROTOCL_DEPRECATE = "0004";
		/** SESSION 失效 **/
		static final String SESSION_INVALIDA = "9999";

	}

	interface ErrorMessageResponseCode {
		interface Key {
			static final String MESSAGE = "WEI_MI";/* session是否已经超时 */
		}

	}

	interface HeartBeatResponseCode {
		interface Key {
			static final String STATUS = "STATUS";/* session是否已经超时 */
		}

		interface Value {
			// (响应码 0 网络状态未切换 1 网络状态已切换 )
			static final String IP_CHANGED = "2";//
			static final String SESSION_OVERDUE = "1";//
			static final String SESSION_EFFECTIVE = "0";
		}
	}

	interface SessionResponse {
		interface Key {
			static final String STATUS = "STATUS";/* session是否已经超时 */
		}

		interface Value {
			static final int STATUS_OUT_TIME = 9999;// 登录成功
		}
	}

	interface DefaultValue {
		static final int STATUS_SUCCESS = 1;// 成功
		static final int STATUS_FAIL = 2;// 失败
		static final int OFFLINE_PAGE_SIZE = 100;// 失败
	}

	interface PageCode {
		static final int DEFAULT_PAGE_SIZE = 20;
		static final int THE_MAX_PAGE = Integer.MAX_VALUE;
	}

	interface InviteValue {
		// M:添加删除方式1= 添加，2= 删除, 3=修改
		/** 添加删除操作 */
		static final int M_NULL = -1;// 增加
		static final int M_1 = 1;// 增加
		static final int M_2 = 2;// 删除
		static final int M_3 = 3;// 修改

		/** 被删除 */
		static final int INVTSTA_0 = 0;// 无效
		static final int INVTSTA_1 = 1;// 有效
		static final int INVTSTA_2 = 2;// 被删除

		/** 是否已经记事了 */
		static final int IF_REC_0 = 0;// 没有记事
		static final int IF_REC_1 = 1;// 记事
		static final int IF_REC_2 = 2;// 主动退出

		/** 是否是系统用户 */
		static final int IF_SYSU_0 = 0;// 临时用户
		static final int IF_SYSU_1 = 1;// 系统用户

		static final int IFACTOR_NO = 0;// 不是记账人
		static final int IFACTOR_YES = 1;// 是记账人

	}
}