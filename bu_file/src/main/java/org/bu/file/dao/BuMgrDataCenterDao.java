package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuMgrDataCenter;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMgrDataCenterDao extends GenericDao<BuMgrDataCenter, String> {
	BuMgrDataCenter saveOrUpdate(BuMgrDataCenter dataCenter);

	
	
}
