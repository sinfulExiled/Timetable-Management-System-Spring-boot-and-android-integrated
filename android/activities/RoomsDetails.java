package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Rooms;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomsDetails extends AppCompatActivity {

    private Rooms roomsResponse;
    private Button reserveRoom;
    private TextView number,floor,reservedDate,startTime,endTime,bookedTeacher;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    private String userfromMain = MainActivity.user;
    private String getReservedDate;
    private String userfromAuth = AuthenticateTeacherActivity.authuserteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooms_details);

        number  = findViewById(R.id.number);
        floor  = findViewById(R.id.floor);
        reservedDate  = findViewById(R.id.reservedDate);
        startTime  = findViewById(R.id.startTime);
        endTime  = findViewById(R.id.endTime);
        bookedTeacher  = findViewById(R.id.bookedTeacher);

        reserveRoom  = findViewById(R.id.reserveRoom);

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            roomsResponse = (Rooms) intent.getSerializableExtra("data");

            String Snumber = roomsResponse.getNumber();
            String Sfloor = roomsResponse.getFloor();
            String Sstatus = roomsResponse.getStatus();
            String SreservedDate = roomsResponse.getReservedDate();
            String SstartTime = roomsResponse.getStartTime();
            String SendTime = roomsResponse.getEndTime();
            String SbookedTeacher = roomsResponse.getBookedTeacher();
            String SmaxCapacity = roomsResponse.getMaxCapacity();
            String SminCapacity = roomsResponse.getMinCapacity();
            String Savailability = roomsResponse.getAvailability();




            reservedDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        System.out.println("hasFocus");
                        Calendar cal = Calendar.getInstance();
                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH);
                        int day = cal.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog dialog = new DatePickerDialog(RoomsDetails.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                                mDatesetListener,
                                year, month, day);
                        // dialog.updateDate(1995,01,01);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();


                    }

                }
            });

            mDatesetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month = month + 1;

                    String modDay = Integer.toString(dayOfMonth);
                    String modmonth = Integer.toString(month);

                    if(dayOfMonth < 10){
                        modDay = '0'+modDay;
                    }
                    if(month < 10){
                        modmonth = '0'+modmonth;
                    }
                    getReservedDate = modDay + "/" + modmonth + "/" + year;
                    reservedDate.setText(getReservedDate);

                }

            };


            number.setText(Snumber);
            floor.setText(Sfloor);


            if(userfromMain.equals(userfromAuth)){
                bookedTeacher.setText(userfromMain);

            }
            if(!userfromMain.equals(userfromAuth)){
                bookedTeacher.setText(userfromAuth);

            }

            reserveRoom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String getnumber = number.getText().toString();
                    String getfloor = floor.getText().toString();
                    String getreservedDate = reservedDate.getText().toString();
                    String getstartTime = startTime.getText().toString();
                    String getendTime = endTime.getText().toString();
                    String getbookedTeacher = bookedTeacher.getText().toString();


                    Rooms sendroom = new Rooms();
                    if(userfromMain.equals(userfromAuth)){
                        sendroom = new Rooms(getnumber,getfloor,"pending",getreservedDate,getstartTime,getendTime,userfromMain
                                ,SmaxCapacity,SminCapacity,"unavailable");

                    }
                    if(!userfromMain.equals(userfromAuth)){
                        sendroom = new Rooms(getnumber,getfloor,"pending",getreservedDate,getstartTime,getendTime,userfromAuth
                                ,SmaxCapacity,SminCapacity,"unavailable");

                    }

                    updateorAddRooms(sendroom);

                    Intent intent = new Intent(RoomsDetails.this, TeacherReserveRoomsActivity.class);

                    startActivityForResult(intent,1);

                }
            });


        }


    }
    public void updateorAddRooms(Rooms rooms){
//        Holidays Rooms = new Rooms("31/12/2020","New Years Eve");

        Call<Rooms> call  = ApiClient.getRoomsService().createRooms(rooms);

        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {

                Rooms getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(RoomsDetails.this,"Successful",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {

            }
        });
    }
}