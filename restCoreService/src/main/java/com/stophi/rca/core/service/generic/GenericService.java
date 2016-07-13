package com.stophi.rca.core.service.generic;

import java.util.List;

import com.stophi.rca.core.persistence.model.AbstractPersistentObject;
import com.stophi.rca.core.service.searchtask.CriteriaMap;


/**
 * The Interface GenericService represents the generic interface for basic entity operations.
 *
 * @author cscheuermann
 * @param <T> the generic type
 */
public interface GenericService<T extends AbstractPersistentObject> {

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<T> findAll();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the t
	 */
	T findById(Long id);
	
	/**
	 * Find by attributes.
	 *
	 * @param criteriaMap the criteria map
	 * @return the list
	 */
	List<T> findByAttributes(CriteriaMap criteriaMap);
	
	/**
	 * Find by.
	 *
	 * @param attributeName the attribute name
	 * @param value the value
	 * @return the list
	 */
	List<T> findBy(String attributeName, Object value);
	
	/**
	 * Find paged.
	 *
	 * @param criteriaMap the criteria map
	 * @param first the first
	 * @param max the max
	 * @return the list
	 */
	List<T> findPaged(CriteriaMap criteriaMap, int first, int max);
    
    /**
     * Count.
     *
     * @param criteriaMap the criteria map
     * @param distinctProperty the distinct property
     * @return the long
     */
    Long count(CriteriaMap criteriaMap, String distinctProperty);
}
