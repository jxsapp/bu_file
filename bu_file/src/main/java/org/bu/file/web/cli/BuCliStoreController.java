package org.bu.file.web.cli;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuCliStoreDao;
import org.bu.file.model.BuCliStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/client/config/store")
public class BuCliStoreController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuCliStoreController.class);

	@Autowired
	private BuCliStoreDao buCliStoreDao;

	/**
	 * 获取已经发布的资源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list/{lastTime}", method = { RequestMethod.GET, RequestMethod.POST })
	public BuRst getStores(HttpServletRequest request, HttpServletResponse response, @PathVariable("lastTime") final long lastTime) {

		return getBuRst(request, response, authService, new BuRstObject() {
			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				List<BuCliStore> rsts = buCliStoreDao.buCliStores(lastTime, PAGE_SIZE);
				buRst.setCount(rsts.size());
				return rsts;
			}
		});
	}

}
