package org.bu.file.dao;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuMenu;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMenuDao extends GenericDao<BuMenu, String> {

	BuMenu getMenuType(String menuId);

	void saveOrUpdate(BuMenu BuMenu);

}
