package com.application.tms.models;

public class Holidays {

    String holidayDate,holidayName,teacherRequested;

    int holidayId,id;

    public Holidays(String holidayDate, String holidayName, String teacherRequested) {
        this.holidayDate = holidayDate;
        this.holidayName = holidayName;
        this.teacherRequested = teacherRequested;
    }

    public String getTeacherRequested() {
        return teacherRequested;
    }

    public void setTeacherRequested(String teacherRequested) {
        this.teacherRequested = teacherRequested;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(int holidayId) {
        this.holidayId = holidayId;
    }

    public String getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }
}
