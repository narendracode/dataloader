package com.app.dbloader.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STORE_ORDER")
public class StoreOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "ORDER_ID")
	private String orderId;

	@Column(name = "ORDER_DATE")
	private java.sql.Date orderDate;

	@Column(name = "SHIP_DATE")
	private java.sql.Date shipDate;

	@Column(name = "SHIP_MODE")
	private String shipMode;

	@Column(name = "QUANTITY")
	private Integer quantity;

	@Column(name = "DISCOUNT")
	private double discount;

	@Column(name = "PROFIT")
	private double profit;

	@Column(name = "PRODUCT_ID")
	private String productId;

	@Column(name = "CUSTOMER_NAME")
	private String customerName;

	@Column(name = "CATEGORY")
	private String category;

	@Column(name = "CUSTOMER_ID")
	private String customerId;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	public int getId() {
		return id;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setOrderDate(java.sql.Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setShipDate(java.sql.Date shipDate) {
		this.shipDate = shipDate;
	}


	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
