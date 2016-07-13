package com.stophi.rca.core.service.searchtask.criteria;

import org.hibernate.criterion.DetachedCriteria;

import com.stophi.rca.core.persistence.criteria.EntityCriteria;
import com.stophi.rca.core.service.searchtask.CriteriaBuilder;

/**
 * Implementation for building criterias to count number of entities.
 * 
 * @author cscheuermann
 */
public class CountCriteriaBuilder implements EntityCriteria {
	
	/** The wrapped criteria builder. */
	private CriteriaBuilder wrappedCriteriaBuilder;
	
	/**
	 * Instantiates a new count criteria builder.
	 *
	 * @param wrappedCriteriaBuilder the wrapped criteria builder
	 */
	public CountCriteriaBuilder(CriteriaBuilder wrappedCriteriaBuilder) {
		super();
		this.wrappedCriteriaBuilder = wrappedCriteriaBuilder;
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.criteria.EntityCriteria#getCriteriaClass()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Class getCriteriaClass() {
		return wrappedCriteriaBuilder.getCriteriaClass();
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.criteria.EntityCriteria#getCriteria()
	 */
	@Override
	public DetachedCriteria getCriteria() {
		return wrappedCriteriaBuilder.getCriteria();
	}

}
