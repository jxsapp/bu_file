package org.bu.file.log;

import org.slf4j.Logger;

public class LogHolder {

	public static Logger getLogger(Class<?> clazz) {
		return org.slf4j.LoggerFactory.getLogger(clazz);
	}

}
