package org.bu.file.web.mgr.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.JsonHttp;
import org.bu.core.pact.PactMaster;

public class BuCliMonitorConnectPact extends PactMaster {

	public BuCliMonitorConnectPact(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super(jsonHttp, httpListener);
	}

	private String getUrl(String uri) {
		return String.format("%s/client/monitor/connect", uri);
	}

	public void connect(String uri) {
		HashMap<String, String> params = new HashMap<String, String>();
		jsonHttp.getJson(getUrl(uri), params, new BuHttpListener() {
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
