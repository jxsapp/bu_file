package org.bu.file.misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bu.file.model.BuFile;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository("fileUploadHolder")
public class FileUploadHolderImpl implements FileUploadHolder {
	/**
	 * 描述 : <将文件保存到指定路径>. <br>
	 * <p>
	 * 
	 * @param multifile
	 * @param path
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void saveFileToDisc(MultipartFile multifile, String path, String fid) throws IOException {
		// 创建目录
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		// 读取文件流并保持在指定路径
		InputStream inputStream = multifile.getInputStream();
		OutputStream outputStream = new FileOutputStream(path + fid);
		byte[] buffer = multifile.getBytes();
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = inputStream.read(buffer)) != -1) {
			bytesum += byteread;
			outputStream.write(buffer, 0, byteread);
			outputStream.flush();
		}
		outputStream.close();
		inputStream.close();

	}

	@Override
	public void saveFileToDisc(byte[] bytes, String path, String fid) throws IOException {
		// 读取文件流并保持在指定路径
		OutputStream outputStream = new FileOutputStream(path + fid);
		outputStream.write(bytes);
		outputStream.flush();
		outputStream.close();
	}

	@Override
	public BuFile getFileFromDisc(String file, String path) {

		File child = new File(path, file);
		if (!child.exists()) {
			return null;
		}
		BuFile buFile = new BuFile();
		buFile.setFileName(file);
		buFile.setFileData(readFile(child));
		return buFile;
	}

	/**
	 * 读取文件 返回字节数组
	 */
	public static byte[] readFile(File file) {
		try {
			byte[] bs = new byte[(int) file.length()];
			FileInputStream fileInputStream = new FileInputStream(file);
			fileInputStream.read(bs);
			fileInputStream.close();
			return (bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
