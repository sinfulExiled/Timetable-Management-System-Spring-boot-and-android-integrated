package com.application.tms.services;


import com.application.tms.models.Classes;
import com.application.tms.models.Holidays;
import com.application.tms.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UsersService {

    @GET("users/")
    Call<List<Users>> getAllUsers();


    @POST("usersSave")
    Call<Users> createUsers(@Body Users users);
}
