package org.bu.file.quartz;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.bu.core.log.BuLog;
import org.bu.file.dao.BuFileCountDao;
import org.bu.file.dao.BuMenuTypeDao;
import org.bu.file.dao.BuStoreFileDao;
import org.bu.file.dic.BuArea;
import org.bu.file.dic.BuAreaDao;
import org.bu.file.model.BuFileCount;
import org.bu.file.model.BuMenuType;
import org.bu.file.model.BuStoreFile;
import org.bu.file.scan.BuScanHolder;
import org.bu.file.scan.BuScanListener;
import org.bu.file.scan.ScanToDBHolder;
import org.springframework.beans.factory.annotation.Autowired;

public class FileScanJob {

	private static BuLog buLog = BuLog.getLogger(FileScanJob.class);
	private static boolean scanned = false;

	@Autowired
	private BuMenuTypeDao buMenuTypeDao;

	@Autowired
	private BuAreaDao buAreaDao;

	@Autowired
	private BuStoreFileDao buStoreFileDao;

	@Autowired
	private BuFileCountDao buFileCountDao;

	public static void setCanScan() {
		scanned = false;
	}

	/*
	 * 用来扫描文件
	 */
	public void work() {
		buLog.info("当前时间:" + new Date().toString());

		if (!ScanToDBHolder.hasPersistencIng() && !scanned) {
			scanned = true;
			List<BuMenuType> buMenuTypes = buMenuTypeDao.getAll();
			List<BuArea> areas = buAreaDao.findAll();
			for (BuMenuType buMenuType : buMenuTypes) {
				for (BuArea area : areas) {
					File rootFile = new File(buMenuType.buildrRootPath(area));
					if (!rootFile.exists()) {
						rootFile.mkdirs();
					}
					BuCountFileSize countFileSize = new BuCountFileSize();
					countFileSize.count(rootFile);
					int size = countFileSize.getSize();
					if (buStoreFileDao.count(buMenuType.getMenuId(), area.getCode()) != size) {
						BuScanHolder.getInstance().scan(new BuScanListener() {
							@Override
							public void onScaned(BuStoreFile storeFile, BuMenuType menutype) {
								if (!storeFile.isDir()) {
									ScanToDBHolder.sendUdpDataPack(storeFile);
								}
							}
						}, buMenuType, rootFile);
					}
					// TODO
					int count = buStoreFileDao.count(buMenuType.getMenuId(), area.getCode());
					BuFileCount buFileCount = new BuFileCount();
					buFileCount.setAreaCode(area.getCode());
					buFileCount.setMenuTypeCode(buMenuType.getMenuId());
					buFileCount.setCount(count);
					buFileCountDao.saveOrUpdate(buFileCount);
//					BuFileWatch.watch(rootFile.getAbsolutePath());
				}
			}
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