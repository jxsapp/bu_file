package org.bu.file.misc;

import java.io.File;
import java.text.DecimalFormat;

public class FileSizeHolder {

	public static long getFolderSize(java.io.File file) {
		long size = 0;
		if (null != file && file.exists() && file.isDirectory()) {
			java.io.File[] fileList = file.listFiles();
			if (null != fileList) {
				for (File subFile : fileList) {
					if (subFile.isDirectory()) {
						size = size + getFolderSize(subFile);
					} else {
						size = size + subFile.length();
					}
				}
			}
		} else {
			size = getFileSizes(file);
		}
		return size;
	}

	/**
	 * 获取文件夹大小
	 * 
	 * @param file
	 *            File实例
	 * @return double 单位为M
	 * @throws Exception
	 */
	public static String getFolderSize4M(java.io.File file) {
		return formatFileSize(getFolderSize(file));
	}

	public static String formatFileSize(long fileSize) {// 转换文件大小

		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileSize == 0) {
			fileSizeString = "0K";
		} else if (fileSize < 1024) {
			fileSizeString = df.format((double) fileSize) + "B";
		} else if (fileSize < 1048576) {
			fileSizeString = df.format((double) fileSize / 1024) + "K";
		} else if (fileSize < 1073741824) {
			fileSizeString = df.format((double) fileSize / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileSize / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static String formatSize(String strSize) {
		String s;
		int L = strSize.length();
		if (L < 4)
			s = "0." + strSize.substring(0, 1) + "k";
		else if (L > 6)
			s = strSize.substring(0, L - 6) + "." + strSize.substring(6, 7) + "M";
		else if (L == 4)
			s = strSize.substring(0, 1) + "." + strSize.substring(1, 2) + "k";
		else
			s = strSize.substring(0, L - 3) + "k";
		return (s);
	}

	public static String getFormatFileSize(File f) {
		return formatFileSize(FileSizeHolder.getFileSizes(f));
	}

	public static long getFileSizes(File f) {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			s = f.length();
		}
		return s;
	}

}
