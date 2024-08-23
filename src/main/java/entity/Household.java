package entity;

import java.util.Date;

public class Household {
    private String idNumber;
    private String householdHeadName;
    private String phoneNumber;
    private Date dateOfBirth;
    private String email;
    private String image;
    private String gender;
    private int memberQuantity;

    public Household() {
    }

    public Household(String idNumber, String householdHeadName, String phoneNumber, Date dateOfBirth,
                     String email, String image, String gender, int memberQuantity) {
        this.idNumber = idNumber;
        this.householdHeadName = householdHeadName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.image = image;
        this.gender = gender;
        this.memberQuantity = memberQuantity;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getHouseholdHeadName() {
        return householdHeadName;
    }

    public void setHouseholdHeadName(String householdHeadName) {
        this.householdHeadName = householdHeadName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getMemberQuantity() {
        return memberQuantity;
    }

    public void setMemberQuantity(int memberQuantity) {
        this.memberQuantity = memberQuantity;
    }

    @Override
    public String toString() {
        return "Household{" +
                "idNumber='" + idNumber + '\'' +
                ", householdHeadName='" + householdHeadName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", email='" + email + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", memberQuantity=" + memberQuantity +
                '}';
    }
}
