package org.bu.file.dao;

import java.util.Date;
import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.core.model.BuStatus;
import org.bu.file.model.BuCliDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuCliDownloadRepository>
 */
@Repository("buCliDownloadDao")
public class BuCliDownloadDaoJpa extends GenericDaoJpa<BuCliDownload, String> implements BuCliDownloadDao {
	@Autowired
	private BuCliDownloadRepository repository;

	@Override
	public BuCliDownload saveOrUpdate(BuCliDownload cliDownload) {
		List<BuCliDownload> rst = repository.buExists(cliDownload.getCliSubscribe().getSys_id(), cliDownload.getPath());
		if (null != rst && rst.size() > 0) {
			cliDownload.setSys_id(rst.get(0).getSys_id());
		}
		return repository.save(cliDownload);
	}

	@Override
	public BuCliDownload updateStatus(String subId, String path, BuStatus buStatus) {
		BuCliDownload cliDownload = null;

		List<BuCliDownload> rst = repository.buExists(subId, path);
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
	protected CrudRepository<BuCliDownload, String> getRepository() {
		return repository;
	}

}
