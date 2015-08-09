package org.bu.file.dao;

import org.bu.file.model.BuSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buSysDao")
public class BuSysDaoJpa implements BuSysDao {
	@Autowired
	private BuSysRepository repository;

	@Override
	public BuSys getSys() {
		return repository.getSys();
	}

	@Override
	public boolean hasData() {
		return repository.count() > 0;
	}

}
