package com.cloud09.internship.activity.model;

public class DisplayContacts {
    public int ContactId;
    public int LeadID;
    public String FirstName;
    String LastName;
    String Role;
    String Address;
    String City;
    String PostalCode;
    String State;
    String Country;
    String Email;
    String Phone;


    public int getContactId() {
        return ContactId;
    }

    public void setContactId(int contactId) {
        ContactId = contactId;
    }

    public int getLeadID() {
        return LeadID;
    }

    public void setLeadID(int leadID) {
        LeadID = leadID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }


    @Override
    public String toString() {
        return "DisplayContacts{" + "\n" +
                "ContactId=" + ContactId + "\n" +
                ", LeadID=" + LeadID + "\n" +
                ", FirstName='" + FirstName + '\'' + "\n" +
                ", LastName='" + LastName + '\'' + "\n" +
                ", Role='" + Role + '\'' + "\n" +
                ", Address='" + Address + '\'' + "\n" +
                ", City='" + City + '\'' + "\n" +
                ", PostalCode='" + PostalCode + '\'' +
                ", State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", Email='" + Email + '\'' +
                ", Phone='" + Phone + '\'' +
                '}';
    }
}
