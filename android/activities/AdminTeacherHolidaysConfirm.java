package com.application.tms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.adapters.HolidaysAdapter;
import com.application.tms.adapters.AdminRoomsAdapter;
import com.application.tms.adapters.HolidaysAdapter;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Holidays;
import com.application.tms.models.Holidays;
import com.application.tms.models.Holidays;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTeacherHolidaysConfirm extends AppCompatActivity implements HolidaysAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    HolidaysAdapter holidaysAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_holidays_add);
        recyclerView = findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        holidaysAdapter = new HolidaysAdapter(this::ClickedClass);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Pending Holidays");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button toolbaraddBtn = findViewById(R.id.toolbaraddBtn);


        getTeacherHolidays();


    }

    public void getTeacherHolidays() {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(AdminTeacherHolidaysConfirm.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Holidays>> Holidayslist = ApiClient.getHolidaysService().getAllHolidays();

        List<Holidays> sortholidayList = new ArrayList<>();

        List<String> holidayNameList = new ArrayList<>();
        Holidayslist.enqueue(new Callback<List<Holidays>>() {

            @Override
            public void onResponse(Call<List<Holidays>> call, Response<List<Holidays>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Holidays> userResponses = response.body();
                        List<Holidays> sortedRooms = new ArrayList<>();

                        String teacherRequested;
                        for (Holidays r : userResponses) {

                            teacherRequested = r.getTeacherRequested();

                            holidayNameList.add(teacherRequested);


                            if(teacherRequested.equals("pending")){
                                sortholidayList.add(r);
                            }

                        }
                        holidaysAdapter.setData(sortholidayList);
                        recyclerView.setAdapter(holidaysAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Holidays>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    }




    @Override
    public void ClickedClass(Holidays holidaysResponse) {

        int id = holidaysResponse.getHolidayId();
        deleteHolidays(id);
        holidaysResponse.setTeacherRequested("approved");
        updateorAddHolidays(holidaysResponse);

    }


    public void updateorAddHolidays(Holidays Holidays){

        Call<Holidays> call  = ApiClient.getHolidaysService().createHolidays(Holidays);

        call.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {

                Holidays getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminTeacherHolidaysConfirm.this,"Successfully Added Class",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {

            }
        });
    }
    public void deleteHolidays(int id){

        Call<Holidays> call  = ApiClient.getHolidaysService().deleteHolidays(id);

        call.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {


            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {

            }
        });
    }



}