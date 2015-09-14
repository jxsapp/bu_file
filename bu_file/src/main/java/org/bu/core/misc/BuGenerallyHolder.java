package org.bu.core.misc;

import java.util.UUID;

public class BuGenerallyHolder {

	public static String nextTimeNumber() {
		return BuTimer.getSDFMMddHHmmss().format(System.currentTimeMillis());
	}

	public static String nextSerialNumber() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("\\-", "");
	}

	public static String subSerialNumber() {
		return nextSerialNumber().substring(0, 10);
	}

}
