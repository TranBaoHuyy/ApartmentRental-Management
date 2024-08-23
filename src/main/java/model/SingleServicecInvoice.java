package model;

public class SingleServicecInvoice {
	private String serviceName;
	private double unitPrice;
	private int quantity;
	private double totalPrice;
	private String status;
	
	public SingleServicecInvoice(String serviceName, double unitPrice, int quantity, String status) {
		super();
		this.serviceName = serviceName;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalPrice = this.quantity * this.unitPrice;
		this.status = status;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
