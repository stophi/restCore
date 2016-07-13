package com.stophi.rca.core.persistence.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.common.util.StringHelper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stophi.rca.core.persistence.criteria.EntityCriteria;
import com.stophi.rca.core.persistence.criteria.HibernateCriteria;
import com.stophi.rca.core.persistence.dao.PersistentObjectDAO;
import com.stophi.rca.core.persistence.model.AbstractPersistentObject;


/**
 * Implementation of the Persistent Object DAO.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
@Repository("persistentObjectDAO")
@SuppressWarnings("unchecked")
public class PersistentObjectDAOImpl<T extends AbstractPersistentObject> implements PersistentObjectDAO<T> {

	/** The logger. */
	private final static Logger log = LoggerFactory.getLogger(PersistentObjectDAOImpl.class);

	/** Injected session factory. */
	@Autowired
	private SessionFactory sessionFactory;
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#findById(java.lang.Class, java.lang.Long)
	 */
	@Override
	public T findById(Class<T> entity, Long id) {

		if (log.isDebugEnabled()) {
			log.debug("Find requested " + entity.getSimpleName() + " by ID: "
					+ id);
		}

		return (T) currentSession().get(entity, id);
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#findByAttributes(com.stophi.rca.persistence.generic.criteria.EntityCriteria)
	 */
	@Override
	public List<T> findByAttributes(EntityCriteria criteria) {
		return findByCriteria(criteria.getCriteria());
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#findAll(java.lang.Class)
	 */
	@Override
	public List<T> findAll(Class<T> entity) {
		
		if (log.isDebugEnabled()) {
			log.debug("Find all entities of " + entity.getSimpleName() + ".");
		}

		Criteria criteria = currentSession().createCriteria(entity);
       
		return criteria.list();
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#find(com.stophi.rca.persistence.generic.criteria.HibernateCriteria)
	 */
	@Override
	public List<T> find(HibernateCriteria<T> hibernateCriteria) {

		if (log.isDebugEnabled()) {
			log.debug("Query requested with Hibernate criteria: "
					+ hibernateCriteria);
		}

		return hibernateCriteria.doInHibernate(currentSession());
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#count(com.stophi.rca.persistence.generic.criteria.EntityCriteria, java.lang.String)
	 */
	@Override
	public Long count(EntityCriteria entityCriteria, String distinctProperty) {
		return countByCriteria(entityCriteria.getCriteria(), distinctProperty);
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.dao.PersistentObjectDAO#findPaged(com.stophi.rca.persistence.generic.criteria.EntityCriteria, int, int)
	 */
	@Override
	public List<T> findPaged(EntityCriteria entityCriteria, int first, int max) {
		return findByCriteria(entityCriteria.getCriteria(), first, max);
	}

	/**
	 * Count by criteria.
	 *
	 * @param criteria the criteria
	 * @param distinctProperty the distinct property
	 * @return the long
	 */
	@SuppressWarnings("rawtypes")
	protected Long countByCriteria(DetachedCriteria criteria, String distinctProperty) {

		if (log.isDebugEnabled()) {
			log.debug("Count requested for criteria " + criteria);
		}

		Long rowCount = new Long(0);

		if (!StringHelper.isEmpty(distinctProperty))
			criteria.setProjection(Projections.countDistinct(distinctProperty));
		else
			criteria.setProjection(Projections.rowCount());

		List resultList = getExecutableCriteria(criteria).list();

		if (resultList != null && !resultList.isEmpty()) {
			rowCount = (Long) resultList.get(0);
		}

		return rowCount;
	}
	
	/**
	 * Find by criteria.
	 *
	 * @param criteria the criteria
	 * @return the list
	 */
	protected List<T> findByCriteria(DetachedCriteria criteria) {

		if (log.isDebugEnabled()) {
			log.debug("FindByCriteria requested for criteria:" + criteria);
		}

		return (List<T>) getExecutableCriteria(criteria).list();
	}

	/**
	 * Find by criteria.
	 *
	 * @param criteria the criteria
	 * @param firstResult the first result
	 * @param maxResult the max result
	 * @return the list
	 */
	protected List<T> findByCriteria(DetachedCriteria criteria, int firstResult, int maxResult) {

		if (log.isDebugEnabled()) {
			log.debug("FindByCriteria requested for firstResult:" + firstResult
					+ " maxResult:" + maxResult + " criteria:" + criteria);
		}
		
		Criteria executableCriteria = getExecutableCriteria(criteria);
		if (firstResult >= 0) {
			executableCriteria.setFirstResult(firstResult);
		}
		if (maxResult > 0) {
			executableCriteria.setMaxResults(maxResult);
		}
		
		return (List<T>) criteria.getExecutableCriteria(currentSession()).list();
	}
	
	/**
	 * Gets the executable criteria.
	 *
	 * @param detachedCriteria the detached criteria
	 * @return the executable criteria
	 */
	private Criteria getExecutableCriteria(DetachedCriteria detachedCriteria) {
		return detachedCriteria.getExecutableCriteria(currentSession());
	}

	/**
	 * Current session.
	 *
	 * @return the session
	 */
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

}
