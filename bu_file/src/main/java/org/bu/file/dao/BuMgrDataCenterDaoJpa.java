package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuMgrDataCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMgrDataCenterRepository>
 */
@Repository("buMgrDataCenterDao")
public class BuMgrDataCenterDaoJpa extends GenericDaoJpa<BuMgrDataCenter, String> implements BuMgrDataCenterDao {
	@Autowired
	private BuMgrDataCenterRepository repository;

	@Override
	public BuMgrDataCenter saveOrUpdate(BuMgrDataCenter cliSubscribe) {
		List<BuMgrDataCenter> rst = repository.buExists(cliSubscribe.getCenterIp());
		if (null != rst && rst.size() > 0) {
			cliSubscribe.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(cliSubscribe);
	}

	@Override
	protected CrudRepository<BuMgrDataCenter, String> getRepository() {
		return repository;
	}

}
