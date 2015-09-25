package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuCliSubscribe;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuCliSubscribeRepository extends CrudRepository<BuCliSubscribe, String> {

	@Query("from BuCliSubscribe where pubServer =? AND  publishId =? ")
	List<BuCliSubscribe> buExists(String pubServer, String publishId);
}
