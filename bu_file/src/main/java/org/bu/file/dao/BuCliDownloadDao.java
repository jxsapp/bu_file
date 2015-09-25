package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliDownload;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuCliDownloadDao extends GenericDao<BuCliDownload, String> {
	BuCliDownload saveOrUpdate(BuCliDownload cliDownload);

	BuCliDownload updateStatus(String subId, String path, BuStatus buStatus);
}
