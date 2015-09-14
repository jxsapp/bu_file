package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.model.BuMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mgr/config/menu")
public class BuMgrMenuController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMgrMenuController.class);

	@Autowired
	private BuMenuDao BuMenuDao;

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
				java.util.List<BuMenu> BuMenus = BuMenuDao.findAll();
				buRst.setCount(BuMenus.size());
				return BuMenus;
			}
		});
	}

	@RequestMapping(value = "/public", method = RequestMethod.POST)
	public void createMenu(HttpServletRequest request, HttpServletResponse response,//
			@RequestBody BuMenu buType) {

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) {

	}

}
