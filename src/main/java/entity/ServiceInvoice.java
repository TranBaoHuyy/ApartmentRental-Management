package entity;

import java.util.Date;

public class ServiceInvoice {

    private int id;
    private String name;
    private String staffId;
    private Date printingDate;
    private Date paymentDate;
    private String apartmentId;
    private String notes;
    private int serviceId;
    private int quantity;
    private double price;
    private boolean status;
    
	public ServiceInvoice() {}

	public ServiceInvoice(int id, String name, String staffId, Date printingDate, Date paymentDate, String apartmentId,
			String notes, int serviceId, int quantity, double price, boolean status) {

		this.id = id;
		this.name = name;
		this.staffId = staffId;
		this.printingDate = printingDate;
		this.paymentDate = paymentDate;
		this.apartmentId = apartmentId;
		this.notes = notes;
		this.serviceId = serviceId;
		this.quantity = quantity;
		this.price = price;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStaffId() {
		return staffId;
	}
	public void setStaffId(String staffId) {
		this.staffId = staffId;
	}
	public Date getPrintingDate() {
		return printingDate;
	}
	public void setPrintingDate(Date printingDate) {
		this.printingDate = printingDate;
	}
	public String getApartmentId() {
		return apartmentId;
	}
	public void setApartmentId(String apartmentId) {
		this.apartmentId = apartmentId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "ServiceInvoice [name=" + name + "]";
	}
}

