package org.bu.file.web;

import java.io.File;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.core.misc.BuError;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuFileCountDao;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.misc.Error;
import org.bu.file.model.BuHardware;
import org.bu.file.model.BuMenu;
import org.bu.file.model.BuRst;
import org.bu.file.model.BuSubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("prototype")
@RequestMapping("/monitor")
public class BuMonitorController extends ControllerSupport {
	static final Logger logger = Logger.getLogger(BuMonitorController.class);
	static final Set<String> areaCodes = new java.util.HashSet<String>();
	static final Set<String> menuIds = new java.util.HashSet<String>();

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuMenuDao BuMenuDao;

	@Autowired
	private BuFileCountDao buFileCountDao;

	// @Autowired
	// private BundleTag

	/**
	 * @监控硬件信息
	 * @param request
	 * @param response
	 * @param secret_key
	 * @return
	 */
	@RequestMapping(value = "/hardware/{secret_key}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst hardware(HttpServletRequest request, HttpServletResponse response, //
			@PathVariable("secret_key") String secret_key // 分配的KEY
	) {

		String path = request.getParameter("path");
		BuRst rst = new BuRst(Error.NO_PERMISSIONS, secret_key);
		if (validate(response, secret_key, false)) {
			rst = new BuRst(secret_key);
			rst.setRst(BuHardware.getData(path));
		}
		return rst;
	}

	/**
	 * 监控一个目录
	 * 
	 * @param request
	 * @param response
	 * @param secret_key
	 * @param path
	 * @return
	 */
	@RequestMapping(value = "/menus/{secret_key}", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	BuRst menu(HttpServletRequest request, HttpServletResponse response, //
			@PathVariable("secret_key") String secret_key, // 分配的KEY
			@RequestParam("path") String path) {
		BuError buError = BuError.get(Error.NO_PERMISSIONS, secret_key);
		BuRst rst = new BuRst(secret_key);

		if (areaCodes.isEmpty()) {
			List<BuArea> areas = buAreaDao.findAll();
			for (BuArea area : areas) {
				areaCodes.add(area.getCode());
			}
		}
		if (menuIds.isEmpty()) {
			List<BuMenu> menus = BuMenuDao.findAll();
			for (BuMenu type : menus) {
				menuIds.add(type.getMenuId());
			}
		}

		if (validate(response, secret_key, false)) {
			buError = BuError.get(Error.SUCCESS, secret_key);
			File file = new File(path);
			if (!file.exists()) {
				buError = BuError.get(Error.FILE_NOT_FOUND, secret_key);
			}
			if (!file.isDirectory()) {
				buError = BuError.get(Error.PATH_NOT_DIRECTORY, secret_key);
			}
			rst.setRst(BuSubFile.get(buFileCountDao, file, areaCodes, menuIds));
		}
		rst.setError(buError);
		return rst;
	}

}
