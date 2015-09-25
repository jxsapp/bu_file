package org.bu.file.dao;

import java.util.Date;
import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.core.model.BuStatus;
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
	public BuCliPublish updateStatus(String path, BuStatus buStatus) {
		BuCliPublish mgrPublish = null;

		List<BuCliPublish> rst = repository.buExists(path);
		if (null != rst && rst.size() > 0) {
			mgrPublish = rst.get(0);
			mgrPublish.setSys_id(rst.get(0).getSys_id());
		}
		if (null != mgrPublish) {
			mgrPublish.setStatus(buStatus.getStatus());
			mgrPublish.setUpdatedTime(new Date());
			mgrPublish = repository.save(mgrPublish);
		}
		return mgrPublish;
	}

	@Override
	protected CrudRepository<BuCliPublish, String> getRepository() {
		return repository;
	}

}
