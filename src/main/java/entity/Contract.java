package entity;

import java.util.Date;

public class Contract {
    private int id;
    private String contractNumber;
    private Date startDate;
    private Date endDate;
    private String apartmentId;
    private String staffId;
    private String householdIdNumber;
    private boolean status;

    public Contract() {
    }

    public Contract(int id, String contractNumber, Date startDate, Date endDate,
                    String apartmentId, String staffId, String householdIdNumber, boolean status) {
        this.id = id;
        this.contractNumber = contractNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.apartmentId = apartmentId;
        this.staffId = staffId;
        this.householdIdNumber = householdIdNumber;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getHouseholdIdNumber() {
        return householdIdNumber;
    }

    public void setHouseholdIdNumber(String householdIdNumber) {
        this.householdIdNumber = householdIdNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", contractNumber='" + contractNumber + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", apartmentId='" + apartmentId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", householdIdNumber='" + householdIdNumber + '\'' +
                ", status=" + status +
                '}';
    }
}
