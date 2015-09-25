package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuCliCount;
import org.springframework.stereotype.Component;

/**
 * @author Jiang XuSheng
 */
@Component
public interface BuCliCountDao extends GenericDao<BuCliCount, String> {

	void saveOrUpdate(BuCliCount area);

	List<BuCliCount> findAll(String pub_id);

	int count(String areaCode, String pub_id);

}
