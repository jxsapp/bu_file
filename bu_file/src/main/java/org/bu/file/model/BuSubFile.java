package org.bu.file.model;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bu.core.misc.Timer;
import org.bu.file.dao.BuCliCountDao;
import org.bu.file.misc.FileSizeHolder;

public class BuSubFile {
	private String name = "";
	private String lastModified = "";
	private String size = "";
	private int subCount = 0;

	public BuSubFile(File file) {
		super();
		this.name = file.getName();
		this.lastModified = Timer.getSDFyyyy_MM_ddHHmm().format(file.lastModified());
	}

	public static Map<String, Object> get(BuCliCountDao buFileCountDao, File file, Set<String> areaCodes, Set<String> menuIds) {

		Map<String, Object> rst = new LinkedHashMap<String, Object>();
		List<BuSubFile> directorys = new ArrayList<BuSubFile>();
		List<BuSubFile> files = new ArrayList<BuSubFile>();

		File[] tempList = file.listFiles();
		if (null == tempList) {
			tempList = new File[0];
		}
		//

		for (File child : tempList) {
			BuSubFile buSubFile = new BuSubFile(child);
			if (child.isDirectory()) {
				File[] chiFiles = child.listFiles();
				int size = 0;
				if (null != chiFiles) {
					size = chiFiles.length;
				}
				String menuTypeCode = "";
				if (null != child.getParentFile()) {
					menuTypeCode = child.getParentFile().getName();
				}
				String areaEncode = child.getName();
				int subCount = 0;
				if (menuIds.contains(menuTypeCode) && areaCodes.contains(areaEncode)) {
					subCount = buFileCountDao.count(areaEncode, menuTypeCode);
				}
				buSubFile.size = Integer.toString(size);
				buSubFile.subCount = subCount;
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

	public int getSubCount() {
		return subCount;
	}

	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}

}
