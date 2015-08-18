package org.bu.file.misc;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

@SuppressWarnings("restriction")
public class CropImageUtil {
	//
	// 小头像: 54 * 54 _min
	// 中头像： 80 * 80 _mid.后缀名
	//
	public static void createNewFile(String _fileName, int _width, int _height, String _newFileName) throws IOException {
		Image src = javax.imageio.ImageIO.read(new File(_fileName)); // 构造Image对象
		Color fillColor = new Color(1, 1, 0, 0.0f);
		int old_w = src.getWidth(null); // 得到源图宽
		int old_h = src.getHeight(null);
		int new_w = 0;
		int new_h = 0; // 得到源图长

		double w2 = (old_w * 1.00) / (_width * 1.00);
		double h2 = (old_h * 1.00) / (_height * 1.00);

		// 图片跟据长宽留白，成一个正方形图。
		BufferedImage oldpic;
		if (old_w > old_h) {
			oldpic = new BufferedImage(old_w, old_w, BufferedImage.TYPE_INT_RGB);
		} else {
			if (old_w < old_h) {
				oldpic = new BufferedImage(old_h, old_h, BufferedImage.TYPE_INT_RGB);
			} else {
				oldpic = new BufferedImage(old_w, old_h, BufferedImage.TYPE_INT_RGB);
			}
		}

		Graphics2D g = oldpic.createGraphics();
		// g.setColor(Color.white);
		g.setColor(fillColor);

		if (old_w > old_h) {
			g.fillRect(0, 0, old_w, old_w);
			g.drawImage(src, 0, (old_w - old_h) / 2, old_w, old_h, fillColor, null);
		} else {
			if (old_w < old_h) {
				g.fillRect(0, 0, old_h, old_h);
				g.drawImage(src, (old_h - old_w) / 2, 0, old_w, old_h, fillColor, null);
			} else {
				// g.fillRect(0,0,old_h,old_h);
				g.drawImage(src.getScaledInstance(old_w, old_h, Image.SCALE_SMOOTH), 0, 0, null);
			}
		}

		g.dispose();
		src = oldpic;
		// 图片调整为方形结束
		if (old_w > _width)
			new_w = (int) Math.round(old_w / w2);
		else
			new_w = old_w;

		if (old_h > _height)
			new_h = (int) Math.round(old_h / h2);// 计算新图长宽
		else
			new_h = old_h;

		BufferedImage tag = new BufferedImage(new_w, new_h, BufferedImage.TYPE_INT_RGB);
		// tag.getGraphics().drawImage(src,0,0,new_w,new_h,null); //绘制缩小后的图
		tag.getGraphics().drawImage(src.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0, 0, null);
		FileOutputStream newimage = new FileOutputStream(new File(_newFileName)); // 输出到文件流
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		JPEGEncodeParam jep = JPEGCodec.getDefaultJPEGEncodeParam(tag);
		/* 压缩质量 */
		jep.setQuality((float) 0.7, true);
		encoder.encode(tag, jep);
		// encoder.encode(tag); //近JPEG编码
		newimage.close();
	}

	public static String genImage54(String rescoure, String targetFileName) throws IOException {
		targetFileName = getMinSuffix(targetFileName);
		return CropImageHolder.genImage54(rescoure, targetFileName);
	}

	public static String genImage80(String rescoure, String targetFileName) throws IOException {
		targetFileName = getMidSuffix(targetFileName);
		return CropImageHolder.genImage80(rescoure, targetFileName);
	}

	public static String getMinSuffix(String fileName) {
		return getSuffix(fileName, "min");
	}

	public static String getMidSuffix(String fileName) {
		return getSuffix(fileName, "mid");
	}

	public static String getSuffix(String fileName, String split) {
		int i = fileName.lastIndexOf('.');
		String name = "";
		String suffix = "";
		if ((i > -1) && (i < (fileName.length() - 1))) {
			name = fileName.substring(0, i);
			suffix = fileName.substring(i);
		}
		return name + "_" + split + suffix;
	}

}
