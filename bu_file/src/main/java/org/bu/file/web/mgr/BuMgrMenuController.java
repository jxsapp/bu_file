package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMgrPublishDao;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuMgrPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mgr/config/menu")
public class BuMgrMenuController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMgrMenuController.class);

	@Autowired
	private BuMgrServerDao buMgrServerDao;

	@Autowired
	private BuMgrPublishDao buMgrPublishDao;

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
	public void getMenus(HttpServletRequest request, HttpServletResponse response) {

		crossDomainCallback(request, response, getBuRst(request, response, authService, new BuRstObject() {
			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				java.util.List<BuMgrPublish> rsts = buMgrPublishDao.findAll();
				buRst.setCount(rsts.size());
				return rsts;
			}
		}));

	}

	@RequestMapping(value = "/create/{server_id}", method = RequestMethod.POST)
	public void createMenu(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("server_id") String server_id,// 机器ID
			@RequestBody BuCliPublish cliPublish) {
		crossDomainCallback(request, response, //
				new BuMgrMenuPublishMaster.BuMgrMenuPublishLogic(this, buMgrServerDao, buMgrPublishDao, jsonHttp).publish(request, response, server_id, cliPublish));
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public void deleteMenu(HttpServletRequest request, HttpServletResponse response) {

	}

}
