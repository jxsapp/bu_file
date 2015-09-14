package org.bu.file.scan;

import org.bu.file.model.BuMenu;
import org.bu.file.model.BuStoreFile;

public interface BuScanListener {

	
	public void onScaned(BuStoreFile storeFile,BuMenu menutype);
	
}
