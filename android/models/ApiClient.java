package com.application.tms.models;

import com.application.tms.services.ClassesService;
import com.application.tms.services.HolidaysService;
import com.application.tms.services.RoomsService;
import com.application.tms.services.StudentServices;
import com.application.tms.services.TeachersService;
import com.application.tms.services.UsersService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://8085366e2001.ngrok.io/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static ClassesService getClassesService(){
        ClassesService classesService = getRetrofit().create(ClassesService.class);
        return classesService;
    }

    public static HolidaysService getHolidaysService(){
        HolidaysService holidaysService = getRetrofit().create(HolidaysService.class);
        return holidaysService;
    }

    public static TeachersService getTeachersService(){
        TeachersService teachersService = getRetrofit().create(TeachersService.class);
        return teachersService;
    }

    public static RoomsService getRoomsService(){
        RoomsService createHolidaysService = getRetrofit().create(RoomsService.class);
        return createHolidaysService;
    }

    public static UsersService getUsersServices(){
        UsersService usersService = getRetrofit().create(UsersService.class);
        return usersService;
    }

    public static StudentServices getStudentService(){
        StudentServices studentService = getRetrofit().create(StudentServices.class);
        return studentService;
    }
}
