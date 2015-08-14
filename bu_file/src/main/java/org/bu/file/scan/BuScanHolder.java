package org.bu.file.scan;

import org.bu.file.model.BuMenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

public class BuScanHolder {

	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	
	public  void scan(BuScanListener lister,BuMenuType menutype){
		BuFileScanor buFileScanor = new BuFileScanor(lister, menutype, null, taskExecutor);
		taskExecutor.execute(buFileScanor);
	}


}
