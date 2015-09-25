package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuCliDownload;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuCliDownloadRepository extends CrudRepository<BuCliDownload, String> {

	@Query("from BuCliDownload where cliSubscribe.sys_id =? AND  path =? ")
	List<BuCliDownload> buExists(String sub_id, String path);
}
