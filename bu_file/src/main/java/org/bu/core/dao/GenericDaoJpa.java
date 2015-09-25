package org.bu.core.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.repository.CrudRepository;

/**
 * generic dao jpa implement.
 * 
 * @author Jiang XuSheng
 */
@SuppressWarnings("unchecked")
public abstract class GenericDaoJpa<T, PK extends Serializable> implements GenericDao<T, PK> {
	@PersistenceContext
	protected EntityManager em;

	@Override
	public void save(T model) {
		getRepository().save(model);
	}

	@Override
	public T findOne(PK id) {
		return getRepository().findOne(id);
	}

	@Override
	public Long count() {
		return getRepository().count();
	}

	@Override
	public boolean hasData() {
		return count() > 0;
	}

	@Override
	public Query query(String hql, Object... params) {
		Query query = em.createQuery(hql);
		if (null != params) {
			for (int index = 1; index <= params.length; index++) {
				query.setParameter(index, params[index - 1]);
			}
		}
		return query;
	}

	@Override
	public List<T> findAll(String hql, Object... params) {
		Query query = em.createQuery(hql);
		if (null != params) {
			for (int index = 1; index <= params.length; index++) {
				query.setParameter(index, params[index - 1]);
			}
		}
		return query.getResultList();
	}

	@Override
	public List<T> findPage(String hql, int offset, int pageSize) {
		Query query = em.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public List<T> findPage(String hql, int offset, int pageSize, Object... params) {
		Query query = em.createQuery(hql);
		if (null != params) {
			for (int index = 1; index <= params.length; index++) {
				query.setParameter(index, params[index - 1]);
			}
		}
		query.setMaxResults(pageSize);
		return query.getResultList();
	}

	@Override
	public void delete(PK id) {
		getRepository().delete(id);
	}

	public List<T> findAll() {
		List<T> result = new ArrayList<T>();
		Iterator<T> i = getRepository().findAll().iterator();
		while (i.hasNext()) {
			result.add(i.next());
		}
		return result;
	}

	protected abstract CrudRepository<T, PK> getRepository();

}
