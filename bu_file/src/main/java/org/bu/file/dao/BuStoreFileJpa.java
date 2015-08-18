package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuStoreFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buStoreFileDao")
public class BuStoreFileJpa extends GenericDaoJpa<BuStoreFile, String> implements BuStoreFileDao {
	@Autowired
	private BuStoreFileRepository repository;

	@Override
	public void saveOrUpdate(BuStoreFile area) {
		List<BuStoreFile> rst = repository.buExists(area.getPrefix(), area.getPath());
		if (null == rst || rst.isEmpty() || rst.size() == 0) {
			save(area);
		} else {
			BuStoreFile dataFile = rst.get(0);
			if (!dataFile.getSecret().equals(area.getSecret())) {
				area.setSys_id(dataFile.getSys_id());
				save(area);
			}
		}
	}

	@Override
	public List<BuStoreFile> getStroreFiles(String parent) {
		return repository.getStoreFiles(parent);
	}

	@Override
	protected CrudRepository<BuStoreFile, String> getRepository() {
		return repository;
	}

	@Override
	public int count(String prefix, String areaEncode) {
		// from BuStoreFile where prefix =? AND path=?
		return findAll("FROM BuStoreFile WHERE "//
				+ " prefix =?1  AND  areaEncode =?2 "//
				+ " ORDER BY createdTime DESC", prefix, areaEncode).size();
	}

}
