package com.stophi.rca.core.service.searchtask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Holds the key/value pairs to put into the entity pairs.
 * 
 * @author cscheuermann
 */
public class CriteriaMap extends HashMap<String, Object> {

	private static final long serialVersionUID = 6441443325641564184L;
	
	/** The orders. */
	private List<OrderFilter> orders = new ArrayList<OrderFilter>();

	/**
	 * Instantiates a new criteria map.
	 */
	public CriteriaMap() {
		super();
	}
	
	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public List<OrderFilter> getOrders() {
		return orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(List<OrderFilter> orders) {
		this.orders = orders;
	}

	/**
	 * Adds the criteria.
	 *
	 * @param criteriaName the criteria name
	 * @param value the value
	 */
	public void addCriteria(String criteriaName, Object value) {
		this.put(criteriaName, value);
	}
	
	/**
	 * Gets the criteria value.
	 *
	 * @param criteriaName the criteria name
	 * @return the criteria value
	 */
	public Object getCriteriaValue(String criteriaName) {
		return this.get(criteriaName);
	}
	
	/**
	 * Adds the order.
	 *
	 * @param orderBy the order by
	 */
	public void addOrder(OrderFilter orderBy) {
		orders.add(orderBy);
	}

	/**
	 * Adds all orders.
	 *
	 * @param list The OrderFilter list
	 */
	public void addAllOrder(List<OrderFilter> list) {
		orders.addAll(list);
	}

}
