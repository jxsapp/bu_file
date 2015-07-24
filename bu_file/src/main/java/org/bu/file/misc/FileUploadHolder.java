package org.bu.file.misc;

import java.io.IOException;

import org.bu.file.model.BuFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadHolder {

	/**
	 * 描述 : <将文件保存到指定路径>. <br>
	 * <p>
	 * 
	 * @param multifile
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public void saveFileToDisc(MultipartFile multifile, String path) throws IOException;

	public void saveFileToDisc(byte[] bytes, String path, String fid) throws IOException;

	public BuFile getFileFromDisc(String rootPath, String type, String path);
}