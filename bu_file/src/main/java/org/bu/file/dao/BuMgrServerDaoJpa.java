package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuMgrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMgrServerRepository>
 */
@Repository("buMgrServerDao")
public class BuMgrServerDaoJpa extends GenericDaoJpa<BuMgrServer, String> implements BuMgrServerDao {
	@Autowired
	private BuMgrServerRepository repository;

	@Override
	public BuMgrServer saveOrUpdate(BuMgrServer buMgrServer) {
		List<BuMgrServer> rst = repository.buExists(buMgrServer.getServerIp());
		if (null != rst && rst.size() > 0) {
			buMgrServer.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(buMgrServer);
	}

	@Override
	protected CrudRepository<BuMgrServer, String> getRepository() {
		return repository;
	}

}
