package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuCliPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuCliPublishRepository>
 */
@Repository("buCliPublishDao")
public class BuCliPublishDaoJpa extends GenericDaoJpa<BuCliPublish, String> implements BuCliPublishDao {
	@Autowired
	private BuCliPublishRepository repository;

	@Override
	public BuCliPublish saveOrUpdate(BuCliPublish mgrPublish) {
		List<BuCliPublish> rst = repository.buExists(mgrPublish.getPath());
		if (null != rst && rst.size() > 0) {
			mgrPublish.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(mgrPublish);
	}

	@Override
	protected CrudRepository<BuCliPublish, String> getRepository() {
		return repository;
	}

}
