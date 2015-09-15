package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMgrPublish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMgrPublishRepository extends CrudRepository<BuMgrPublish, String> {

	@Query("from BuMgrPublish where mgrServer.sys_id =?  AND  path =? ")
	List<BuMgrPublish> buExists(String serverId, String path);
}
