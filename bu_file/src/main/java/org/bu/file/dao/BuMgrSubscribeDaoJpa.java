package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuMgrSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMgrSubscribeRepository>
 */

@Repository("buMgrSubscribeDao")
public class BuMgrSubscribeDaoJpa extends GenericDaoJpa<BuMgrSubscribe, String> implements BuMgrSubscribeDao {
	@Autowired
	private BuMgrSubscribeRepository repository;

	@Override
	public BuMgrSubscribe saveOrUpdate(BuMgrSubscribe mgrPublish) {
		List<BuMgrSubscribe> rst = repository.buExists(mgrPublish.getMgrServer().getSys_id(), mgrPublish.getMgrPublish().getSys_id());
		if (null != rst && rst.size() > 0) {
			mgrPublish.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(mgrPublish);
	}

	@Override
	protected CrudRepository<BuMgrSubscribe, String> getRepository() {
		return repository;
	}

}
