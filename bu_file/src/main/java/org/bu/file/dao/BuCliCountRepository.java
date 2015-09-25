package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuCliCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuCliCountRepository extends CrudRepository<BuCliCount, String> {

	@Query("from BuCliCount where areaCode =? AND  cliPublish.sys_id=? ")
	List<BuCliCount> buExists(String areaCode, String pub_id);

}
