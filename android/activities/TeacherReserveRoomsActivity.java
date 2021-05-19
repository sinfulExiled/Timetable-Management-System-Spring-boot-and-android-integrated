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
import com.application.tms.adapters.ClassesAdapter;
import com.application.tms.adapters.RoomsAdapter;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Classes;
import com.application.tms.models.Holidays;
import com.application.tms.models.Rooms;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherReserveRoomsActivity extends AppCompatActivity implements RoomsAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    RoomsAdapter usersAdapter;


    String userfromMain = MainActivity.user;
    String userfromAuth = AuthenticateTeacherActivity.authuserteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_reserve_rooms);
        recyclerView = findViewById(R.id.recycler_view);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Reserve");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        usersAdapter = new RoomsAdapter(this::ClickedClass);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Button toolbaraddBtn = findViewById(R.id.toolbaraddBtn);

        if(userfromMain.equals(userfromAuth)){
            System.out.println("calling main  auth "+userfromMain);
            getSheduledRooms(userfromMain);
        }
        if(!userfromMain.equals(userfromAuth)){
            System.out.println("calling teacher auth "+userfromAuth);
            getSheduledRooms(userfromAuth);

        }

        toolbaraddBtn.setText("My Shedule");
        toolbaraddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userfromMain.equals(userfromAuth)){
                    System.out.println("calling main  auth "+userfromMain);
                    getTeacherRooms(userfromMain);
                }
                if(!userfromMain.equals(userfromAuth)){
                    System.out.println("calling teacher auth "+userfromAuth);
                    getTeacherRooms(userfromAuth);

                }


            }
        });




    }

    public void getSheduledRooms(String teacherName) {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(TeacherReserveRoomsActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Rooms>> roomslist = ApiClient.getRoomsService().getAllRooms();

        List<Rooms> roomList = new ArrayList<>();
        List<Rooms> sortroomList = new ArrayList<>();

        List<String> roomNameList = new ArrayList<>();
        List<String> roomcheck = new ArrayList<>();
        roomslist.enqueue(new Callback<List<Rooms>>() {

            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Rooms> userResponses = response.body();
                        List<Rooms> sortedRooms = new ArrayList<>();

                        String roomnumber;
                        for (Rooms r : userResponses) {

                            roomnumber = r.getNumber();

                            roomNameList.add(roomnumber);

                            roomList.add(r);



                            for (String b : roomNameList) {
                                if (!roomcheck.contains(b)) {
                                    roomcheck.add(b);
                                    sortroomList.add(r);

                                }
                            }


                        }
                        usersAdapter.setData(sortroomList,"null");
                        recyclerView.setAdapter(usersAdapter);
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


    public void getTeacherRooms(String teacherName) {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(TeacherReserveRoomsActivity.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Rooms>> roomslist = ApiClient.getRoomsService().getAllRooms();

        List<Rooms> batchNameList = new ArrayList<>();

        roomslist.enqueue(new Callback<List<Rooms>>() {


            @Override
            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Rooms> userResponses = response.body();
                        List<Rooms> sortedRooms = new ArrayList<>();

                        for (Rooms r : userResponses) {
                            String teacher= r.getBookedTeacher();

                              if(teacherName.equals(teacher)){

                                  batchNameList.add(r);


                                }
                        }
                        usersAdapter.setData(batchNameList,"hide");
                        recyclerView.setAdapter(usersAdapter);

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

        startActivity(new Intent(this, RoomsDetails.class).putExtra("data", roomsResponse));



    }




}