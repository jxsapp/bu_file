package org.bu.file.dic;

import java.util.List;

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

	@Query("from BuArea where code =? ")
	List<BuArea> buExists(String code);

}
