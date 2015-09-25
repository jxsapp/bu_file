package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;
import org.bu.file.model.BuCliSubscribe;

/**
 * 发布目录到客户端去
 * 
 * @author jxs
 * 
 */
public class BuCliSubMenuCreatePact extends PactMaster {

	public BuCliSubMenuCreatePact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri) {
		return String.format("%s/client/config/sub_menu/subscribe", uri);
	}

	public void subscribe(String uri, BuCliSubscribe cliSubscribe) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("savePath", cliSubscribe.getSavePath());
		params.put("pubServer", cliSubscribe.getPubServer());
		params.put("publishId", cliSubscribe.getPublishId());
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
