package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMgrServer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMgrServerRepository extends CrudRepository<BuMgrServer, String> {

	@Query("from BuMgrServer where serverIp =? ")
	List<BuMgrServer> buExists(String serverIp);

	@Query("from BuMgrServer where dataCenter =? ")
	List<BuMgrServer> getDataCenter(int dataCenter);
}
