package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuMgrPublish;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMgrPublishDao extends GenericDao<BuMgrPublish, String> {
	BuMgrPublish saveOrUpdate(BuMgrPublish mgrPublish);
}
