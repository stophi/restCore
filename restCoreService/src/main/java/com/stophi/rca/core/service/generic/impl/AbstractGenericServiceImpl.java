package com.stophi.rca.core.service.generic.impl;

import java.util.List;

import javax.annotation.Resource;

import com.stophi.rca.core.persistence.dao.PersistentObjectDAO;
import com.stophi.rca.core.persistence.model.AbstractPersistentObject;
import com.stophi.rca.core.service.generic.GenericService;
import com.stophi.rca.core.service.searchtask.CriteriaBuilder;
import com.stophi.rca.core.service.searchtask.CriteriaMap;


/**
 * The Class AbstractGenericServiceImpl is an abstract implementation for the Generic Service.
 *
 * @param <T> the generic type
 */
public abstract class AbstractGenericServiceImpl<T extends AbstractPersistentObject> implements GenericService<T> {
	
	/** The dao. */
	@Resource(name = "persistentObjectDAO")
	private PersistentObjectDAO<T> dao;

	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#findAll()
	 */
	@Override
	public List<T> findAll() {
		return dao.findAll(getEntityClass());
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#findById(java.lang.Long)
	 */
	@Override
	public T findById(Long id) {
		return dao.findById(getEntityClass(), id);
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#findByAttributes(com.stophi.rca.service.searchtask.CriteriaMap)
	 */
	@Override
	public List<T> findByAttributes(CriteriaMap criteriaMap) {
		return dao.findByAttributes(getCriteriaBuilder(criteriaMap));
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#findBy(java.lang.String, java.lang.Object)
	 */
	@Override
	public List<T> findBy(String attributeName, Object value) {
		CriteriaMap criteriaMap = new CriteriaMap();
		
		criteriaMap.addCriteria(attributeName, value);
		return findByAttributes(criteriaMap);
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#findPaged(com.stophi.rca.service.searchtask.CriteriaMap, int, int)
	 */
	@Override
	public List<T> findPaged(CriteriaMap criteriaMap, int first, int max) {
		
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder(criteriaMap);
		
		List<T> resultList = dao.findPaged(criteriaBuilder, first, max);
		return resultList;
	}


	/* (non-Javadoc)
	 * @see com.stophi.rca.service.generic.GenericService#count(com.stophi.rca.service.searchtask.CriteriaMap, java.lang.String)
	 */
	@Override
	public Long count(CriteriaMap criteriaMap, String distinctProperty) {
		CriteriaBuilder criteriaBuilder = getCriteriaBuilder(criteriaMap);
		
		return dao.count(criteriaBuilder.getCountCriteriaBuilder(), distinctProperty);
	}
	
	/**
	 * Gets the entity class. To be implemented for each service.
	 *
	 * @return the entity class
	 */
	protected abstract Class<T> getEntityClass();

	/**
	 * Gets the criteria builder. To be implemented for each service.
	 *
	 * @param criteriaMap the criteria map
	 * @return the criteria builder
	 */
	protected abstract CriteriaBuilder getCriteriaBuilder(CriteriaMap criteriaMap);

}
