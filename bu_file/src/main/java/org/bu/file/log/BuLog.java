package org.bu.file.log;

import org.slf4j.Logger;
import org.slf4j.Marker;

public class BuLog implements Logger {

	private Logger logger = null;

	public BuLog(Class<?> clazz) {
		super();
		this.logger = org.slf4j.LoggerFactory.getLogger(clazz);
	}

	public static BuLog getLogger(Class<?> clazz) {
		return new BuLog(clazz);
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
	}

	@Override
	public void debug(String format, Object arg) {
		logger.debug(format, arg);
	}

	@Override
	public void debug(String format, Object... arguments) {
		logger.debug(format, arguments);
	}

	@Override
	public void debug(String msg, Throwable t) {
		logger.debug(msg, t);
	}

	@Override
	public void debug(Marker marker, String msg) {
		logger.debug(marker, msg);
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		logger.debug(format, arg1, arg2);
	}

	@Override
	public void debug(Marker marker, String format, Object arg) {
		logger.debug(marker, format, arg);
	}

	@Override
	public void debug(Marker marker, String format, Object... arguments) {
		logger.debug(marker, format, arguments);
	}

	@Override
	public void debug(Marker marker, String msg, Throwable t) {
		logger.debug(marker, msg, t);
	}

	@Override
	public void debug(Marker marker, String format, Object arg1, Object arg2) {
		logger.debug(marker, format, arg1, arg2);
	}

	@Override
	public void error(String msg) {
		logger.error(msg);
	}

	@Override
	public void error(String format, Object arg) {
		logger.error(format, arg);
	}

	@Override
	public void error(String format, Object... arguments) {
		logger.error(format, arguments);
	}

	@Override
	public void error(String msg, Throwable t) {
		logger.error(msg, t);
	}

	@Override
	public void error(Marker marker, String msg) {
		logger.error(marker, msg);
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		logger.error(format, arg1, arg2);
	}

	@Override
	public void error(Marker marker, String format, Object arg) {
		logger.error(marker, format, arg);
	}

	@Override
	public void error(Marker marker, String format, Object... arguments) {
		logger.error(marker, format, arguments);
	}

	@Override
	public void error(Marker marker, String msg, Throwable t) {
		logger.error(marker, msg, t);
	}

	@Override
	public void error(Marker marker, String format, Object arg1, Object arg2) {
		logger.error(marker, format, arg1, arg2);
	}

	@Override
	public String getName() {
		return logger.getName();
	}

	@Override
	public void info(String msg) {
		logger.info(msg);
	}

	@Override
	public void info(String format, Object arg) {
		logger.info(format, arg);
	}

	@Override
	public void info(String format, Object... arguments) {
		logger.info(format, arguments);
	}

	@Override
	public void info(String msg, Throwable t) {
		logger.info(msg, t);
	}

	@Override
	public void info(Marker marker, String msg) {
		logger.info(marker, msg);
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		logger.info(format, arg1, arg2);
	}

	@Override
	public void info(Marker marker, String format, Object arg) {
		logger.info(marker, format, arg);
	}

	@Override
	public void info(Marker marker, String format, Object... arguments) {
		logger.info(marker, format, arguments);
	}

	@Override
	public void info(Marker marker, String msg, Throwable t) {
		logger.info(marker, msg, t);
	}

	@Override
	public void info(Marker marker, String format, Object arg1, Object arg2) {
		logger.info(marker, format, arg1, arg2);
	}

	@Override
	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isDebugEnabled(Marker marker) {
		return logger.isDebugEnabled(marker);
	}

	@Override
	public boolean isErrorEnabled() {
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isErrorEnabled(Marker marker) {
		return logger.isDebugEnabled(marker);
	}

	@Override
	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isInfoEnabled(Marker marker) {
		return logger.isInfoEnabled(marker);
	}

	@Override
	public boolean isTraceEnabled() {
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isTraceEnabled(Marker marker) {
		return logger.isTraceEnabled(marker);
	}

	@Override
	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isWarnEnabled(Marker marker) {
		return logger.isWarnEnabled(marker);
	}

	@Override
	public void trace(String msg) {
		logger.trace(msg);
	}

	@Override
	public void trace(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String arg0, Object... arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0, Object arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0, Object... arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Object... arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void warn(Marker marker, String format, Object arg1, Object arg2) {
		logger.warn(marker, format, arg1, arg2);
	}

}