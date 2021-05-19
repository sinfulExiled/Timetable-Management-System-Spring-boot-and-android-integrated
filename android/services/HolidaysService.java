package com.application.tms.services;

import com.application.tms.models.Classes;
import com.application.tms.models.Holidays;
import com.application.tms.models.Rooms;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface HolidaysService {
    @GET("holidays/")
    Call<List<Holidays>> getAllHolidays();


    @GET("deleteHolidays/{id}")
    Call<Holidays> deleteHolidays(@Path("id") int postId);

    @POST("holidaysSave")
    Call<Holidays> createHolidays(@Body Holidays post);

    @FormUrlEncoded
    @POST("holidaysSave")
    Call<Holidays> createHolisdays(
            @Field("holidayDate") String holidayDate,
            @Field("holidayName") String holidayName
    );

    @FormUrlEncoded
    @POST("saveHolidays")
    Call<Holidays> createHolisdays(@FieldMap Map<String, String> fields);

    @PUT("holidays/{id}")
    Call<Holidays> putHolidays(@Path("id") int id, @Body Holidays holidays);

    @PATCH("holidays/{id}")
    Call<Holidays> patchHolidays(@Path("id") int id, @Body Holidays holidays);


}
