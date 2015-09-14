package org.bu.core.auth;

public interface IAuthService {

	boolean authority(String token, String action);
}
