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

public class AdminHolidaysManagement extends AppCompatActivity {

    Button adminStudentHolidayBtn,adminTeachersHolidaysBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_holidays_management);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Holidays  Management");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        adminStudentHolidayBtn = findViewById(R.id.adminStudentHolidayBtn);
        adminTeachersHolidaysBtn = findViewById(R.id.adminTeachersHolidaysBtn);


        adminStudentHolidayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Student Holidays", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHolidaysManagement.this, AdminStudentHolidaysAdd.class);

                startActivityForResult(intent, 1);

            }
        });

        adminTeachersHolidaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Holidays", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminHolidaysManagement.this, AdminTeacherHolidaysConfirm.class);

                startActivityForResult(intent, 1);

            }
        });
    }

}