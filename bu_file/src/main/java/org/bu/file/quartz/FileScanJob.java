package org.bu.file.quartz;

import java.util.Date;

import org.bu.file.log.BuLog;

public class FileScanJob {

	private static BuLog buLog = BuLog.getLogger(FileScanJob.class);

	/*
	 * 用来扫描文件
	 */
	public void work() {
		buLog.info("当前时间:" + new Date().toString());
	}
}