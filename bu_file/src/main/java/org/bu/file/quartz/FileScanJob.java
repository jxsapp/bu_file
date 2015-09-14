package org.bu.file.quartz;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.bu.core.log.BuLog;
import org.bu.file.dao.BuFileCountDao;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.dao.BuStoreFileDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.model.BuFileCount;
import org.bu.file.model.BuMenu;
import org.bu.file.model.BuStoreFile;
import org.bu.file.scan.BuScanHolder;
import org.bu.file.scan.BuScanListener;
import org.bu.file.scan.ScanToDBHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FileScanJob {

	private static BuLog buLog = BuLog.getLogger(FileScanJob.class);
	private static boolean scanning = true;

	@Autowired
	private BuMenuDao BuMenuDao;

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuStoreFileDao buStoreFileDao;

	@Autowired
	private BuFileCountDao buFileCountDao;

	/*
	 * 用来扫描文件
	 */
	public void work() {
		buLog.info("当前时间:" + new Date().toString());

		// boolean isPersisten = ScanToDBHolder.hasPersistencIng();

		if (!scanning) {
			scanning = true;
			List<BuMenu> BuMenus = BuMenuDao.findAll();
			List<BuArea> areas = buAreaDao.findAll();
			for (BuMenu BuMenu : BuMenus) {
				for (BuArea area : areas) {
					File rootFile = new File(BuMenu.buildRootPath(area));
					if (!rootFile.exists()) {
						rootFile.mkdirs();
					}
					BuCountFileSize countFileSize = new BuCountFileSize();
					countFileSize.count(rootFile);
					int size = countFileSize.getSize();// 获取本目录下有文件数变化时在同步

					if (buStoreFileDao.count(BuMenu.getMenuId(), area.getCode()) != size) {
						BuScanHolder.getInstance().scan(new BuScanListener() {
							@Override
							public void onScaned(BuStoreFile storeFile, BuMenu menutype) {
								if (!storeFile.isDir()) {
									ScanToDBHolder.saveStoreFile(storeFile);
								}
							}
						}, BuMenu, rootFile);
					}
					// TODO
					int count = buStoreFileDao.count(BuMenu.getMenuId(), area.getCode());
					BuFileCount buFileCount = new BuFileCount();
					buFileCount.setAreaCode(area.getCode());
					buFileCount.setMenuTypeCode(BuMenu.getMenuId());
					buFileCount.setCount(count);
					buFileCountDao.saveOrUpdate(buFileCount);
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