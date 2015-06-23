package org.bu.file.web;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.file.dao.BuFileDao;
import org.bu.file.misc.CropImageUtil;
import org.bu.file.misc.DirHolder;
import org.bu.file.misc.FileHolder;
import org.bu.file.misc.FileUploadHolder;
import org.bu.file.misc.PropertiesHolder;
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
@RequestMapping("/chat")
public class BuChatController extends BasicController {
	static final Logger logger = Logger.getLogger(BuChatController.class);

	// 过期时间
	static final long EXPIRE = PropertiesHolder.getLongValue("put.file.expire.seconds");
	static String CHAT_FILE_SAVE_PATH = PropertiesHolder.getValue("chat.file.path");

	@Autowired
	private BuFileDao buFileDao;

	@Autowired
	private FileUploadHolder fileUploadHolder;

	/**
	 * 目前情况下，将文件保存两份，满足前面部分用户获取图片
	 * 
	 * @param response
	 * @param session
	 * @param fid
	 * @param model
	 * @param file
	 */

	@RequestMapping(value = "/put/{fid}/{session}", method = RequestMethod.POST)
	public void put(HttpServletResponse response, @PathVariable("fid") String fid,
			@PathVariable("session") String session, Model model, @RequestParam("file") MultipartFile file) {

		try {

			if (!validate(response, session, fid)) {
				return;
			}
			if (null == fid || "".equals(fid.trim()) || null == file || file.getSize() <= 0) {
				fileNotFound(response, fid);
				return;
			}
			String resourceFileDir = CHAT_FILE_SAVE_PATH;
			String dir = CHAT_FILE_SAVE_PATH;
			if (PUT_SAVE_TO_DISC) {
				if (PUT_FILE_TO_SUB_DIR) {// 如果是保存到子目录中
					dir = DirHolder.subDir(CHAT_FILE_SAVE_PATH, fid, PUT_FILE_TO_SUB_DIR_SIZE);
				}
				resourceFileDir = this.handleImport(model, file, dir, fid);
			}
			BuFile wxlhFile = new BuFile();
			wxlhFile.setFileName(fid);
			wxlhFile.setFileData(file.getBytes());
			buFileDao.put(wxlhFile, EXPIRE);

			if (fid.toLowerCase().endsWith("jpg")) {
				File img_80 = null;

				if (PUT_FILE_TO_SUB_DIR) {// 如果是保存到子目录中
					img_80 = new File(CropImageUtil.genImage80(
							resourceFileDir,
							DirHolder.subDir(CHAT_FILE_SAVE_PATH, CropImageUtil.getMidSuffix(fid),
									PUT_FILE_TO_SUB_DIR_SIZE) + fid));
				} else {
					img_80 = new File(CropImageUtil.genImage80(resourceFileDir, CHAT_FILE_SAVE_PATH + fid));
				}
				wxlhFile = new BuFile();
				wxlhFile.setFileName(img_80.getName());
				wxlhFile.setFileData(FileHolder.readFile(img_80));
				buFileDao.put(wxlhFile, EXPIRE);
			}

			success(response, fid);
		} catch (IOException e) {
			logger.error("IO", e);
			try {
				error(response, fid);
			} catch (IOException e1) {
				logger.debug("上传文件失败");
			}
		}

	}

	@RequestMapping(value = "/get/{fid}/{session}", method = { RequestMethod.GET, RequestMethod.POST })
	public void get(HttpServletResponse response, @PathVariable("fid") String fid,
			@PathVariable("session") String session) {
		try {
			if (!validate(response, session, fid)) {
				return;
			}
			if (null == fid || "".equals(fid.trim())) {
				fileNotFound(response, fid);
				return;
			}
			BuFile buFile = buFileDao.get(fid);
			if (null == buFile && GET_FILE_RIDIS_NOT_FOUNT_GET_FROM_DISC) {

				if (PUT_FILE_TO_SUB_DIR) {// 如果是保存到子目录中
					buFile = fileUploadHolder.getFileFromDisc(fid,
							DirHolder.subDir(CHAT_FILE_SAVE_PATH, fid, PUT_FILE_TO_SUB_DIR_SIZE));
				}
				if (null == buFile) {
					buFile = fileUploadHolder.getFileFromDisc(fid, CHAT_FILE_SAVE_PATH);
				}
			}
			if (null != buFile) {
				response.getOutputStream().write(buFile.getFileData());
				response.getOutputStream().flush();
			} else {
				fileNotFound(response, fid);
			}

		} catch (IOException e) {
			try {
				fileNotFound(response, fid);
			} catch (IOException e1) {
				logger.debug("获取文件失败，内部错误");
			}
		}
	}

	private String handleImport(Model model, MultipartFile multifile, String savePath, String fid) throws IOException {
		fileUploadHolder.saveFileToDisc(multifile, savePath, fid);
		return savePath + fid;
	}

}
