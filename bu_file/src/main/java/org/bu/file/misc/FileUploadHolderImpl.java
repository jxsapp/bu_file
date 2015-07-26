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
	public void saveFileToDisc(MultipartFile multifile, String path) throws IOException {
		// 创建目录
		File dir = new File(path);
		if (null != dir.getParentFile() && !dir.getParentFile().exists()) {
			dir.getParentFile().mkdirs();
		}
		// 读取文件流并保持在指定路径
		InputStream inputStream = multifile.getInputStream();
		OutputStream outputStream = new FileOutputStream(path);
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
	public BuFile getFileFromDisc(String targetPath, String path) {
		File child = new File(path);
		targetPath = targetPath + "/temp";
		if (child == null || !child.exists()) {
			return null;
		}

		BuFile buFile = new BuFile("", path);
		if (child.isDirectory()) {
			buFile.setZipped(true);
			path = new AntZipHolder(2048).doZip(path, targetPath);
			child = new File(path);
		}
		buFile.setFileData(readFile(child));
		if (buFile.isZipped()) {
			boolean rst = child.delete();
			System.out.println(rst);
			child.deleteOnExit();
		}
		return buFile;
	}

	@Override
	public BuFile getFileFromDisc(String rootPath, String type, String path) {

		File child = new File(rootPath, BuFile.getKey(type, path));
		if (!child.exists()) {
			return null;
		}
		BuFile buFile = new BuFile(type, path);
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
