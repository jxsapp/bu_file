package org.bu.file.web;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ftpserver.ftplet.FtpException;
import org.bu.core.log.BuLog;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.misc.FileHolder;
import org.bu.file.model.BuMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bu.file.ftp.server.BuFtpServer;

/**
 * 页面跳转拦截器
 * 
 * @author Jiang XuSheng
 */
@Controller
@Scope("prototype")
@RequestMapping("/bu_test")
public class BuTestController extends ControllerSupport {

	private BuLog buLog = BuLog.getLogger(BuTestController.class);

	@Autowired
	private BuFtpServer buFtpServer;

	@RequestMapping(value = "/ftp/start", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String ftpStart(HttpServletRequest request, HttpServletResponse response) {
		try {
			buFtpServer.init(null);
		} catch (FtpException e) {
			buLog.error("FtpException", e);
		}
		try {
			success(response, "nihao");
		} catch (Exception e) {

		}
		return "starting";
	}

	@RequestMapping(value = "/ftp/stop", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String ftpStop(HttpServletRequest request, HttpServletResponse response) {
		buFtpServer.stop();
		return "ftp stop";
	}

	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst getCallback(HttpServletRequest request, HttpServletResponse response, @RequestParam("callback") String callback) {// callback
		final String name = request.getParameter("name");
		return getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				if (!StringUtils.isEmpty(name)) {
					return name;
				}
				return "hello world";
			}
		});
	}

	static final BuLog logger = BuLog.getLogger(BuTestController.class);

	private static byte[] rst = "i'm a test data...".getBytes();

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuMenuDao BuMenuDao;

	private Random random = new Random();

	@RequestMapping(value = "/scan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String scan(HttpServletRequest request, HttpServletResponse response) {

		List<BuArea> areas = buAreaDao.findAll();

		List<BuMenu> BuMenus = BuMenuDao.findAll();

		for (BuMenu bumenu : BuMenus) {
			for (BuArea area : areas) {
				File dir = new File(bumenu.getBasePath(), bumenu.getMenuId());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File suDir = new File(dir, area.getCode());
				if (!suDir.exists()) {
					suDir.mkdirs();
				}

				int size = random.nextInt(100);
				if (size < 1) {
					size = 1;
				}
				for (int subIndex = 0; subIndex < size; subIndex++) {
					File file = new File(suDir, subIndex + ".txt");
					FileHolder.saveFile(file, rst);
				}
			}
		}

		return "scan..ing";
	}
}
