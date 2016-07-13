package com.stophi.rca.core.service.searchtask;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.internal.util.StringHelper;

import com.stophi.rca.core.persistence.criteria.AbstractEntityCriteria;
import com.stophi.rca.core.service.searchtask.criteria.CountCriteriaBuilder;


/**
 * An abstract implementation to build basic criteria for entity queries
 * 
 * @author cscheuermann
 */
public abstract class CriteriaBuilder extends AbstractEntityCriteria {

	/** The criteria map. */
	private CriteriaMap criteriaMap;
	
	/**
	 * Instantiates a new criteria builder.
	 *
	 * @param entityClass the entity class
	 * @param criteriaMap the criteria map
	 */
	@SuppressWarnings("rawtypes")
	public CriteriaBuilder(Class entityClass, CriteriaMap criteriaMap) {
		super(entityClass);
		this.criteriaMap = criteriaMap;
	}

	/* (non-Javadoc)
	 * @see com.stophi.rca.persistence.generic.criteria.AbstractEntityCriteria#fillCriteria(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	protected void fillCriteria(DetachedCriteria criteria) {
		addRestrictions(criteria);
		addOrder(criteria);
	}
	
	/**
	 * Adds the restrictions. To be implemented for every specific entity
	 *
	 * @param criteria the criteria
	 */
	protected abstract void addRestrictions(DetachedCriteria criteria);
	
	/**
	 * Adds the restriction string starts like.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 */
	protected void addRestrictionStringStartsLike(DetachedCriteria criteria,
			String attributeName) {
		String attributeValue = (String) criteriaMap
				.getCriteriaValue(attributeName);
		if (!StringHelper.isEmpty(attributeValue)) {
			criteria.add(Restrictions.ilike(attributeName, attributeValue + "%"));
		}

	}
	
	/**
	 * Adds the restriction string contains like.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 */
	protected void addRestrictionStringContainsLike(DetachedCriteria criteria, String attributeName) {
		String attributeValue = (String) criteriaMap
				.getCriteriaValue(attributeName);
		if (!StringHelper.isEmpty(attributeValue)) {
			criteria.add(Restrictions.ilike(attributeName, "%" + attributeValue + "%"));
		}

	}
	
	/**
	 * Adds the restriction string equals.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 */
	protected void addRestrictionStringEquals(DetachedCriteria criteria, String attributeName) {
		String attributeValue = (String) criteriaMap.getCriteriaValue(attributeName);
		if (!StringHelper.isEmpty(attributeValue)) {
			criteria.add(Restrictions.eq(attributeName, attributeValue));
		}

	}
	
	/**
	 * Adds the restriction object equals.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 */
	protected void addRestrictionObjectEquals(DetachedCriteria criteria, String attributeName) {
		Object attributeValue = criteriaMap.getCriteriaValue(attributeName);
		addRestrictionEquals(criteria, attributeName, attributeValue);
	}

	/**
	 * Adds the restriction entity id equals.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 */
	protected void addRestrictionEntityIdEquals(DetachedCriteria criteria, String attributeName) {
		Long entityId = (Long) criteriaMap.getCriteriaValue(attributeName);
		if (entityId != null) {
			addRestrictionEquals(criteria, attributeName + ".id", entityId);
		}
	}

	/**
	 * Adds the restriction equals.
	 *
	 * @param criteria the criteria
	 * @param attributeName the attribute name
	 * @param attributeValue the attribute value
	 */
	private void addRestrictionEquals(DetachedCriteria criteria,
			String attributeName, Object attributeValue) {
		if (attributeValue != null) {
			criteria.add(Restrictions.eq(attributeName, attributeValue));
		}
	}
	
	/**
	 * Adds the order.
	 *
	 * @param criteria the criteria
	 */
	private void addOrder(DetachedCriteria criteria) {
		for (OrderFilter order : criteriaMap.getOrders()) {
			if (order.getOrderType() == OrderType.Ascending) {
				criteria.addOrder(Order.asc(order.getPropertyName()));
			} else if (order.getOrderType() == OrderType.Descending) {
				criteria.addOrder(Order.desc(order.getPropertyName()));
			}
		}
	}
	
	
	/**
	 * Gets the count criteria builder.
	 *
	 * @return the count criteria builder
	 */
	public CountCriteriaBuilder getCountCriteriaBuilder() {
		return new CountCriteriaBuilder(this);

	}
}
