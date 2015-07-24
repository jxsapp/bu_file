package org.bu.file.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bu.file.dao.BuFileDao;
import org.bu.file.misc.DirHolder;
import org.bu.file.misc.FileUploadHolder;
import org.bu.file.model.BuFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Scope("prototype")
@RequestMapping("/share")
public class BuFileController extends BasicController {
	static final Logger logger = Logger.getLogger(BuFileController.class);

	@Autowired
	private BuFileDao buFileDao;

	@Autowired
	private FileUploadHolder fileUploadHolder;

	/**
	 * 目前情况下，将文件保存两份，满足前面部分用户获取图片
	 * 
	 * @param response
	 * @param secret_key
	 * @param type
	 * @param model
	 * @param file
	 */

	@RequestMapping(value = "/put/{secret_key}/{type}", method = RequestMethod.POST)
	public void put(HttpServletResponse response,//
			@PathVariable("secret_key") String secret_key, // 分配的KEY
			@PathVariable("type") String type,// 路径类型
			@RequestParam String path,// 存储路径
			Model model, @RequestParam("file") MultipartFile file) {

		try {

			if (!validate(response, secret_key, type)) {
				return;
			}
			if (null == type || "".equals(type.trim()) || null == file || file.getSize() <= 0) {
				fileNotFound(response, path);
				return;
			}
			String dir = DirHolder.getDir(ROOT_PATH, type, path);
			this.handleImport(model, file, dir, type);
			BuFile wxlhFile = new BuFile(type, path);
			wxlhFile.setFileData(file.getBytes());
			buFileDao.put(wxlhFile, EXPIRE);
			success(response, type);
		} catch (IOException e) {
			logger.error("IO", e);
			try {
				error(response, type);
			} catch (IOException e1) {
				logger.debug("上传文件失败");
			}
		}

	}

	@RequestMapping(value = "/get/{secret_key}/{type}", method = { RequestMethod.GET, RequestMethod.POST })
	public void get(HttpServletRequest request,//
			HttpServletResponse response,//
			@PathVariable("type") String type,//
			@PathVariable("secret_key") String secret_key,//
			@RequestParam String path// 存储路径
	) {
		download(response, type, path, secret_key);
	}

	private void download(HttpServletResponse response, String type, String path, String secret_key) {
		try {
			if (!validate(response, secret_key, type)) {
				return;
			}
			if (StringUtils.isEmpty(type) || StringUtils.isEmpty(path)) {
				fileNotFound(response, type);
				return;
			}
			BuFile buFile = buFileDao.get(type, path);

			if (null == buFile) {
				buFile = fileUploadHolder.getFileFromDisc(ROOT_PATH, type, path);
			}

			if (null != buFile) {
				// response.setContentType("application/x-msdownload");
				response.setContentType("application/octet-stream");
				String name = path.substring(path.lastIndexOf("/"));
				name = name.replaceAll("/", "");
				name = new String(name.getBytes("UTF-8"), "ISO_8859_1");
				response.addHeader("Content-Disposition", "attachment;filename=" + name);
				response.getOutputStream().write(buFile.getFileData());
				response.getOutputStream().flush();
			} else {
				fileNotFound(response, path);
			}

		} catch (IOException e) {
			try {
				fileNotFound(response, path);
			} catch (IOException e1) {
				logger.debug("获取文件失败，内部错误");
			}
		}
	}

	private String handleImport(Model model, MultipartFile multifile, String savePath, String type) throws IOException {
		fileUploadHolder.saveFileToDisc(multifile, savePath);
		return savePath + type;
	}

}
