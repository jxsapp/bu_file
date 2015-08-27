package org.bu.file.web;

import java.io.File;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.bu.core.log.BuLog;
import org.bu.file.dao.BuMenuTypeDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.misc.FileHolder;
import org.bu.file.model.BuMenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面跳转拦截器
 * 
 * @author Jiang XuSheng
 */
@Controller
@Scope("prototype")
@RequestMapping("/bu_test")
public class BuTestController extends BasicController {

	static final BuLog logger = BuLog.getLogger(BuTestController.class);

	private static byte[] rst = "i'm a test data...".getBytes();

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuMenuTypeDao buMenuTypeDao;

	private Random random = new Random();

	@RequestMapping(value = "/scan", method = { RequestMethod.GET, RequestMethod.POST })
	public @ResponseBody
	String scan(HttpServletRequest request, HttpServletResponse response) {

		List<BuArea> areas = buAreaDao.findAll();

		List<BuMenuType> buMenuTypes = buMenuTypeDao.getAll();

		for (BuMenuType buMenuType : buMenuTypes) {
			for (BuArea area : areas) {
				File dir = new File(buMenuType.getBasePath(), buMenuType.getMenuId());
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
