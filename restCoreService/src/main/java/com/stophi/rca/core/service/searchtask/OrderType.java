package com.stophi.rca.core.service.searchtask;

/**
 * Enum OrderType (Acending or Descending).
 * 
 * @author cscheuermann
 */
public enum OrderType {

	/** The Ascending. */
	Ascending(0, "Ascending"), /** The Descending. */
	Descending(1, "Descending");
	
	/** The desc. */
	private String desc;
	
	/** The id. */
	private Integer id;

	/**
	 * Instantiates a new order type.
	 *
	 * @param id the id
	 * @param desc the desc
	 */
	private OrderType(Integer id, String desc) {

		this.id = id;
		this.desc = desc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.desc;
	}

	/**
	 * Sets the desc.
	 *
	 * @param desc the new desc
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

}
