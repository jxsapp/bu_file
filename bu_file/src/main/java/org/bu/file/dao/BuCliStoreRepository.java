package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuCliStore;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuCliStoreRepository extends CrudRepository<BuCliStore, String> {

	@Query("from BuCliStore where cliPublish.sys_id =? AND  path=? ")
	List<BuCliStore> buExists(String sys_id, String path);

}
