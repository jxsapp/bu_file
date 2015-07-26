package org.bu.file.dao;

import org.bu.file.model.BuMenuType;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuMenuTypeDao {

	BuMenuType getLastestActiveVersion(String id);

}
