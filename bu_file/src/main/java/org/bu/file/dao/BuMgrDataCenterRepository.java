package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMgrDataCenter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMgrDataCenterRepository extends CrudRepository<BuMgrDataCenter, String> {

	@Query("from BuMgrDataCenter where centerIp =? ")
	List<BuMgrDataCenter> buExists(String centerIp);
}
