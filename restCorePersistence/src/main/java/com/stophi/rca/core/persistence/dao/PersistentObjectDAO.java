package com.stophi.rca.core.persistence.dao;

import java.util.List;

import com.stophi.rca.core.persistence.criteria.EntityCriteria;
import com.stophi.rca.core.persistence.criteria.HibernateCriteria;
import com.stophi.rca.core.persistence.model.AbstractPersistentObject;


/**
 * Persistent Object DAO Interface to serve as a generic DAO.
 *
 * @author cscheuermann
 * @param <T> The generic model type.
 */
public interface PersistentObjectDAO<T extends AbstractPersistentObject> {

	/**
	 * Find entity by id.
	 *
	 * @param entity The entity
	 * @param id The id
	 * @return The entity
	 */
	T findById(Class<T> entity, Long id);
	
	/**
	 * Find entity by attributes.
	 *
	 * @param criteria The criteria
	 * @return The entity list
	 */
	List<T> findByAttributes(EntityCriteria criteria);
	
	/**
	 * Find all entities.
	 *
	 * @param entity The entity
	 * @return The entity list
	 */
	List<T> findAll(Class<T> entity);
	
	/**
	 * Find by Hibernate Criteria.
	 *
	 * @param hibernateCriteria The hibernate criteria
	 * @return The entity list
	 */
	List<T> find(HibernateCriteria<T> hibernateCriteria);
	
	/**
	 * Count.
	 *
	 * @param entityCriteria The entity criteria
	 * @param distinctProperty The distinct property
	 * @return Counted result
	 */
	Long count(EntityCriteria entityCriteria, String distinctProperty);
	
	/**
	 * Find paged entities.
	 *
	 * @param entityCriteria The entity criteria
	 * @param first The first result to be obtained
	 * @param max the max result to be obtained
	 * @return The entity list
	 */
	List<T> findPaged(EntityCriteria entityCriteria, int first, int max);
	
}
