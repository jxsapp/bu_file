package org.bu.file.scan;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bu.core.log.BuLog;
import org.bu.file.dao.BuMenuDao;
import org.bu.file.dao.BuStoreFileDao;
import org.bu.file.misc.SpringContextUtil;
import org.bu.file.model.BuMenu;
import org.bu.file.model.BuStoreFile;
import org.springframework.beans.factory.annotation.Autowired;

public class ScanToDBWorker extends Thread {

	private static BuLog buLog = BuLog.getLogger(ScanToDBWorker.class);
	private Queue<BuStoreFile> sendQueue;

	@Autowired
	private BuStoreFileDao buStoreFileDao;

	@Autowired
	private BuMenuDao BuMenuDao;

	protected ScanToDBWorker() {
		sendQueue = new ConcurrentLinkedQueue<BuStoreFile>();
	}

	public void addRequestQueue(BuStoreFile dataPack) {
		sendQueue.add(dataPack);
		wakeUp();
	}

	public synchronized boolean isNotEmptyQueue() {
		return sendQueue.size() > 0;
	}

	protected synchronized void wakeUp() {
		notify();
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				while (isNotEmptyQueue()) {
					BuStoreFile dataPack = sendQueue.poll();// 获取但不移除此队列的头；如果此队列为空，则返回

					if (null == buStoreFileDao) {
						buLog.error("i'm null -- buStoreFileDao");
						buStoreFileDao = (BuStoreFileDao) SpringContextUtil.getBean("buStoreFileDao");
					}

					if (null == BuMenuDao) {
						BuMenuDao = (BuMenuDao) SpringContextUtil.getBean("BuMenuDao");
					}

					BuMenu BuMenu = BuMenuDao.getMenuType(dataPack.getPrefix());
					File file = new File(BuMenu.buildRootPath(), dataPack.getPath());
					File scanParent = new File(file.getParent().replace(BuMenu.buildRootPath(), BuMenu.buildScanRootPath()));
					if (!scanParent.exists()) {
						scanParent.mkdirs();
					}
					File distFile = new File(scanParent, file.getName());
					boolean renamed = file.renameTo(distFile);// 移动到扫描文件夹下去
					if (renamed) {
						buLog.info("Move " + file.getAbsolutePath() + " To " + distFile.getAbsolutePath());
					}
					buStoreFileDao.saveOrUpdate(dataPack);

				}
				wait();
			} catch (Exception e) {
				buLog.error("Exception", e);
			}
		}
	}
}
