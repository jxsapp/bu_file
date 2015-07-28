package org.bu.file.web;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.file.misc.Error;
import org.bu.file.model.BuError;
import org.bu.file.model.BuHardware;
import org.bu.file.model.BuRst;
import org.bu.file.model.BuSubFile;
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
public class BuMonitorController extends BasicController {
	static final Logger logger = Logger.getLogger(BuMonitorController.class);

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
	BuRst hardware(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("secret_key") String secret_key // 分配的KEY
	) {
		BuRst rst = new BuRst(Error.NO_PERMISSIONS, secret_key);
		if (validate(response, secret_key, false)) {
			rst = new BuRst(secret_key);
			rst.setRst(BuHardware.getTest());
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
	BuRst menu(HttpServletRequest request, HttpServletResponse response,//
			@PathVariable("secret_key") String secret_key, // 分配的KEY
			@RequestParam("path") String path) {
		BuError buError = BuError.get(Error.NO_PERMISSIONS, secret_key);
		BuRst rst = new BuRst(secret_key);
		if (validate(response, secret_key, false)) {
			buError = BuError.get(Error.SUCCESS, secret_key);
			File file = new File(path);
			if (!file.exists()) {
				buError = BuError.get(Error.FILE_NOT_FOUND, secret_key);
			}
			if (!file.isDirectory()) {
				buError = BuError.get(Error.PATH_NOT_DIRECTORY, secret_key);
			}
			rst.setRst(BuSubFile.get(file));
		}
		rst.setError(buError);
		return rst;
	}

}
