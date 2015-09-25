package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliServer;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuCliServerDao extends GenericDao<BuCliServer, String> {
	BuCliServer saveOrUpdate(BuCliServer buCliServer);

	BuCliServer updateStatus(BuStatus buStatus);
}
