package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * ShowMessage repository.
 * 
 * @author Jiang XuSheng
 */
public interface BuMenuRepository extends CrudRepository<BuMenu, String> {

	@Query("from BuMenu where menuId =? ")
	List<BuMenu> buExists(String menuId);
}
