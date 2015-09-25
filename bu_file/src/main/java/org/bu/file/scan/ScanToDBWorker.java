package org.bu.file.scan;

import java.io.File;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.bu.core.log.BuLog;
import org.bu.file.dao.BuCliPublishDao;
import org.bu.file.dao.BuCliStoreDao;
import org.bu.file.misc.SpringContextUtil;
import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuCliStore;
import org.springframework.beans.factory.annotation.Autowired;

public class ScanToDBWorker extends Thread {

	private static BuLog buLog = BuLog.getLogger(ScanToDBWorker.class);
	private Queue<BuCliStore> sendQueue;

	@Autowired
	private BuCliStoreDao buCliStoreDao;

	@Autowired
	private BuCliPublishDao buCliPublishDao;

	protected ScanToDBWorker() {
		sendQueue = new ConcurrentLinkedQueue<BuCliStore>();
	}

	public void addRequestQueue(BuCliStore dataPack) {
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
					BuCliStore dataPack = sendQueue.poll();// 获取但不移除此队列的头；如果此队列为空，则返回

					if (null == buCliStoreDao) {
						buLog.error("i'm null -- buCliStoreDao");
						buCliStoreDao = (BuCliStoreDao) SpringContextUtil.getBean("buCliStoreDao");
					}

					if (null == buCliPublishDao) {
						buCliPublishDao = (BuCliPublishDao) SpringContextUtil.getBean("buCliPublishDao");
					}

					BuCliPublish buCliPublish = buCliPublishDao.findOne(dataPack.getCliPublish().getSys_id());
					File file = new File(buCliPublish.buildRootPath(), dataPack.getPath());
					File scanParent = new File(file.getParent().replace(buCliPublish.buildRootPath(), buCliPublish.buildScanRootPath()));
					if (!scanParent.exists()) {
						scanParent.mkdirs();
					}
					File distFile = new File(scanParent, file.getName());
					boolean renamed = file.renameTo(distFile);// 移动到扫描文件夹下去
					if (renamed) {
						buLog.info("Move " + file.getAbsolutePath() + " To " + distFile.getAbsolutePath());
					}
					buCliStoreDao.saveOrUpdate(dataPack);

				}
				wait();
			} catch (Exception e) {
				buLog.error("Exception", e);
			}
		}
	}
}
