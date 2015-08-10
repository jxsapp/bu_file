package org.bu.file.init;

import org.bu.file.log.BuLog;

/**
 * 
 * @目的 控制数据初始化命令类
 * @时间 May 4, 2010
 * @作者 BestUpon
 * @邮箱 bestupon@foxmail.com
 * 
 */
public class BuCmd {
	BuLog log = BuLog.getLogger(super.getClass());
	private String name;

	public void execute() {

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
