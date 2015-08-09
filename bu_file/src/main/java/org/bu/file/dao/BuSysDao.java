package org.bu.file.dao;

import org.bu.file.model.BuSys;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Component
public interface BuSysDao {

	BuSys getSys();

	boolean hasData();

}
