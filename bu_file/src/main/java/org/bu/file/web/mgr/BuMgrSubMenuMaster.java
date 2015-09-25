package org.bu.file.web.mgr;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.misc.BuError;
import org.bu.core.misc.BuJSON;
import org.bu.core.misc.BuRst;
import org.bu.core.model.BuStatus;
import org.bu.core.pact.BuHttp.BuHttpListener;
import org.bu.core.pact.ErrorCode;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.pact.JsonHttp;
import org.bu.core.web.ControllerMaster;
import org.bu.core.web.ControllerSupport;
import org.bu.core.web.ControllerSupport.BuRstObject;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.dao.BuMgrSubscribeDao;
import org.bu.file.model.BuCliSubscribe;
import org.bu.file.model.BuMgrServer;
import org.bu.file.model.BuMgrSubscribe;
import org.bu.file.web.mgr.pact.BuCliPubMenuOptionPact;
import org.bu.file.web.mgr.pact.BuCliSubMenuCreatePact;

public interface BuMgrSubMenuMaster extends ControllerMaster {

	public class BuMgrSubMenuLogic extends ControllerLogic {

		private BuMgrServerDao buMgrServerDao;

		private BuMgrSubscribeDao buMgrSubscribeDao;

		private JsonHttp jsonHttp;

		public BuMgrSubMenuLogic(ControllerSupport support, BuMgrServerDao buMgrServerDao, BuMgrSubscribeDao buMgrSubscribeDao, JsonHttp jsonHttp) {
			super(support);
			this.buMgrSubscribeDao = buMgrSubscribeDao;
			this.buMgrServerDao = buMgrServerDao;
			this.jsonHttp = jsonHttp;
		}

		private BuError buError = new BuError("");

		public BuRst subscribe(HttpServletRequest request, HttpServletResponse response,//
				String server_id,// 机器ID
				String publish_id) {
			BuRst buRst = BuRst.getSuccess();
			final BuMgrServer mgrServer = buMgrServerDao.findOne(server_id);
			if (null == mgrServer) {
				buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_SERVER_EXISTED));
			} else {

				final BuCliSubscribe cliSubscribe = new BuCliSubscribe();

				new BuCliSubMenuCreatePact(jsonHttp, new BuHttpListener() {
					@Override
					public void onSuccess(BuJSON json) {
						BuJSON rst = new BuJSON(json.getJSONObject("buRst"));
						buError = BuError.getObj(rst.getJSONObject("error"));
					}

					@Override
					public void onFailuire(int status) {
						buError = BuError.get(new ErrorcodeException(status));
					}
				}).subscribe(support.getClientUri(mgrServer.getServerIp()), cliSubscribe);

				if (buError.isSuccess()) {
					buRst = support.getBuRst(request, response, authService, new BuRstObject() {
						@Override
						public Object getObject(BuRst buRst) throws ErrorcodeException {
							return buMgrSubscribeDao.saveOrUpdate(BuMgrSubscribe.buildSubscribe(cliSubscribe, mgrServer));
						}
					});
				} else {
					buRst = BuRst.get(new ErrorcodeException(buError.getCode()));
				}
			}

			return buRst;
		}

		public BuRst option(HttpServletRequest request, HttpServletResponse response, String menu_id, final BuStatus buStatus) {
			BuRst buRst = BuRst.getSuccess();
			final BuMgrSubscribe buMgrSubscribe = buMgrSubscribeDao.findOne(menu_id);
			if (null == buMgrSubscribe) {
				buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_SERVER_EXISTED));
			} else {
				new BuCliPubMenuOptionPact(jsonHttp, new BuHttpListener() {
					@Override
					public void onSuccess(BuJSON json) {
						BuJSON rst = new BuJSON(json.getJSONObject("buRst"));
						buError = BuError.getObj(rst.getJSONObject("error"));
					}

					@Override
					public void onFailuire(int status) {
						buError = BuError.get(new ErrorcodeException(status));
					}
				}).option(support.getClientUri(buMgrSubscribe.getMgrServer().getServerIp()), "", buStatus);

				if (buError.isSuccess()) {
					buRst = support.getBuRst(request, response, authService, new BuRstObject() {
						@Override
						public Object getObject(BuRst buRst) throws ErrorcodeException {
							buMgrSubscribe.setStatus(buStatus.getStatus());
							return buMgrSubscribeDao.saveOrUpdate(buMgrSubscribe);
						}
					});
				} else {
					buRst = BuRst.get(new ErrorcodeException(buError.getCode()));
				}
			}

			return buRst;
		}
	}

}
