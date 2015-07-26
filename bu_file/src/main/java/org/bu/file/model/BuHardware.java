package org.bu.file.model;

import java.util.Random;

public class BuHardware {
	private float cupUsage = 0;// CPU使用率
	private int cupCore = 0;// cup 核数

	private long diskSize = 0;// 监控目录所在的磁盘大小
	private float diskUsage = 0; // 监控目录磁盘使用率

	private long memorySize = 0;// 内存总大小
	private float memoryUsage = 0;// 内存使用率

	public static BuHardware getTest() {
		Random random = new Random();

		BuHardware hardware = new BuHardware();

		hardware.cupUsage = random.nextFloat();// CPU使用率
		hardware.cupCore = random.nextInt(16);// cup 核数

		hardware.diskSize = random.nextLong();// 监控目录所在的磁盘大小
		hardware.diskUsage = random.nextFloat(); // 监控目录磁盘使用率

		hardware.memorySize = random.nextLong();// 内存总大小
		hardware.memoryUsage = random.nextFloat();// 内存使用率

		return hardware;

	}

	public float getCupUsage() {
		return cupUsage;
	}

	public void setCupUsage(float cupUsage) {
		this.cupUsage = cupUsage;
	}

	public int getCupCore() {
		return cupCore;
	}

	public void setCupCore(int cupCore) {
		this.cupCore = cupCore;
	}

	public long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(long diskSize) {
		this.diskSize = diskSize;
	}

	public float getDiskUsage() {
		return diskUsage;
	}

	public void setDiskUsage(float diskUsage) {
		this.diskUsage = diskUsage;
	}

	public long getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(long memorySize) {
		this.memorySize = memorySize;
	}

	public float getMemoryUsage() {
		return memoryUsage;
	}

	public void setMemoryUsage(float memoryUsage) {
		this.memoryUsage = memoryUsage;
	}

}
