package org.bu.file.dao;

import java.util.List;

import javax.persistence.Query;

import org.bu.core.dao.GenericDaoJpa;
import org.bu.file.model.BuCliStore;
import org.bu.file.model.BuFileSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buCliStoreDao")
public class BuCliStoreDaoJpa extends GenericDaoJpa<BuCliStore, String> implements BuCliStoreDao {
	@Autowired
	private BuCliStoreRepository repository;

	@Override
	public void saveOrUpdate(BuCliStore area) {
		List<BuCliStore> rst = repository.buExists(area.getCliPublish().getSys_id(), area.getPath());
		if (null == rst || rst.isEmpty() || rst.size() == 0) {
			save(area);
		} else {
			BuCliStore dataFile = rst.get(0);
			if (!dataFile.getSecret().equals(area.getSecret())) {
				area.setSys_id(dataFile.getSys_id());
				save(area);
			}
		}
	}

	@Override
	public List<BuCliStore> buCliStores(long lastTime, int pageSize) {
		return findPage(" FROM BuCliStore WHERE lastTime >? ORDER BY lastTime  ASC ", 0, pageSize, lastTime);
	}

	@Override
	public BuFileSql calculate(String pub_id, String areaEncode) {

		Query query = query("SELECT count(sys_id) AS count ,sum(size) AS size FROM BuCliStore WHERE "//
				+ " cliPublish.sys_id =?1  AND  areaEncode =?2 ",//
				pub_id, areaEncode);
		List<?> buFileSqls = query.getResultList();
		BuFileSql size = new BuFileSql();
		if (null != buFileSqls) {
			for (Object obj : buFileSqls) {
				System.out.println(obj);
			}
		}

		return size;
	}

	@Override
	public int count(String pub_id, String areaEncode) {
		Query query = query("SELECT count(sys_id) FROM BuCliStore WHERE "//
				+ " cliPublish.sys_id =?1  AND  areaEncode =?2 ",//
				pub_id, areaEncode);
		Object size = query.getSingleResult();
		return Integer.valueOf(size.toString());
	}

	@Override
	public long sum(String pub_id, String areaEncode) {
		Query query = query("SELECT sum(size) FROM BuCliStore WHERE "//
				+ " cliPublish.sys_id =?1  AND  areaEncode =?2 ",//
				pub_id, areaEncode);
		Object size = query.getSingleResult();
		return Integer.valueOf(size.toString());
	}

	@Override
	protected CrudRepository<BuCliStore, String> getRepository() {
		return repository;
	}

}
