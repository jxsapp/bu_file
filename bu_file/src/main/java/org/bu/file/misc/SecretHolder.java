package org.bu.file.misc;

import org.apache.commons.codec.binary.Base64;

public class SecretHolder {

	public static String getSecret(String... params) {
		StringBuilder builder = new StringBuilder();
		if (null != params) {
			for (String param : params) {
				builder.append(param + ":");
			}
		}
		return Base64.encodeBase64String(builder.toString().getBytes());
	}

}
