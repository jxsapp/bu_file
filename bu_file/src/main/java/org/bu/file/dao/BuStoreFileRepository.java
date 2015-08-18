package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuStoreFile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * @author Jiang XuSheng
 */
public interface BuStoreFileRepository extends CrudRepository<BuStoreFile, String> {
	
	@Query("from BuStoreFile where parent =? ")
	List<BuStoreFile> getStoreFiles(String parent);

	@Query("from BuStoreFile where prefix =? AND  path=? ")
	List<BuStoreFile> buExists(String prefix, String path);

}
