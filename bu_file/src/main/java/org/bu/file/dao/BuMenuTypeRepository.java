package org.bu.file.dao;

import org.bu.file.model.BuMenu;
import org.bu.file.model.BuMenuType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMenuTypeRepository extends CrudRepository<BuMenu, String> {
	@Query("from BuMenu where type =? and  clientVersion = osVersion ")
	public BuMenuType getLastestActiveVersion(String id);
}
