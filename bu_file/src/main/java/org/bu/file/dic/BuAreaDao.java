package org.bu.file.dic;

import java.util.List;

import org.bu.core.dao.GenericDao;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuAreaDao extends GenericDao<BuArea, String> {

	List<BuArea> getAreas(String parent);

	void saveOrUpdate(BuArea area);

}
