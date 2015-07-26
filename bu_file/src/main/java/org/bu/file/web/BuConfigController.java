package org.bu.file.web;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.bu.file.model.BuMenu;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Scope("prototype")
@RequestMapping("/config")
public class BuConfigController extends BasicController {
	static final Logger logger = Logger.getLogger(BuConfigController.class);

	// @Autowired
	// private BundleTag

	/**
	 * 目前情况下，将文件保存两份，满足前面部分用户获取图片
	 * 
	 * @param response
	 * @param secret_key
	 * @param type
	 * @param model
	 * @param file
	 */

	@RequestMapping(value = "/menu_type/{secret_key}", method = RequestMethod.POST)
	public void createType(HttpServletResponse response,//
			@PathVariable("secret_key") String secret_key, // 分配的KEY
			@PathVariable("type") String type,// 路径类型
			@RequestParam BuMenu model) {
		System.out.println(model.toString());
	}

}
