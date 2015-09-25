package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliSubscribe;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuCliSubscribeDao extends GenericDao<BuCliSubscribe, String> {
	BuCliSubscribe saveOrUpdate(BuCliSubscribe mgrPublish);

	BuCliSubscribe updateStatus(String pubServer, String publishId, BuStatus buStatus);
}
