package org.bu.core.web;

import org.bu.core.auth.IAuthService;

public abstract class ControllerLogic {
	protected ControllerSupport support;
	protected IAuthService authService;

	public ControllerLogic(ControllerSupport support) {
		super();
		this.support = support;
		this.authService = support.getAuthService();
	}

}