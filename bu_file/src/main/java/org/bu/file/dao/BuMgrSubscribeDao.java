package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuMgrSubscribe;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMgrSubscribeDao extends GenericDao<BuMgrSubscribe, String> {
	BuMgrSubscribe saveOrUpdate(BuMgrSubscribe mgrSubscribe);
}
