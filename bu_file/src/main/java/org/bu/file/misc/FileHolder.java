package org.bu.file.misc;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;

/**
 * @author JiangXusheng
 * @date 2013-6-26 上午10:54:10
 */
public class FileHolder {

	static final String TAG = FileHolder.class.getName();

	public static void copyInputStream(InputStream in, OutputStream out) throws IOException {
		byte[] buffer = new byte[1024];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	public static void copyInputStream(BufferedReader in, BufferedWriter out) throws IOException {
		char[] buffer = new char[1024];
		int len;
		while ((len = in.read(buffer)) >= 0) {
			out.write(buffer, 0, len);
		}
		in.close();
		out.close();
	}

	/**
	 * 保存为一个文件
	 */
	public static boolean saveFile(File file, String s) {
		boolean ret = false;
		BufferedOutputStream stream = null;
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(s.getBytes());
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 保存为一个文件
	 */
	public static boolean saveFile(File file, BufferedReader br) {
		boolean ret = false;
		BufferedWriter output = null;
		try {
			output = new BufferedWriter(new FileWriter(file));
			copyInputStream(br, output);
			ret = true;
		} catch (Exception e) {
			file.delete();
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 保存为一个文件
	 */
	public static boolean saveFile(File file, InputStream is) {
		boolean ret = false;
		BufferedOutputStream stream = null;
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);

			copyInputStream(is, stream);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

			if (is != null) {
				try {
					is.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 保存为一个文件
	 */
	public static boolean saveFile(File dir, String filename, InputStream is) {
		if (!dir.exists())
			dir.mkdirs();

		File file = new File(dir.getPath(), filename);

		if (file.exists()) {
			return true;
		}

		return saveFile(file, is);
	}

	/**
	 * 把字节数组保存为一个文件
	 */
	public static boolean saveFile(File file, byte[] b) {
		return saveFile(file, b, 0, b.length);
	}

	/**
	 * 把字节数组保存为一个文件
	 */
	public static boolean saveFile(File file, byte[] b, int offset, int length) {
		boolean ret = false;
		BufferedOutputStream stream = null;
		try {
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b, offset, length);
			ret = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 把字节数组保存为一个文件
	 */
	public static boolean saveFile(File dir, String filename, byte[] b, int offset, int length) {
		if (!dir.exists())
			dir.mkdirs();

		File file = new File(dir.getPath(), filename);
		return saveFile(file, b, offset, length);
	}

	/**
	 * 把字节数组保存为一个文件
	 */
	public static boolean saveFile(File dir, String filename, byte[] b) {
		return saveFile(dir, filename, b, 0, b.length);
	}

	/**
	 * 读取文件 返回字节数组
	 */
	public static byte[] readFile(File file) {
		try {
			if (!file.exists())
				return null;

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

	/**
	 * 复制文件
	 * 
	 * @param fs
	 *            源文件
	 * @param fd
	 *            目标文件
	 * @return
	 */
	public static boolean copyFile(File fs, File fd) {
		try {
			if (!fs.exists())
				return false;

			if (fd.exists())
				fd.delete();

			FileInputStream fileInputStream = new FileInputStream(fs);
			return saveFile(fd, fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
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
	
	public static void main(String[] args) {
		System.out.println(FormetFileSize(31821226L));
		System.out.println(FormetFileSize(124341120L));
	}

	public static String FormetFileSize(long fileS) {// 转换文件大小

		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static void deleteFile(File file) {
		if (null != file && file.exists()) {
			file.delete();
		}
	}

	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (null != file && file.exists()) {
			file.delete();
		}
	}

	public static boolean isExists(File file) {
		if (null == file || !file.exists()) {
			return false;
		}
		return true;
	}

}
