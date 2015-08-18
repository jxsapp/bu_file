package org.bu.file.dao;

import java.util.List;

import org.bu.core.dao.GenericDao;
import org.bu.file.model.BuStoreFile;
import org.springframework.stereotype.Component;

/**
 * @author Jiang XuSheng
 */
@Component
public interface BuStoreFileDao extends GenericDao<BuStoreFile, String> {

	List<BuStoreFile> getStroreFiles(String parent);

	void saveOrUpdate(BuStoreFile area);

	int count(String prefix, String areaEncode);

}
