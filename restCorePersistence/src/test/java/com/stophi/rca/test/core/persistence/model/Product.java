package com.stophi.rca.test.core.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.stophi.rca.core.persistence.model.AbstractPersistentObject;

@Entity
@Table(name = "Product_Table")
public class Product extends AbstractPersistentObject {
	
	private Long productId;
	private String ticker;
	private String assetClass;
	
	@Id
	@Column
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	@Column
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	@Column
	public String getAssetClass() {
		return assetClass;
	}
	
	public void setAssetClass(String assetClass) {
		this.assetClass = assetClass;
	}
	
}
