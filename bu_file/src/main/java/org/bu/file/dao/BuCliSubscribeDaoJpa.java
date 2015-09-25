package org.bu.file.dao;

import java.util.Date;
import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliSubscribe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuCliSubscribeRepository>
 */
@Repository("buCliSubscribeDao")
public class BuCliSubscribeDaoJpa extends GenericDaoJpa<BuCliSubscribe, String> implements BuCliSubscribeDao {
	@Autowired
	private BuCliSubscribeRepository repository;

	@Override
	public BuCliSubscribe saveOrUpdate(BuCliSubscribe cliSubscribe) {
		List<BuCliSubscribe> rst = repository.buExists(cliSubscribe.getPubServer(), cliSubscribe.getPublishId());
		if (null != rst && rst.size() > 0) {
			cliSubscribe.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(cliSubscribe);
	}

	@Override
	public BuCliSubscribe updateStatus(String pubServer, String publishId, BuStatus buStatus) {
		BuCliSubscribe cliSubscribe = null;

		List<BuCliSubscribe> rst = repository.buExists(pubServer, publishId);
		if (null != rst && rst.size() > 0) {
			cliSubscribe = rst.get(0);
			cliSubscribe.setSys_id(rst.get(0).getSys_id());
		}
		if (null != cliSubscribe) {
			cliSubscribe.setStatus(buStatus.getStatus());
			cliSubscribe.setUpdatedTime(new Date());
			cliSubscribe = repository.save(cliSubscribe);
		}
		return cliSubscribe;
	}

	@Override
	protected CrudRepository<BuCliSubscribe, String> getRepository() {
		return repository;
	}

}
