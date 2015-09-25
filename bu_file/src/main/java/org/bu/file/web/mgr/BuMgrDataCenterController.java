package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuJSON;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMgrDataCenterDao;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.model.BuMgrDataCenter;
import org.bu.file.web.mgr.pact.BuCliMonitorConnectPact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mgr/config/data_center")
public class BuMgrDataCenterController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMgrDataCenterController.class);

	@Autowired
	private BuMgrDataCenterDao buMgrDataCenterDao;

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
	@RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
	public void getMenus(HttpServletRequest request, HttpServletResponse response) {
		crossDomainCallback(request, response, getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				BuMgrDataCenter dataCenter = null;
				java.util.List<BuMgrDataCenter> dataCenters = buMgrDataCenterDao.findAll();
				if (null != dataCenters && dataCenters.size() > 0) {
					dataCenter = dataCenters.get(0);
				}
				return dataCenter;
			}
		}));
	}

	boolean success = false;

	@RequestMapping(value = "/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createMenu(HttpServletRequest request, HttpServletResponse response,//
			@RequestBody final BuMgrDataCenter dataCenter) {

		new BuCliMonitorConnectPact(jsonHttp, new BuHttpListener() {

			@Override
			public void onSuccess(BuJSON json) {
				success = true;
			}

			@Override
			public void onFailuire(int status) {
				success = false;
			}
		}).connect(getClientUri(dataCenter.getCenterIp()));

		BuRst buRst = BuRst.getSuccess();
		if (success) {
			buRst = getBuRst(request, response, authService, new BuRstObject() {

				@Override
				public Object getObject(BuRst buRst) throws ErrorcodeException {
					buMgrServerDao.saveOrUpdate(dataCenter.buildMgrServer());
					return buMgrDataCenterDao.saveOrUpdate(dataCenter);
				}
			});
		} else {
			buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_CONNET_ERROR));
		}
		crossDomainCallback(request, response, buRst);
	}

}
