package org.bu.file.web.cli;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.model.BuStatus;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuCliSubscribeDao;
import org.bu.file.model.BuCliSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client/config/sub_menu")
public class BuCliSubMenuController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuCliSubMenuController.class);

	@Autowired
	private BuCliSubscribeDao buCliSubscribeDao;

	/**
	 * 获取已经发布的资源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public BuRst getMenus(HttpServletRequest request, HttpServletResponse response) {
		return getBuRst(request, response, authService, new BuRstObject() {
			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				java.util.List<BuCliSubscribe> rsts = buCliSubscribeDao.findAll();
				buRst.setCount(rsts.size());
				return rsts;
			}
		});
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST)
	public BuRst createMenu(HttpServletRequest request, HttpServletResponse response,//
			@RequestParam("savePath") String savePath,//
			@RequestParam("pubServer") String pubServer,//
			@RequestParam("publishId") String publishId//
	) {

		final BuCliSubscribe cliPublish = new BuCliSubscribe();
		cliPublish.setPubServer(pubServer);
		cliPublish.setPublishId(publishId);

		File publishFile = new File(savePath);

		if (null == publishFile || !publishFile.exists() || !publishFile.isDirectory()) {
			return BuRst.get(new ErrorcodeException(ErrorCode.CLINET_SUBSCRIBE_MENU_EXISTED));
		}
		return getBuRst(request, response, authService, new BuRstObject() {
			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				return buCliSubscribeDao.saveOrUpdate(cliPublish);
			}
		});
	}

	@RequestMapping(value = "/option", method = RequestMethod.POST)
	public BuRst cancelMenu(HttpServletRequest request, HttpServletResponse response,//
			@RequestParam("pubServer") String pubServer,//
			@RequestParam("publishId") String publishId,//
			@RequestParam("status") final int status// 目录ID

	) {
		BuStatus buStatus = BuStatus.buildStatus(status);
		if (buStatus.isInvalid()) {
			return BuRst.get(new ErrorcodeException(ErrorCode.PARAM_ERROR));
		}
		final BuCliSubscribe buCliSubscribe = buCliSubscribeDao.updateStatus(pubServer, publishId, buStatus);
		BuRst buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_SUBSCRIBE_MENU_EXISTED));
		if (null != buCliSubscribe) {// 如果文件不存在
			buRst = getBuRst(request, response, authService, new BuRstObject() {
				@Override
				public Object getObject(BuRst buRst) throws ErrorcodeException {
					return buCliSubscribe;
				}
			});
		}
		return buRst;
	}
}
