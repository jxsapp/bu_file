package org.bu.file.scan;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;

import org.bu.core.log.BuLog;
import org.bu.file.quartz.FileScanJob;

public class BuFileWatch {

	private static BuLog buLog = BuLog.getLogger(BuFileWatch.class);
	private static final List<String> WATCH_PATHS = new ArrayList<String>();

	private static boolean hasWatch(String path) {
		if (WATCH_PATHS.contains(path)) {
			return true;
		}
		WATCH_PATHS.add(path);
		return false;
	}

	public static void watch(String pathName) {
		try {
			if (hasWatch(pathName)) {
				return;
			}
			buLog.info("Watched  ... Path :" + pathName);
			System.out.println(pathName);
			WatchService watchService = FileSystems.getDefault().newWatchService();
			Path path = Paths.get(pathName);
			// 注册监听器
			path.register(watchService,//
					StandardWatchEventKinds.ENTRY_CREATE,//
					StandardWatchEventKinds.ENTRY_MODIFY);
			while (true) {
				// 阻塞方式，消费文件更改事件
				List<WatchEvent<?>> watchEvents = watchService.take().pollEvents();
				for (WatchEvent<?> watchEvent : watchEvents) {
					System.out.printf("[%s]文件发生了[%s]事件。%n", watchEvent.context(), watchEvent.kind());
					FileScanJob.setCanScan();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		BuFileWatch.watch("/sharefile/ds_files/bjsb");
		BuFileWatch.watch("/sharefile/ds_files/tysb");
	}

}
