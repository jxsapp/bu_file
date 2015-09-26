package org.bu.file.web.cli;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.log.BuLog;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
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

		File file = new File(rootPath);

		BuRst buRst = BuRst.getSuccess();
		if (null == file || !file.exists()) {
			buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_MENU_UNEXISTED));
		}
		if (buRst.isSuccess()) {
			ftpInitStart(serverPort, rootPath, username, password);
		}

		return buRst;
	}

	@Autowired
	private BuFtpServer buFtpServer;

	@RequestMapping(value = "/start", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst ftpStart(HttpServletRequest request, HttpServletResponse response) {
		BuRst buRst = BuRst.getSuccess();
		List<BuCliServer> buCliServers = buCliServerDao.findAll();
		if (null != buCliServers && buCliServers.size() > 0) {
			BuCliServer buCliServer = buCliServers.get(0);
			File file = new File(buCliServer.getRootPath());
			if (null == file || !file.exists()) {
				buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_MENU_UNEXISTED));
			}
			if (buRst.isSuccess()) {
				ftpInitStart(buCliServer.getServerPort(), buCliServer.getRootPath(), buCliServer.getUsername(), buCliServer.getPassword());
			}
		} else {
			buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_CONFIG_ERROR));
		}
		return buRst;
	}

	private void ftpInitStart(int serverPort,// PORT
			String rootPath,// 跟路径
			String username,// 用户名
			String password// 访问密码
	) {
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
	}

	@RequestMapping(value = "/stop", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst ftpStop(HttpServletRequest request, HttpServletResponse response) {
		BuRst buRst = BuRst.getSuccess();
		if (!buFtpServer.isStop()) {
			buFtpServer.stop();
		}
		buRst.setRst(true);
		return buRst;
	}

	@RequestMapping(value = "/isalive", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst ftpTest(HttpServletRequest request, HttpServletResponse response) {
		BuRst buRst = BuRst.getSuccess();
		buRst.setRst(!buFtpServer.isStop());
		return buRst;
	}

}
