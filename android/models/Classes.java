package com.application.tms.models;

import java.io.Serializable;

public class Classes implements Serializable {

    Integer id;
    String date,day,time,endTime,venue,lecturerName,batch,module,degreeType;

    private boolean is_active;

    public Classes(String date, String day, String time, String EndTime, String venue, String lecturerName, String batch, String module, String degreeType) {

        this.date = date;
        this.day = day;
        this.time = time;
        this.endTime = EndTime;
        this.venue = venue;
        this.lecturerName = lecturerName;
        this.batch = batch;
        this.module = module;
        this.degreeType = degreeType;
    }

    public Classes() {
        System.out.println(toString());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", EndTime='" + endTime + '\'' +
                ", venue='" + venue + '\'' +
                ", lecturerName=" + lecturerName +
                ", batch='" + batch + '\'' +
                ", module='" + module + '\'' +
                ", degreeType='" + degreeType + '\'' +
                '}';
    }


}
