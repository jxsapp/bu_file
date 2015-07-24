package org.bu.file.dao;

import org.bu.file.model.BuFile;

/**
 * @author jxs
 * 
 */
public interface BuFileDao {
	/**
	 * 要保存的文件对象
	 * 
	 * @param wxlhFile
	 */
	public void put(final BuFile wxlhFile, final long seconds);

	/**
	 * 获取文件
	 * 
	 * @param fileName
	 * @return
	 */
	public BuFile get(String type, String path);

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 * @return
	 */
	public long delete(String fileName);
}
