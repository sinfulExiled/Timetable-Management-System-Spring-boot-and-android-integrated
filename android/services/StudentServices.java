package com.application.tms.services;

import com.application.tms.models.Students;
import com.application.tms.models.Teachers;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface StudentServices {

    @POST("registerStudent")
    Call<Students> createStudent(@Body Students students);
}
