package com.application.tms.services;


import com.application.tms.models.Classes;
import com.application.tms.models.Holidays;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ClassesService {

    @GET("classes/")
    Call<List<Classes>> getAllClasses();


    @POST("classesSave")
    Call<Classes> createClass(@Body Classes post);


    @GET("deleteClasses/{id}")
    Call<Classes> deleteClasses(@Path("id") int postId);



}
