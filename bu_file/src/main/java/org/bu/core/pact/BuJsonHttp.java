package org.bu.core.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.springframework.stereotype.Repository;

@Repository("jsonHttp")
public class BuJsonHttp implements JsonHttp {

	@Override
	public void postJson(String url, HashMap<String, String> params, BuHttpListener httpListener) {
		new BuHttp(url, httpListener).post(params);
	}

	@Override
	public void postJson(String url, BuJSON params, BuHttpListener httpListener) {
		new BuHttp(url, httpListener).post(params);
	}

	@Override
	public void getJson(String url, HashMap<String, String> params, BuHttpListener httpListener) {
		new BuHttp(url, httpListener).get(params);
	}

}
