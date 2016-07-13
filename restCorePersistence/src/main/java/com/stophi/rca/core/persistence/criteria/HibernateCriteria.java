package com.stophi.rca.core.persistence.criteria;

import java.util.List;

import org.hibernate.Session;


/**
 * The Interface HibernateCriteria represents a special Criteria 
 * for performing custom queries in hibernate.
 *
 * @param <T> the generic type
 * 
 * @author cscheuermann
 */
public interface HibernateCriteria<T> {

	/**
	 * Do in hibernate.
	 *
	 * @param session the session
	 * @return the list
	 */
	List<T> doInHibernate(Session session);
}
