package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMgrSubscribe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMgrSubscribeRepository extends CrudRepository<BuMgrSubscribe, String> {

	@Query(" from BuMgrSubscribe where mgrServer.sys_id =? and  mgrPublish.sys_id =? ")
	List<BuMgrSubscribe> buExists(String serverId, String publishId);
}
