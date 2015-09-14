package org.bu.core.pact;

import java.util.HashMap;

import org.bu.core.misc.BuJSON;
import org.bu.core.pact.BuHttp.BuHttpListener;

public interface JsonHttp {

	public void postJson(String url, HashMap<String, String> params, BuHttpListener httpListener);
	
	public void postJson(String url, BuJSON params, BuHttpListener httpListener);

	public void getJson(String url, HashMap<String, String> params, BuHttpListener httpListener);
}
