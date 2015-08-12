package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMenuType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMenuTypeRepository extends CrudRepository<BuMenuType, String> {
	@Query("from BuMenu where type =? and  clientVersion = osVersion ")
	public BuMenuType getLastestActiveVersion(String id);

	@Query("from BuMenuType where menuId =? ")
	List<BuMenuType> buExists(String menuId);
}
