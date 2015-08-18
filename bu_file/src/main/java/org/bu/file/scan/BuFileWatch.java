package org.bu.file.scan;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchService;
import java.util.List;

public class BuFileWatch {

	public static void watch(String pathName) {
		try {
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
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		watch("/sharefile/ds_files/bjsb");
	}

}
