package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.Classes;

public class AdminHome extends AppCompatActivity {

    Button adminAddClassesBtnOne,adminmanageClassesBtnTwo,adminTeacherManagementBtnThree,
            adminRoomsManagementBtnFour,adminRoomsReservationsBtnFive,adminHolidaysManagement;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Admin Menu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        adminAddClassesBtnOne = findViewById(R.id.adminAddClassesBtnOne);
        adminmanageClassesBtnTwo = findViewById(R.id.adminmanageClassesBtnTwo);
        adminTeacherManagementBtnThree = findViewById(R.id.adminTeacherManagementBtnThree);
        adminRoomsReservationsBtnFive = findViewById(R.id.adminRoomsReservationsBtnFive);
        adminHolidaysManagement = findViewById(R.id.adminHolidaysManagementBtnSix);

        adminAddClassesBtnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Add Classes", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Classes classadd= new Classes();
                startActivity(new Intent(AdminHome.this, AdminAddClassesActivity.class).putExtra("data", classadd));


            }
        });

        adminmanageClassesBtnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Manage Classes", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHome.this, AdminManageTimetable.class);

                startActivityForResult(intent, 1);

            }
        });
        adminTeacherManagementBtnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Management", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHome.this, AdminTeacherManagement.class);

                startActivityForResult(intent, 1);

            }
        });


        adminRoomsReservationsBtnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Rooms Reservations", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHome.this, AdminRoomReservationsManagement.class);

                startActivityForResult(intent, 1);

            }
        });

        adminHolidaysManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Holidays Management", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHome.this, AdminHolidaysManagement.class);

                startActivityForResult(intent, 1);

            }
        });


    }

}