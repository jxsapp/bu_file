package org.bu.core.pact;


import org.bu.core.pact.BuHttp.BuHttpListener;

public abstract class PactMaster {

	protected JsonHttp jsonHttp;
	protected BuHttpListener httpListener;

	public PactMaster(JsonHttp jsonHttp, BuHttpListener httpListener) {
		super();
		this.jsonHttp = jsonHttp;
		this.httpListener = httpListener;
	}

}
