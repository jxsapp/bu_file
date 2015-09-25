package org.bu.file.quartz;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.apache.camel.CamelContext;
import org.bu.core.log.BuLog;
import org.bu.file.camel.BuRouteBuilder;
import org.bu.file.dao.BuCliCountDao;
import org.bu.file.dao.BuCliPublishDao;
import org.bu.file.dao.BuCliStoreDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.model.BuCliCount;
import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuCliStore;
import org.bu.file.model.BuFileSql;
import org.bu.file.scan.BuScanHolder;
import org.bu.file.scan.BuScanListener;
import org.bu.file.scan.ScanToDBHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FileScanJob {

	private static BuLog buLog = BuLog.getLogger(FileScanJob.class);
	private static boolean scanning = true;
	private static boolean isAddroud = false;

	@Autowired
	private BuCliPublishDao buCliPublishDao;

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuCliStoreDao buCliStoreDao;

	@Autowired
	private BuCliCountDao buCliCountDao;

	@Autowired
	private CamelContext camelContext;

	/*
	 * 用来扫描文件
	 */
	public void work() {
		buLog.info("当前时间:" + new Date().toString());

		// boolean isPersisten = ScanToDBHolder.hasPersistencIng();

		try {
			if (!isAddroud) {
				camelContext.addRoutes(new BuRouteBuilder());
				isAddroud = true;
			}
			camelContext.startAllRoutes();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!scanning) {
			scanning = true;
			List<BuCliPublish> buCliPublishs = buCliPublishDao.findAll();
			List<BuArea> areas = buAreaDao.findAll();
			for (BuCliPublish cliPublish : buCliPublishs) {
				for (BuArea area : areas) {
					File rootFile = new File(cliPublish.buildRootPath(area));
					if (!rootFile.exists()) {
						rootFile.mkdirs();
					}
					BuCountFileSize countFileSize = new BuCountFileSize();
					countFileSize.count(rootFile);
					int size = countFileSize.getSize();// 获取本目录下有文件数变化时在同步

					int stroreSize = buCliStoreDao.count(cliPublish.getSys_id(), area.getCode());

					if (stroreSize == 0 || stroreSize != size) {
						BuScanHolder.getInstance().scanDirs(new BuScanListener() {
							@Override
							public void onScaned(BuCliStore storeFile, BuCliPublish cliPublish) {
								if (!storeFile.isDir()) {
									ScanToDBHolder.saveStoreFile(storeFile);
								}
							}
						}, cliPublish, rootFile);
					}
					// TODO
					BuFileSql buFileSql = buCliStoreDao.calculate(cliPublish.getSys_id(), area.getCode());
					BuCliCount buFileCount = new BuCliCount();
					buFileCount.setAreaCode(area.getCode());
					buFileCount.setCliPublish(cliPublish);
					buFileCount.setCount(buFileSql.getCount());
					buFileCount.setSize(buFileSql.getSize());
					buCliCountDao.saveOrUpdate(buFileCount);
					// BuFileWatch.watch(rootFile.getAbsolutePath());
				}
			}
			scanning = false;
		}
	}

	private class BuCountFileSize {
		int size = 0;

		void count(File... lister) {
			if (null == lister) {
				return;
			}
			for (File root : lister) {
				if (root.isDirectory()) {
					File[] children = root.listFiles();
					if (children != null) {
						count(children);
					}
				} else {
					size++;
					System.out.println(size + "");
				}
			}
		}

		int getSize() {
			return size;
		}
	}
}