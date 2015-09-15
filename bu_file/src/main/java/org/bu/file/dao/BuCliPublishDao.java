package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuCliPublish;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuCliPublishDao extends GenericDao<BuCliPublish, String> {
	BuCliPublish saveOrUpdate(BuCliPublish mgrPublish);
}
