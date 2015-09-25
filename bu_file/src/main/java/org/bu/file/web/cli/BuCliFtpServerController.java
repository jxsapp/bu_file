package org.bu.file.web.cli;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ftpserver.ftplet.FtpException;
import org.bu.core.log.BuLog;
import org.bu.core.misc.BuRst;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuCliServerDao;
import org.bu.file.model.BuCliServer;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/client/config/ftpserver")
public class BuCliFtpServerController extends ControllerSupport {

	private BuLog buLog = BuLog.getLogger(BuCliFtpServerController.class);

	@Autowired
	private BuCliServerDao buCliServerDao;

	@RequestMapping(value = "/init", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst init(HttpServletRequest request, HttpServletResponse response,//
			@RequestParam("serverPort") int serverPort,// PORT
			@RequestParam("rootPath") String rootPath,// 跟路径
			@RequestParam("username") String username,// 用户名
			@RequestParam("password") String password// 访问密码
	) {
		BuRst buRst = BuRst.getSuccess();
		BuCliServer cliServer = new BuCliServer();
		cliServer.setRootPath(rootPath);
		cliServer.setPassword(password);
		cliServer.setServerPort(serverPort);
		cliServer.setUsername(username);

		buCliServerDao.saveOrUpdate(cliServer);

		if (!buFtpServer.isStop()) {
			buFtpServer.stop();
		}
		try {
			buFtpServer.init(serverPort, rootPath, username, password);
		} catch (Exception e) {
			buLog.error("org.apache.ftpserver.ftplet.FtpException", e);
		}

		return buRst;
	}

	@Autowired
	private BuFtpServer buFtpServer;

	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST })
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

	@RequestMapping(value = "/stop", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String ftpStop(HttpServletRequest request, HttpServletResponse response) {
		buFtpServer.stop();
		return "ftp stop";
	}

}
