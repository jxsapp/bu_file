package org.bu.file.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * 
 * @author Jiang XuSheng
 */
@Service("buSysService")
public class BuSysService {

	@Autowired
	private BuSysRepository repository;

	public boolean hasData() {
		long count = repository.count();
		return count > 0;
	}
}
