package com.application.tms.services;

import com.application.tms.models.Classes;
import com.application.tms.models.Teachers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TeachersService {
    @GET("teachers/")
    Call<List<Teachers>> getAllTeachers();

    @POST("teachersSave")
    Call<Teachers> createTeacher(@Body Teachers teacher);
    
    @GET("deleteTeachers/{id}")
    Call<Teachers> deleteTeacher(@Path("id") int postId);


}
