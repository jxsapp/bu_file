package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuCliStore;
import org.bu.file.model.BuFileSql;
import org.springframework.stereotype.Component;

/**
 * @author Jiang XuSheng
 */
@Component
public interface BuCliStoreDao extends GenericDao<BuCliStore, String> {

	void saveOrUpdate(BuCliStore area);

	int count(String pub_id, String areaEncode);

	long sum(String pub_id, String areaEncode);

	BuFileSql calculate(String pub_id, String areaEncode);

	public List<BuCliStore> buCliStores(long lastTime, int pageSize);

}
