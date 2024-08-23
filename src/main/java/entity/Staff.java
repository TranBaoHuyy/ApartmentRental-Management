package entity;

import java.util.Date;

public class Staff {
    private String id;
    private String name;
    private boolean gender;
    private String address;
    private String phoneNumber;
    private Date dob;
    private String email;
    private String image;
    private String idNumber;
    private int departmentId;

    public Staff() {
    }

    public Staff(String id, String name, boolean gender, String address, String phoneNumber, Date dob,
                 String email, String image, String idNumber, int departmentId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.email = email;
        this.image = image;
        this.idNumber = idNumber;
        this.departmentId = departmentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
