package org.bu.file.web;

import javax.servlet.http.HttpServletResponse;

import org.bu.file.dao.BuMenuTypeDao;
import org.bu.file.log.BuLog;
import org.bu.file.model.BuMenuType;
import org.bu.file.model.BuStoreFile;
import org.bu.file.scan.BuScanHolder;
import org.bu.file.scan.BuScanListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转拦截器
 * 
 * @author Jiang XuSheng
 */
@Controller
@RequestMapping("/test")
public class BuTestController extends BasicController {


	private static final BuLog logger = BuLog.getLogger(BuTestController.class);
	
	@Autowired
	private BuMenuTypeDao buMenuTypeDao;
	
	
	@RequestMapping(value = "/scan", method = RequestMethod.GET)
	public void scan(HttpServletResponse response) {
		BuMenuType menuType = buMenuTypeDao.getAll().get(0);
			new BuScanHolder().scan(new BuScanListener() {
				
				@Override
				public void onScaned(BuStoreFile storeFile, BuMenuType menutype) {
					logger.error(storeFile.toJson());
				}
			}, menuType);
	}
}
