package com.stophi.rca.core.persistence.criteria;

import org.hibernate.criterion.DetachedCriteria;

/**
 * The Interface EntityCriteria represents the generic interface 
 * for a criteria of an entity.
 * 
 * @author cscheuermann
 */
@SuppressWarnings("rawtypes")
public interface EntityCriteria {

	/**
	 * Gets the criteria.
	 *
	 * @return the criteria
	 */
	DetachedCriteria getCriteria();
	
	/**
	 * Gets the criteria class.
	 *
	 * @return the criteria class
	 */
	Class getCriteriaClass();
}
