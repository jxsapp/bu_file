package org.bu.file.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.file.dao.BuFileCountDao;
import org.bu.file.misc.Error;
import org.bu.file.model.BuRst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/statistics")
public class BuFileCountController extends BasicController {
	static final Logger logger = Logger.getLogger(BuFileCountController.class);

	@Autowired
	private BuFileCountDao buFileCountDao;

	// @Autowired
	// private BundleTag

	/**
	 * @监控硬件信息
	 * @param request
	 * @param response
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value = "/file_count/{secret_key}/{menuId}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst fileCount(HttpServletRequest request, HttpServletResponse response, //
			@PathVariable("secret_key") String secret_key, // 分配的KEY
			@PathVariable("menuId") String menuId // 监控的目录ID
	) {
		BuRst rst = new BuRst(Error.NO_PERMISSIONS, menuId);
		if (validate(response, secret_key, false)) {
			rst = new BuRst(menuId);
			rst.setRst(buFileCountDao.findAll(menuId));
		}
		return rst;
	}

}
