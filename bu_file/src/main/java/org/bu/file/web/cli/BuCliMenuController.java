package org.bu.file.web.cli;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuCliPublishDao;
import org.bu.file.model.BuCliPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/client/config/menu")
public class BuCliMenuController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuCliMenuController.class);

	@Autowired
	private BuCliPublishDao buCliPublishDao;

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
				java.util.List<BuCliPublish> rsts = buCliPublishDao.findAll();
				buRst.setCount(rsts.size());
				return rsts;
			}
		});
	}

	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public BuRst createMenu(HttpServletRequest request, HttpServletResponse response,//
			@RequestParam("path") String path, @RequestParam("desc") String desc) {

		final BuCliPublish cliPublish = new BuCliPublish();
		cliPublish.setPath(path);
		cliPublish.setDesc(desc);

		File publishFile = new File(path);

		if (null == publishFile || !publishFile.exists() || !publishFile.isDirectory()) {
			return BuRst.get(new ErrorcodeException(ErrorCode.CLINET_PUBLISH_MENU_EXISTED));
		}
		return getBuRst(request, response, authService, new BuRstObject() {
			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				return buCliPublishDao.saveOrUpdate(cliPublish);
			}
		});
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) {

	}

}
