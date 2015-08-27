package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuFileCount;
import org.springframework.stereotype.Component;

/**
 * @author Jiang XuSheng
 */
@Component
public interface BuFileCountDao extends GenericDao<BuFileCount, String> {

	void saveOrUpdate(BuFileCount area);

	List<BuFileCount> findAll(String menuTypeId);

	int count(String areaCode, String menuTypeCode);

}
