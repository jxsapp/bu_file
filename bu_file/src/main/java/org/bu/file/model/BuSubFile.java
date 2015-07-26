package org.bu.file.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.bu.file.misc.FileSizeHolder;
import org.bu.file.misc.Timer;

public class BuSubFile {
	private String name = "";
	private String lastModified = "";
	private String size = "";

	public BuSubFile(File file) {
		super();
		this.name = file.getName();
		this.lastModified = Timer.getSDFyyyy_MM_ddHHmm().format(file.lastModified());
	}

	public static Map<String, Object> get(File file) {

		Map<String, Object> rst = new LinkedHashMap<String, Object>();
		List<BuSubFile> directorys = new ArrayList<BuSubFile>();
		List<BuSubFile> files = new ArrayList<BuSubFile>();

		File[] tempList = file.listFiles();
		if (null == tempList) {
			tempList = new File[0];
		}
		for (File child : tempList) {
			BuSubFile buSubFile = new BuSubFile(child);
			if (child.isDirectory()) {
				File[] chiFiles = child.listFiles();
				int size = 0;
				if (null != chiFiles) {
					size = chiFiles.length;
				}
				buSubFile.size = Integer.toString(size);
				directorys.add(buSubFile);
			}

			if (child.isFile()) {
				buSubFile.size = FileSizeHolder.formatFileSize(child.length());
				files.add(buSubFile);
			}
		}
		rst.put("total_space", FileSizeHolder.formatFileSize(file.getTotalSpace()));
		rst.put("usable_space", FileSizeHolder.formatFileSize(file.getUsableSpace()));
		rst.put("free_space", FileSizeHolder.formatFileSize(file.getFreeSpace()));
		rst.put("lastModified", Timer.getSDFyyyy_MM_ddHHmm().format(file.lastModified()));
		rst.put("path", file.getPath());
		rst.put("name", file.getName());
		rst.put("size", tempList.length);

		rst.put("directorys", directorys);
		rst.put("files", files);

		return rst;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

}
