package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuArea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuAreaRepository extends CrudRepository<BuArea, String> {
	@Query("from BuArea where parent =? ")
	List<BuArea> getAreas(String parent);

	@Override
	@Query("from BuArea where code =? ")
	boolean exists(String id);

}