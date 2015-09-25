package org.bu.file.dao;

import java.util.Date;
import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuCliServerRepository>
 */
@Repository("buCliServerDao")
public class BuCliServerDaoJpa extends GenericDaoJpa<BuCliServer, String> implements BuCliServerDao {
	@Autowired
	private BuCliServerRepository repository;

	@Override
	public BuCliServer saveOrUpdate(BuCliServer cliDownload) {
		List<BuCliServer> rst = findAll();
		if (null != rst && rst.size() > 0) {
			cliDownload.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(cliDownload);
	}

	@Override
	public BuCliServer updateStatus(BuStatus buStatus) {
		BuCliServer cliDownload = null;

		List<BuCliServer> rst = findAll();
		if (null != rst && rst.size() > 0) {
			cliDownload = rst.get(0);
			cliDownload.setSys_id(rst.get(0).getSys_id());
		}
		if (null != cliDownload) {
			cliDownload.setStatus(buStatus.getStatus());
			cliDownload.setUpdatedTime(new Date());
			cliDownload = repository.save(cliDownload);
		}
		return cliDownload;
	}

	@Override
	protected CrudRepository<BuCliServer, String> getRepository() {
		return repository;
	}

}
