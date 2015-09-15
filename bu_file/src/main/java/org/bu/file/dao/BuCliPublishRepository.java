package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuCliPublish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuCliPublishRepository extends CrudRepository<BuCliPublish, String> {

	@Query("from BuCliPublish where path =? ")
	List<BuCliPublish> buExists(String path);
}
