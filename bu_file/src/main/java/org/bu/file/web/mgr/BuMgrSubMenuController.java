package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.model.BuStatus;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.dao.BuMgrSubscribeDao;
import org.bu.file.model.BuMgrSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mgr/config/sub_menu")
public class BuMgrSubMenuController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMgrSubMenuController.class);

	@Autowired
	private BuMgrServerDao buMgrServerDao;

	@Autowired
	private BuMgrSubscribeDao buMgrSubscribeDao;

	@Autowired
	private JsonHttp jsonHttp;

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
				java.util.List<BuMgrSubscribe> rsts = buMgrSubscribeDao.findAll();
				buRst.setCount(rsts.size());
				return rsts;
			}
		});

	}

	@RequestMapping(value = "/create/{server_id}", method = RequestMethod.POST)
	public BuRst createMenu(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("server_id") String server_id,// 机器ID订阅者机器号
			@PathVariable("publish_id") String publish_id// 发布资源ID
	) {
		return //
		new BuMgrSubMenuLogic(this, buMgrServerDao, buMgrSubscribeDao, jsonHttp).subscribe(request, response, server_id, publish_id);
	}

	@RequestMapping(value = "/cancel/{menu_id}", method = RequestMethod.POST)
	public BuRst cancelMenu(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("menu_id") String menu_id// 目录ID
	) {
		return //
		new BuMgrSubMenuLogic(this, buMgrServerDao, buMgrSubscribeDao, jsonHttp).option(request, response, menu_id, BuStatus.CANCELED);
	}

}
