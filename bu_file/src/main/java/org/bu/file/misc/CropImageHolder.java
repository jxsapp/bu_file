package org.bu.file.misc;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 压缩图片 创建图片缩略图
 * 
 * @author slzs
 */
@SuppressWarnings("restriction")
public class CropImageHolder {

	public static String genImage54(String rescoure, String targetFileName) throws IOException {
		return doCompress(rescoure, 54, 54, 1f, targetFileName, true);
	}

	public static String genImage80(String rescoure, String targetFileName) throws IOException {
		return doCompress(rescoure, 80, 80, 1f, targetFileName, true);
	}

	/**
	 * 压缩图片方法
	 * 
	 * @param oldFile
	 *            将要压缩的图片
	 * @param width
	 *            压缩宽
	 * @param height
	 *            压缩高
	 * @param quality
	 *            压缩清晰度 <b>建议为1.0</b>
	 * @param targetFilePath
	 *            压缩图片后,添加的扩展名（在图片后缀名前添加）
	 * @param percentage
	 *            是否等比压缩 若true宽高比率将将自动调整
	 * @return 如果处理正确返回压缩后的文件名 null则参数可能有误
	 */
	public static String doCompress(String oldFile, int width, int height, float quality, String targetFilePath,
			boolean percentage) {
		if (oldFile != null && width > 0 && height > 0) {
			Image srcFile = null;
			try {
				File file = new File(oldFile);
				// 文件不存在
				if (!file.exists()) {
					return null;
				}
				/* 读取图片信息 */
				srcFile = ImageIO.read(file);
				int new_w = width;
				int new_h = height;
				if (percentage) {
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) srcFile.getWidth(null)) / (double) width + 0.1;
					double rate2 = ((double) srcFile.getHeight(null)) / (double) height + 0.1;
					double rate = rate1 > rate2 ? rate1 : rate2;
					new_w = (int) (((double) srcFile.getWidth(null)) / rate);
					new_h = (int) (((double) srcFile.getHeight(null)) / rate);
				}
				/* 宽高设定 */
				BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(srcFile, 0, 0, new_w, new_h, null);
				/* 压缩后的文件名 */

				File targFile = new File(targetFilePath);

				if (null != targFile && !targFile.exists()) {
					new File(targFile.getParent()).mkdirs();
				}
				/* 压缩之后临时存放位置 */
				FileOutputStream out = new FileOutputStream(targetFilePath);
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
				/* 压缩质量 */
				jep.setQuality(quality, true);
				encoder.encode(tag, jep);
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				srcFile.flush();
			}
			return targetFilePath;
		} else {
			return null;
		}
	}
}