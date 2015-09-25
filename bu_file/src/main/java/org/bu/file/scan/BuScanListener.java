package org.bu.file.scan;

import org.bu.file.model.BuCliPublish;
import org.bu.file.model.BuCliStore;

public interface BuScanListener {

	public void onScaned(BuCliStore storeFile, BuCliPublish cliPublish);

}
