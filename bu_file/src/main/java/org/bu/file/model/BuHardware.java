package org.bu.file.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class BuHardware {

	private static Sigar sigar = new Sigar();

	private Object cpus = null;
	private Object memory = null;
	private Object disks = null;
	private Object pathDisk = null;

	public static BuHardware getData(String path) {
		BuHardware hardware = new BuHardware();
		getCpus(hardware);
		getMomory(hardware);
		getDiskInfos(hardware, path);
		return hardware;
	}

	/**
	 * @vendor 供应商
	 * @model 型号(主频包含在内)
	 * @idle 空闲(比率)
	 * @user 使用
	 * @wait等待
	 * @irq 中断
	 * @param buHardware
	 */
	private static void getCpus(BuHardware buHardware) {
		try {
			CpuPerc[] percs = sigar.getCpuPercList();
			buHardware.setCpus(percs);
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @total 4183597056,
	 * @ram 3992,
	 * @used 3210285056,
	 * @free 973312000,
	 * @actualUsed 3113713664,
	 * @actualFree 1069883392,
	 * @usedPercent 74.42671037198474,
	 * @freePercent 25.573289628015267
	 * 
	 * @param buHardware
	 */
	private static void getMomory(BuHardware buHardware) {
		try {
			Mem mem = sigar.getMem();
			buHardware.setMemory(mem);
		} catch (SigarException e) {
			e.printStackTrace();
		}
	}

	public static void getDiskInfos(BuHardware buHardware, String path) {
		try {
			List<BuDeskInfo> disks = new ArrayList<BuDeskInfo>();
			FileSystem[] fsList = sigar.getFileSystemList();
			if (null != fsList) {
				for (FileSystem fs : fsList) {
					if (fs.getType() == FileSystem.TYPE_LOCAL_DISK) {
						FileSystemUsage usage = sigar.getFileSystemUsage(fs.getDirName());
						disks.add(BuDeskInfo.build(usage, fs));
					}
				}
			}
			buHardware.setDisks(disks);
		} catch (SigarException e) {
			e.printStackTrace();
		}
		if (!StringUtils.isEmpty(path)) {
			File file = new File(path);
			if (null != file && file.exists()) {
				try {
					FileSystemUsage usage = sigar.getFileSystemUsage(path);
					FileSystem fs = new FileSystem();
					BuDeskInfo deskInfo = BuDeskInfo.build(usage, fs);
					deskInfo.setDevName(path);
					buHardware.pathDisk = deskInfo;
				} catch (SigarException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Object getPathDisk() {
		return pathDisk;
	}

	public void setPathDisk(Object pathDisk) {
		this.pathDisk = pathDisk;
	}

	public Object getCpus() {
		return cpus;
	}

	public void setCpus(Object cpus) {
		this.cpus = cpus;
	}

	public Object getMemory() {
		return memory;
	}

	public void setMemory(Object memory) {
		this.memory = memory;
	}

	public Object getDisks() {
		return disks;
	}

	public void setDisks(Object disks) {
		this.disks = disks;
	}

}
