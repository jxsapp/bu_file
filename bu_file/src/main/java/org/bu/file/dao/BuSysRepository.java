package org.bu.file.dao;

import org.bu.file.model.BuSys;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuSysRepository extends CrudRepository<BuSys, String> {
	@Query("from BuSys where version <> '' limit 1 ")
	public BuSys getSys();
}
