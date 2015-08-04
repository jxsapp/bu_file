package org.bu.file.model;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;

public class BuDeskInfo {

	private String dirName = "";// "C:\",
	private String devName = "";// "C:\",
	private String typeName = "";// "local",
	private int type = 0;
	private String sysTypeName = "";// "NTFS",
	private String options = "";// "rw",

	private long total;// 153597432,
	private long free;// 147208068,
	private long used;// 6389364,
	private long avail;// 147208068,
	private long files;// -1,
	private long freeFiles;// -1,
	private long diskReads;// 39690,
	private long diskWrites;// 3439,
	private long diskReadBytes;// 870382592,
	private long diskWriteBytes;// 65959936,
	private double diskQueue;// 0,
	private double diskServiceTime;// -1,
	private double usePercent;// 0.05

	public static BuDeskInfo build(FileSystemUsage usage, FileSystem fs) {
		BuDeskInfo deskinfo = new BuDeskInfo();

		deskinfo.dirName = fs.getDirName();
		deskinfo.devName = fs.getDevName();
		deskinfo.typeName = fs.getTypeName();
		deskinfo.type = fs.getType();
		deskinfo.sysTypeName = fs.getSysTypeName();
		deskinfo.options = fs.getOptions();

		deskinfo.total = usage.getTotal();// 153597432,
		deskinfo.free = usage.getFree();// 147208068,
		deskinfo.used = usage.getUsed();// 6389364,
		deskinfo.avail = usage.getAvail();// 147208068,
		deskinfo.files = usage.getFiles();// -1,
		deskinfo.freeFiles = usage.getFreeFiles();// -1,
		deskinfo.diskReads = usage.getDiskReads();// 39690,
		deskinfo.diskWrites = usage.getDiskWrites();// 3439,
		deskinfo.diskReadBytes = usage.getDiskReadBytes();// 870382592,
		deskinfo.diskWriteBytes = usage.getDiskWriteBytes();// 65959936,
		deskinfo.diskQueue = usage.getDiskQueue();// 0,
		deskinfo.diskServiceTime = usage.getDiskServiceTime();// -1,
		deskinfo.usePercent = usage.getUsePercent();// 0.05

		return deskinfo;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getFree() {
		return free;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public long getUsed() {
		return used;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public long getAvail() {
		return avail;
	}

	public void setAvail(long avail) {
		this.avail = avail;
	}

	public long getFiles() {
		return files;
	}

	public void setFiles(long files) {
		this.files = files;
	}

	public long getFreeFiles() {
		return freeFiles;
	}

	public void setFreeFiles(long freeFiles) {
		this.freeFiles = freeFiles;
	}

	public long getDiskReads() {
		return diskReads;
	}

	public void setDiskReads(long diskReads) {
		this.diskReads = diskReads;
	}

	public long getDiskWrites() {
		return diskWrites;
	}

	public void setDiskWrites(long diskWrites) {
		this.diskWrites = diskWrites;
	}

	public long getDiskReadBytes() {
		return diskReadBytes;
	}

	public void setDiskReadBytes(long diskReadBytes) {
		this.diskReadBytes = diskReadBytes;
	}

	public long getDiskWriteBytes() {
		return diskWriteBytes;
	}

	public void setDiskWriteBytes(long diskWriteBytes) {
		this.diskWriteBytes = diskWriteBytes;
	}

	public double getDiskQueue() {
		return diskQueue;
	}

	public void setDiskQueue(double diskQueue) {
		this.diskQueue = diskQueue;
	}

	public double getDiskServiceTime() {
		return diskServiceTime;
	}

	public void setDiskServiceTime(double diskServiceTime) {
		this.diskServiceTime = diskServiceTime;
	}

	public double getUsePercent() {
		return usePercent;
	}

	public void setUsePercent(double usePercent) {
		this.usePercent = usePercent;
	}

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSysTypeName() {
		return sysTypeName;
	}

	public void setSysTypeName(String sysTypeName) {
		this.sysTypeName = sysTypeName;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

}