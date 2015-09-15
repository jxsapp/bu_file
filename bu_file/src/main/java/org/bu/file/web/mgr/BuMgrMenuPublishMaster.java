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
import org.bu.core.web.ControllerMaster;
import org.bu.core.web.ControllerSupport;
import org.bu.core.web.ControllerSupport.BuRstObject;
import org.bu.file.dao.BuMgrPublishDao;
import org.bu.file.dao.BuMgrServerDao;
import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuMgrPublish;
import org.bu.file.model.BuMgrServer;
import org.bu.file.web.mgr.pact.BuCliMenuPublishPact;

public interface BuMgrMenuPublishMaster extends ControllerMaster {

	public class BuMgrMenuPublishLogic extends ControllerLogic {

		private BuMgrServerDao buMgrServerDao;

		private BuMgrPublishDao buMgrPublishDao;

		private JsonHttp jsonHttp;

		public BuMgrMenuPublishLogic(ControllerSupport support, BuMgrServerDao buMgrServerDao, BuMgrPublishDao buMgrPublishDao, JsonHttp jsonHttp) {
			super(support);
			this.buMgrPublishDao = buMgrPublishDao;
			this.buMgrServerDao = buMgrServerDao;
			this.jsonHttp = jsonHttp;
		}

		private BuError buError = new BuError("");

		public BuRst publish(HttpServletRequest request, HttpServletResponse response,//
				String server_id,// 机器ID
				final BuCliPublish cliPublish) {
			BuRst buRst = BuRst.getSuccess();
			final BuMgrServer mgrServer = buMgrServerDao.findOne(server_id);
			if (null == mgrServer) {
				buRst = BuRst.get(new ErrorcodeException(ErrorCode.CLINET_SERVER_EXISTED));
			} else {

				new BuCliMenuPublishPact(jsonHttp, new BuHttpListener() {
					@Override
					public void onSuccess(BuJSON json) {
						BuJSON rst = new BuJSON(json.getJSONObject("buRst"));
						buError = BuError.getObj(rst.getJSONObject("error"));
					}

					@Override
					public void onFailuire(int status) {
						buError = BuError.get(new ErrorcodeException(status));
					}
				}).publish(support.getClientUri(mgrServer.getServerIp()), cliPublish);

				if (buError.isSuccess()) {
					buRst = support.getBuRst(request, response, authService, new BuRstObject() {
						@Override
						public Object getObject(BuRst buRst) throws ErrorcodeException {
							return buMgrPublishDao.saveOrUpdate(BuMgrPublish.buildPublish(cliPublish, mgrServer));
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
