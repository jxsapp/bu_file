package org.bu.file.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @目的 控制数据初始化命令类	
 * @时间 May 4, 2010
 * @作者 BestUpon
 * @邮箱 bestupon@foxmail.com
 *
 */
public abstract class Cmd {
	protected final Logger log = LoggerFactory.getLogger(super.getClass());
	private String name;

	public abstract void execute();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
