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
import com.application.tms.adapters.AdminRoomsAdapter;
import com.application.tms.adapters.AdminRoomsAdapter;
import com.application.tms.adapters.AdminRoomsAdapter;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Rooms;
import com.application.tms.models.Rooms;
import com.application.tms.models.Rooms;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminRoomReservationsManagement extends AppCompatActivity implements AdminRoomsAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    AdminRoomsAdapter adminRoomsAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room_reservations_management);
        recyclerView = findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        adminRoomsAdapter = new AdminRoomsAdapter(this::ClickedClass);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Pending Rooms");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button toolbaraddBtn = findViewById(R.id.toolbaraddBtn);


        getTeacherRooms();


    }

    public void getTeacherRooms() {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(AdminRoomReservationsManagement.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Rooms>> roomslist = ApiClient.getRoomsService().getAllRooms();

        List<Rooms> sortholidayList = new ArrayList<>();

        List<String> holidayNameList = new ArrayList<>();
        roomslist.enqueue(new Callback<List<Rooms>>() {

            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Rooms> userResponses = response.body();
                        List<Rooms> sortedRooms = new ArrayList<>();

                        String roomstatus;
                        for (Rooms r : userResponses) {

                            roomstatus = r.getStatus();

                            holidayNameList.add(roomstatus);


                            if(roomstatus.equals("pending")){
                                sortholidayList.add(r);
                            }

                        }
                        adminRoomsAdapter.setData(sortholidayList);
                        recyclerView.setAdapter(adminRoomsAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Rooms>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    }




    @Override
    public void ClickedClass(Rooms roomsResponse) {

        int id = roomsResponse.getId();
        deleteRooms(id);
        roomsResponse.setStatus("booked");
        updateorAddRooms(roomsResponse);

    }


    public void updateorAddRooms(Rooms Rooms){

        Call<Rooms> call  = ApiClient.getRoomsService().createRooms(Rooms);

        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {

                Rooms getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminRoomReservationsManagement.this,"Successfully Added Class",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {

            }
        });
    }
    public void deleteRooms(int id){

        Call<Rooms> call  = ApiClient.getRoomsService().deleteRooms(id);

        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {


            }

            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {

            }
        });
    }



}