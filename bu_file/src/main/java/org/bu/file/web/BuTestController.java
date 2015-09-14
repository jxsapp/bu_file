package org.bu.file.web;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.log.BuLog;
import org.bu.core.misc.BuRst;
import org.bu.core.pact.ErrorcodeException;
import org.bu.core.web.ControllerSupport;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.misc.FileHolder;
import org.bu.file.model.BuMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转拦截器
 * 
 * @author Jiang XuSheng
 */
@Controller
@Scope("prototype")
@RequestMapping("/bu_test")
public class BuTestController extends ControllerSupport {

	@RequestMapping(value = "/callback", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	void getCallback(HttpServletRequest request, HttpServletResponse response, @RequestParam("callback") String callback) {// callback
		crossDomainCallback(request, response, getBuRst(request, response, authService, new BuRstObject() {

			@Override
			public Object getObject(BuRst buRst) throws ErrorcodeException {
				return "hello world";
			}
		}));
	}

	static final BuLog logger = BuLog.getLogger(BuTestController.class);

	private static byte[] rst = "i'm a test data...".getBytes();

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuMenuDao BuMenuDao;

	private Random random = new Random();

	@RequestMapping(value = "/scan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String scan(HttpServletRequest request, HttpServletResponse response) {

		List<BuArea> areas = buAreaDao.findAll();

		List<BuMenu> BuMenus = BuMenuDao.findAll();

		for (BuMenu BuMenu : BuMenus) {
			for (BuArea area : areas) {
				File dir = new File(BuMenu.getBasePath(), BuMenu.getMenuId());
				if (!dir.exists()) {
					dir.mkdirs();
				}
				File suDir = new File(dir, area.getCode());
				if (!suDir.exists()) {
					suDir.mkdirs();
				}

				int size = random.nextInt(100);
				if (size < 1) {
					size = 1;
				}
				for (int subIndex = 0; subIndex < size; subIndex++) {
					File file = new File(suDir, subIndex + ".txt");
					FileHolder.saveFile(file, rst);
				}
			}
		}

		return "scan..ing";
	}
}
