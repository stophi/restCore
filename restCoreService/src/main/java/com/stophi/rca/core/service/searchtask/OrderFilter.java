package com.stophi.rca.core.service.searchtask;

/**
 * Holds the property name and order type (Ascending or Descending) values.
 * 
 * @author cscheuermann
 */
public class OrderFilter {

	/** The property name. */
	private String propertyName;
	
	/** The order type. */
	private OrderType orderType;
	
	/**
	 * Instantiates a new order filter.
	 *
	 * @param propertyName the property name
	 * @param orderType the order type
	 */
	public OrderFilter(String propertyName, OrderType orderType) {
		super();
		this.propertyName = propertyName;
		this.orderType = orderType;
	}
	
	/**
	 * Gets the property name.
	 *
	 * @return the property name
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * Sets the property name.
	 *
	 * @param propertyName the new property name
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * Gets the order type.
	 *
	 * @return the order type
	 */
	public OrderType getOrderType() {
		return orderType;
	}

	/**
	 * Sets the order type.
	 *
	 * @param orderType the new order type
	 */
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
}
