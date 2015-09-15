package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuMgrPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMgrPublishRepository>
 */
@Repository("buMgrPublishDao")
public class BuMgrPublishDaoJpa extends GenericDaoJpa<BuMgrPublish, String> implements BuMgrPublishDao {
	@Autowired
	private BuMgrPublishRepository repository;

	@Override
	public BuMgrPublish saveOrUpdate(BuMgrPublish mgrPublish) {
		List<BuMgrPublish> rst = repository.buExists(mgrPublish.getMgrServer().getSys_id(), mgrPublish.getPath());
		if (null != rst && rst.size() > 0) {
			mgrPublish.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(mgrPublish);
	}

	@Override
	protected CrudRepository<BuMgrPublish, String> getRepository() {
		return repository;
	}

}
