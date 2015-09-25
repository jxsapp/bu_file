package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;
import org.bu.file.model.BuCliServer;

/**
 * 发布目录到客户端去
 * 
 * @author jxs
 * 
 */
public class BuCliServerInitPact extends PactMaster {

	public BuCliServerInitPact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri) {
		return String.format("%s/client/config/ftpserver/init", uri);
	}

	public void publish(String uri, BuCliServer cliServer) {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("serverPort", Integer.toString(cliServer.getServerPort()));// PORT
		params.put("rootPath", cliServer.getRootPath());// 跟路径
		params.put("username", cliServer.getUsername());// 用户名
		params.put("password", cliServer.getPassword());// 访问密码

		jsonHttp.postJson(getUrl(uri), params, new BuHttpListener() {
			@Override
			public void onSuccess(BuJSON json) {
				httpListener.onSuccess(json);
			}

			@Override
			public void onFailuire(int status) {
				httpListener.onFailuire(status);
			}
		});
	}
}
