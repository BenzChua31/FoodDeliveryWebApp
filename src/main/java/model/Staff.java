package model;

import java.time.LocalDate;

public class Staff extends User {

    private int UserID;
    private String fname;
    private String lname;
    private String pass;
    private String email;
    private String phoneNo;
    private String dob;
    private String streetNo;
    private String streetName;
    private String postcode;
    private String state;
    private String suburb;
    private String country;
    private Boolean activated;
   
    private int staffID;
    private int restaurantID;
    private int privilege;
    private String position;

    public Staff(int userID, String fname, String lname, String pass, String email, String phoneNo, String dob, String streetNo, String streetName, String postcode, String state, String suburb, String country, Boolean activated, int staffID, int restaurantID, int privilege, String position) {
        super(userID, fname, lname, pass, email, phoneNo, dob, streetNo, streetName, postcode, state, suburb, country, activated);
        this.staffID = staffID;
        this.restaurantID = restaurantID;
        this.privilege = privilege;
        this.position = position;

        this.UserID = userID;
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.email = email;
        this.phoneNo = phoneNo;
        this.dob = dob;
        this.streetNo = streetNo;
        this.streetName = streetName;
        this.postcode = postcode;
        this.state = state;
        this.suburb = suburb;
        this.country = country;
        this.activated = activated;
    }

    public int getUserID() {
        return this.UserID;
    }

    public void setUserID(int UserID) {
        this.UserID = UserID;
    }

    public int getStaffID() {
        return this.staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public int getRestaurantID() {
        return this.restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public int getPrivilege() {
        return this.privilege;
    }

    public void setPrivilege(int privilege) {
        this.privilege = privilege;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPass() {
        return this.pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return this.phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getDob() {
        return this.dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getStreetNo() {
        return this.streetNo;
    }

    public void setStreetNo(String streetNo) {
        this.streetNo = streetNo;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSuburb() {
        return this.suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Boolean isActivated() {
        return this.activated;
    }

    public Boolean getActivated() {
        return this.activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }
}
