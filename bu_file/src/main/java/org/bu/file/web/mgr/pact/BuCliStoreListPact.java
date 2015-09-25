package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;

/**
 * 获取发布端发布的数据
 * 
 * @author jxs
 * 
 */
public class BuCliStoreListPact extends PactMaster {

	public BuCliStoreListPact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri, long lastTime) {
		return String.format("%s/client/config/store/list/%d", uri, lastTime);
	}

	public void publish(String uri, long lastTime) {
		HashMap<String, String> params = new HashMap<String, String>();
		jsonHttp.postJson(getUrl(uri, lastTime), params, new BuHttpListener() {
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
