package org.bu.file.dao;

import org.bu.file.model.BuMenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buMenuTypeDao")
public class BuMenuTypeDaoJpa implements BuMenuTypeDao {
	@Autowired
	private BuMenuTypeRepository repository;

	@Override
	public BuMenuType getLastestActiveVersion(String id) {
		return repository.getLastestActiveVersion(id);
	}

}
