package com.application.tms.models;

import java.io.Serializable;

public class Students implements Serializable {
    private Integer id;
    private String userName;
    private String studentRef;

    private String emailAddress;

    private String courseStudy;

    private String password;

    private String dob;

    private String number;

    private String gender;

    public Students(String userName, String studentRef, String emailAddress, String courseStudy, String password, String dob, String number, String gender) {
        this.userName = userName;
        this.studentRef = studentRef;
        this.emailAddress = emailAddress;
        this.courseStudy = courseStudy;
        this.password = password;
        this.dob = dob;
        this.number = number;
        this.gender = gender;
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

    public String getStudentRef() {
        return studentRef;
    }

    public void setStudentRef(String studentRef) {
        this.studentRef = studentRef;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCourseStudy() {
        return courseStudy;
    }

    public void setCourseStudy(String courseStudy) {
        this.courseStudy = courseStudy;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDOB() {
        return dob;
    }

    public void setDOB(String dob) {
        dob = dob;
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
