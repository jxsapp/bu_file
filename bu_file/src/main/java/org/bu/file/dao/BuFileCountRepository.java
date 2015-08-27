package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuFileCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuFileCountRepository extends CrudRepository<BuFileCount, String> {

	@Query("from BuFileCount where areaCode =? AND  menuTypeCode=? ")
	List<BuFileCount> buExists(String areaCode, String menuTypeCode);

}
