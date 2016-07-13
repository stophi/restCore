package com.stophi.rca.core.persistence.criteria;

import org.hibernate.criterion.DetachedCriteria;


/**
 * Abstract implementation for the Entity Criteria.
 * 
 * @author cscheuermann
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractEntityCriteria implements EntityCriteria {
	
	/** The criteria class. */
	private Class criteriaClass;
	
	/**
	 * Instantiates a new abstract entity criteria.
	 *
	 * @param criteriaClass the criteria class
	 */
	public AbstractEntityCriteria(Class criteriaClass) {
		this.criteriaClass = criteriaClass;
	}
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.criteria.EntityCriteria#getCriteria()
	 */
	@Override
	public DetachedCriteria getCriteria() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(criteriaClass);
		fillCriteria(detachedCriteria);
		return detachedCriteria;
	}
	
	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.criteria.EntityCriteria#getCriteriaClass()
	 */
	@Override
	public Class getCriteriaClass() {
		return criteriaClass;
	}
	
	/**
	 * Fill criteria.
	 *
	 * @param criteria the criteria
	 */
	protected abstract void fillCriteria(DetachedCriteria criteria);

}
