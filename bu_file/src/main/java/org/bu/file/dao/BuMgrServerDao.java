package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuMgrServer;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMgrServerDao extends GenericDao<BuMgrServer, String> {
	BuMgrServer saveOrUpdate(BuMgrServer buMgrServer);

	BuMgrServer getDataCenter();
}
