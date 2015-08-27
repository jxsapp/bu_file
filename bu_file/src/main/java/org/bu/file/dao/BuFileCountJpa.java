package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuFileCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buFileCountDao")
public class BuFileCountJpa extends GenericDaoJpa<BuFileCount, String> implements BuFileCountDao {
	@Autowired
	private BuFileCountRepository repository;

	@Override
	public void saveOrUpdate(BuFileCount area) {
		List<BuFileCount> rst = repository.buExists(area.getAreaCode(), area.getMenuTypeCode());
		if (null != rst && !rst.isEmpty() && rst.size() > 0) {
			BuFileCount dataFile = rst.get(0);
			area.setSys_id(dataFile.getSys_id());
		}
		save(area);
	}

	@Override
	protected CrudRepository<BuFileCount, String> getRepository() {
		return repository;
	}

	@Override
	public int count(String areaCode, String menuTypeCode) {
		return findAll("FROM BuFileCount WHERE "//
				+ " areaCode =?1  AND  menuTypeCode =?2 "//
				+ " ORDER BY createdTime DESC", areaCode, menuTypeCode).size();
	}

	public List<BuFileCount> findAll(String menuTypeId) {
		return findAll("FROM BuFileCount WHERE "//
				+ "  menuTypeCode =?1 "//
				+ " ORDER BY createdTime DESC", menuTypeId);
	}
}
