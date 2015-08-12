package org.bu.file.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bu.file.model.BuMenuType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Jiang XuSheng
 * @param <BuMenuRepository>
 */
@Repository("buMenuTypeDao")
public class BuMenuTypeDaoJpa implements BuMenuTypeDao {
	@Autowired
	private BuMenuTypeRepository repository;

	@Override
	public List<BuMenuType> getAll() {
		List<BuMenuType> buMenuTypes = new ArrayList<BuMenuType>();
		Iterable<BuMenuType> iterables = repository.findAll();
		Iterator<BuMenuType> iterator = iterables.iterator();
		while (iterator.hasNext()) {
			buMenuTypes.add(iterator.next());
		}
		return buMenuTypes;
	}

	@Override
	public void saveOrUpdate(BuMenuType buMenuType) {
		List<BuMenuType> rst = repository.buExists(buMenuType.getMenuId());
		if (null == rst || rst.isEmpty() || rst.size() == 0) {
			repository.save(buMenuType);
		}
	}

}
