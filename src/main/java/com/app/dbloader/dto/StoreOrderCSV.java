package com.app.dbloader.dto;

import com.app.dbloader.entity.StoreOrder;
import com.app.dbloader.exception.InvalidStoreDataException;
import com.app.dbloader.util.DateUtil;

public class StoreOrderCSV {
	private String orderId;
	private String orderDate;
	private String shipDate;
	private String shipMode;
	private int quantity;
	private double discount ;
	private double profit;
	private String productId;
	private String customerName;
	private String category;
	private String customerId;
	private String productName;
	
	
	public StoreOrderCSV() {
		super();
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getShipDate() {
		return shipDate;
	}
	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}
	public String getShipMode() {
		return shipMode;
	}
	public void setShipMode(String shipMode) {
		this.shipMode = shipMode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "StoreOrder [orderId=" + orderId + ", orderDate=" + orderDate + ", shipDate=" + shipDate + ", shipMode="
				+ shipMode + ", quantity=" + quantity + ", discount=" + discount + ", profit=" + profit + ", productId="
				+ productId + ", customerName=" + customerName + ", category=" + category + ", customerId=" + customerId
				+ ", productName=" + productName + "]";
	}

	
	public StoreOrder toStoreOrder() throws InvalidStoreDataException{
				StoreOrder order = new StoreOrder();
				try {
				order.setOrderId(this.getOrderId());
				order.setOrderDate(DateUtil.getSqlDate(this.getOrderDate()));
				order.setShipDate(DateUtil.getSqlDate(this.getShipDate()));
				order.setShipMode(this.getShipMode());
				order.setQuantity(this.getQuantity());
				order.setDiscount(this.getDiscount());
				order.setProfit(this.getProfit());
				order.setProductId(this.getProductId());
				order.setCustomerName(this.getCustomerName());
				order.setCategory(this.getCategory());
				order.setCustomerId(this.getCustomerId());
				order.setProductName(this.getProductName());
				order.setCategory(this.getCategory());
				}catch(Exception e) {
					throw new InvalidStoreDataException("Invalid data provided");
				}
				return order;
	}
	

}
