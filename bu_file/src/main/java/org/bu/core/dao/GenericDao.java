package org.bu.core.dao;

import java.io.Serializable;
import java.util.List;

/**
 * generic dao interface definition.
 * 
 * @author Jiang XuSheng
 */
public interface GenericDao<T, PK extends Serializable> {
	/**
	 * save model.
	 * 
	 * @param model
	 */
	void save(T model);

	/**
	 * find one model.
	 * 
	 * @param id
	 * @return
	 */
	T findOne(PK id);

	/**
	 * count model.
	 * 
	 * @return
	 */
	Long count();

	/**
	 * find all entity.
	 * 
	 * @return
	 */
	List<T> findAll();

	/**
	 * find list.
	 * 
	 * @param hql
	 * @return
	 */
	List<T> findAll(String hql, Object... params);

	/**
	 * find page list.
	 * 
	 * @param hql
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	List<T> findPage(String hql, int offset, int pageSize);

	List<T> findPage(String hql, int offset, int pageSize, Object... params);

	/**
	 * 
	 * delete model by id
	 * 
	 * @param id
	 */
	void delete(PK id);

}
