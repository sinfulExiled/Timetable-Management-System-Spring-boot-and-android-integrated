package com.application.tms.services;

import com.application.tms.models.Rooms;
import com.application.tms.models.Teachers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RoomsService {
    @GET("rooms")
    Call<List<Rooms>> getAllRooms();

    @POST("roomsSave")
    Call<Rooms> createRooms(@Body Rooms teacher);

    @GET("deleteRooms/{id}")
    Call<Rooms> deleteRooms(@Path("id") int postId);
}
