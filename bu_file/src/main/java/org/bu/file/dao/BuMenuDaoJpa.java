package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buMenuDao")
public class BuMenuDaoJpa extends GenericDaoJpa<BuMenu, String> implements BuMenuDao {
	@Autowired
	private BuMenuRepository repository;

	@Override
	public void saveOrUpdate(BuMenu BuMenu) {
		List<BuMenu> rst = repository.buExists(BuMenu.getMenuId());
		if (null == rst || rst.isEmpty() || rst.size() == 0) {
			repository.save(BuMenu);
		}
	}

	@Override
	public BuMenu getMenuType(String menuId) {
		List<BuMenu> rst = repository.buExists(menuId);
		if (rst.size() > 0) {
			return rst.get(0);
		}
		return null;
	}

	@Override
	protected CrudRepository<BuMenu, String> getRepository() {
		return repository;
	}

}
