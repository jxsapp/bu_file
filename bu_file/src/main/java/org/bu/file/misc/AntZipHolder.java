package org.bu.file.misc;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

public class AntZipHolder {
	private ZipFile zipFile;
	private ZipOutputStream zipOut; // 压缩Zip
	private int bufSize; // size of bytes
	private byte[] buf;
	private int readedBytes;

	public AntZipHolder() {
		this(512);
	}

	public AntZipHolder(int bufSize) {
		this.bufSize = bufSize;
		this.buf = new byte[this.bufSize];
	}

	// 压缩文件夹内的文件
	public String doZip(String zipDirectory, String targetDir) {// zipDirectoryPath:需要压缩的文件夹名
		File zipDir;

		zipDir = new File(zipDirectory);
		String zipFileName = zipDir.getName() + ".zip";// 压缩后生成的zip文件名

		try {
			File file = new File(targetDir, zipFileName);
			this.zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
			handleDir(zipDir, this.zipOut);
			this.zipOut.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return targetDir + "/" + zipFileName;
	}

	// 由doZip调用,递归完成目录文件读取
	private void handleDir(File dir, ZipOutputStream zipOut) throws IOException {
		FileInputStream fileIn;
		File[] files;
		files = dir.listFiles();

		if (files.length == 0) {// 如果目录为空,则单独创建之.
			// ZipEntry的isDirectory()方法中,目录以"/"结尾.
			this.zipOut.putNextEntry(new ZipEntry(dir.toString() + "/"));
			this.zipOut.closeEntry();
		} else {// 如果目录不为空,则分别处理目录和文件.
			for (File fileName : files) {
				if (fileName.isDirectory()) {
					handleDir(fileName, this.zipOut);
				} else {
					fileIn = new FileInputStream(fileName);
					this.zipOut.putNextEntry(new ZipEntry(fileName.toString()));
					while ((this.readedBytes = fileIn.read(this.buf)) > 0) {
						this.zipOut.write(this.buf, 0, this.readedBytes);
					}
					this.zipOut.closeEntry();
				}
			}
		}
	}

	// 解压指定zip文件
	public void unZip(String srcZip, String target) {// unZipfileName需要解压的zip文件名
		FileOutputStream fileOut;
		File file;
		InputStream inputStream;
		try {
			this.zipFile = new ZipFile(srcZip);

			for (Enumeration<ZipEntry> entries = this.zipFile.getEntries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				file = new File(target + entry.getName());

				if (entry.isDirectory()) {
					file.mkdirs();
				} else {
					if (!new File(file.getParent()).exists()) {
						new File(file.getParent()).mkdirs();
					}
					// 如果指定文件的目录不存在,则创建之.
					File targetDir = new File(target);
					if (!targetDir.exists()) {
						targetDir.mkdirs();
					}
					inputStream = zipFile.getInputStream(entry);
					fileOut = new FileOutputStream(file);
					while ((this.readedBytes = inputStream.read(this.buf)) > 0) {
						fileOut.write(this.buf, 0, this.readedBytes);
					}
					fileOut.close();
					inputStream.close();
				}
			}
			this.zipFile.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	// 设置缓冲区大小
	public void setBufSize(int bufSize) {
		this.bufSize = bufSize;
	}

	public static void main(String[] args) {
		AntZipHolder zip = new AntZipHolder();
		zip.doZip("/Users/jxs/Desktop/pic/recvs", "/Users/jxs/Desktop/test");
	}

	// 测试AntZip类
	public static void main1(String[] args) throws Exception {
		if (args.length == 2) {
			String name = args[1];
			AntZipHolder zip = new AntZipHolder();

			if (args[0].equals("-zip"))
				zip.doZip(name, "/Users/jxs/Desktop");
			else if (args[0].equals("-unzip"))
				zip.unZip(name, new File(name).getParent());
		} else {
			System.out.println("Usage:");
			System.out.println("压缩:java AntZip -zip directoryName");
			System.out.println("解压:java AntZip -unzip fileName.zip");
			throw new Exception("Arguments error!");
		}
	}
}