package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.model.BuMgrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mgr/config/server")
public class BuMgrServerController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMgrServerController.class);

	@Autowired
	private BuMgrServerDao buMgrServerDao;

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
	public BuRst list(HttpServletRequest request, HttpServletResponse response) {
		return getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				java.util.List<BuMgrServer> BuMenus = buMgrServerDao.findAll();
				buRst.setCount(BuMenus.size());
				return BuMenus;
			}
		});
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BuRst create(HttpServletRequest request, HttpServletResponse response,//
			@RequestBody final BuMgrServer mgrServer) {
		return new BuMgrServerLogic(this, buMgrServerDao, jsonHttp).create(request, response, mgrServer);
	}

	@RequestMapping(value = "/isalive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BuRst isalive(HttpServletRequest request, HttpServletResponse response,//
			@RequestBody final BuMgrServer mgrServer) {
		return new BuMgrServerLogic(this, buMgrServerDao, jsonHttp).create(request, response, mgrServer);
	}

	@RequestMapping(value = "/delete/{serverId}", method = RequestMethod.DELETE)
	public BuRst deleteMenu(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("serverId") final String serverId) {
		return getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				// TODO 此处需要判断是否已经发布了资源，别人有订阅的资源
				buMgrServerDao.delete(serverId);
				return serverId;
			}
		});
	}

}
