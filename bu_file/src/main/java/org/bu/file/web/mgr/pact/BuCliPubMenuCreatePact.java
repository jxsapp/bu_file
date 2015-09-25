package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;
import org.bu.file.model.BuCliPublish;

/**
 * 发布目录到客户端去
 * 
 * @author jxs
 * 
 */
public class BuCliPubMenuCreatePact extends PactMaster {

	public BuCliPubMenuCreatePact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri) {
		return String.format("%s/client/config/pub_menu/publish", uri);
	}

	public void publish(String uri, BuCliPublish publish) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("path", publish.getPath());
		params.put("desc", publish.getDesc());
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
