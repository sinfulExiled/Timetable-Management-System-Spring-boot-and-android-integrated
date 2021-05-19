package com.application.tms.models;

import java.io.Serializable;

public class Teachers implements Serializable {

    private Integer id;
    private String userName;

    private String staffID;

    private String emailAddress;

    private String teachingModel;

    private String password;

    private String dob;

    private String number;

    private String gender;


    public Teachers(String userName, String staffID, String emailAddress, String teachingModel, String password, String dob, String number, String gender) {
        this.userName = userName;
        this.staffID = staffID;
        this.emailAddress = emailAddress;
        this.teachingModel = teachingModel;
        this.password = password;
        this.dob = dob;
        this.number = number;
        this.gender = gender;
    }


    public Teachers() {
        System.out.println(toString());
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTeachingModel() {
        return teachingModel;
    }

    public void setTeachingModel(String teachingModel) {
        this.teachingModel = teachingModel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
