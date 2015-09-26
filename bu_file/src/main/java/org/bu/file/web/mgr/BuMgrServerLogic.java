package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.misc.BuError;
import org.bu.core.misc.BuJSON;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerLogic;
import org.bu.core.web.ControllerSupport;
import org.bu.core.web.ControllerSupport.BuRstObject;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.model.BuCliServer;
import org.bu.file.model.BuMgrServer;
import org.bu.file.web.mgr.pact.BuCliServerInitPact;

public class BuMgrServerLogic extends ControllerLogic {

	private BuMgrServerDao buMgrServerDao;

	private JsonHttp jsonHttp;
	private BuError buError = new BuError("");

	public BuMgrServerLogic(ControllerSupport support, BuMgrServerDao buMgrServerDao, JsonHttp jsonHttp) {
		super(support);
		this.buMgrServerDao = buMgrServerDao;
		this.jsonHttp = jsonHttp;
	}

	public BuRst create(HttpServletRequest request, HttpServletResponse response,//
			final BuMgrServer mgrServer) {
		BuRst buRst = BuRst.getSuccess();

		if (!mgrServer.validateParams()) {
			buRst = BuRst.get(new ErrorcodeException(ErrorCode.PARAM_ERROR));
		}

		BuMgrServer centerServer = buMgrServerDao.getDataCenter();
		if (null == centerServer) {
			buRst = BuRst.get(new ErrorcodeException(ErrorCode.NOT_HAS_DATA_CENTER));
		}
		if (buRst.isSuccess()) {

			BuCliServer cliServer = mgrServer.buildCliServer();
			mgrServer.setDataCenter(mgrServer.buildDataCenter(centerServer));

			new BuCliServerInitPact(jsonHttp, new BuHttpListener() {
				@Override
				public void onSuccess(BuJSON json) {
					BuJSON rst = new BuJSON(json.getJSONObject("buRst"));
					buError = BuError.getObj(rst.getJSONObject("error"));
				}

				@Override
				public void onFailuire(int status) {
					buError = BuError.get(new ErrorcodeException(status));
				}
			}).init(support.getClientUri(mgrServer.getServerIp()), cliServer);

			if (buError.isSuccess()) {
				buRst = support.getBuRst(request, response, authService, new BuRstObject() {
					@Override
					public Object getObject(BuRst buRst) throws ErrorcodeException {
						return buMgrServerDao.saveOrUpdate(mgrServer);
					}
				});
			} else {
				buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_CONNET_ERROR));
			}
		}
		return buRst;
	}

}
