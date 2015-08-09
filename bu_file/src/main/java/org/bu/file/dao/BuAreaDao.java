package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuArea;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuAreaDao {

	List<BuArea> getAreas(String parent);

	void saveOrUpdate(BuArea area);

}
