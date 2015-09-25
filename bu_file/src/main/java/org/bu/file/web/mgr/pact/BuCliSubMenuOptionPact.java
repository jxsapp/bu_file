package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.model.BuStatus;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;

/**
 * 发布目录到客户端去
 * 
 * @author jxs
 * 
 */
public class BuCliSubMenuOptionPact extends PactMaster {

	public BuCliSubMenuOptionPact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri) {
		return String.format("%s/client/config/sub_menu/option", uri);
	}

	public void option(String uri, String pubServer, String publishId, BuStatus buStatus) {
		HashMap<String, String> params = new HashMap<String, String>();

		params.put("pubServer", pubServer);
		params.put("publishId", publishId);
		params.put("status", Integer.toString(buStatus.getStatus()));

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
