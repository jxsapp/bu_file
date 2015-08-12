package org.bu.file.dao;

import java.util.List;

import org.bu.file.model.BuMenuType;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMenuTypeDao {

	List<BuMenuType> getAll();

	void saveOrUpdate(BuMenuType buMenuType);

}
