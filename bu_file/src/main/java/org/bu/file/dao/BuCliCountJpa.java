package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuCliCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buCliCountDao")
public class BuCliCountJpa extends GenericDaoJpa<BuCliCount, String> implements BuCliCountDao {
	@Autowired
	private BuCliCountRepository repository;

	@Override
	public void saveOrUpdate(BuCliCount area) {
		List<BuCliCount> rst = repository.buExists(area.getAreaCode(), area.getCliPublish().getSys_id());
		if (null != rst && !rst.isEmpty() && rst.size() > 0) {
			BuCliCount dataFile = rst.get(0);
			area.setSys_id(dataFile.getSys_id());
		}
		save(area);
	}

	@Override
	protected CrudRepository<BuCliCount, String> getRepository() {
		return repository;
	}

	@Override
	public int count(String areaCode, String pub_id) {
		return findAll("FROM BuCliCount WHERE "//
				+ " areaCode =?1  AND  pub_id =?2 "//
				+ " ORDER BY createdTime DESC", areaCode, pub_id).size();
	}

	public List<BuCliCount> findAll(String pub_id) {
		return findAll("FROM BuCliCount WHERE "//
				+ "  pub_id =?1 "//
				+ " ORDER BY createdTime DESC", pub_id);
	}
}
