package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buAreaDao")
public class BuAreaDaoJpa implements BuAreaDao {
	@Autowired
	private BuAreaRepository repository;

	@Override
	public List<BuArea> getAreas(String parent) {
		return repository.getAreas(parent);
	}

	public void saveOrUpdate(BuArea area) {

		boolean exists = repository.exists(area.getCode());
		if (!exists) {
			repository.save(area);
		}
	}

}
