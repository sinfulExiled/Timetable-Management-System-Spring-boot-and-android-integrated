package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;

public class StudentHomeActivity extends AppCompatActivity {

    Button timeTableBtn,stuHolidays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        timeTableBtn = findViewById(R.id.studentTimetable);
        stuHolidays = findViewById(R.id.studentHolidays);
        timeTableBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast toast3 = Toast.makeText(getApplicationContext(), "Student Timetables", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent6 = new Intent(StudentHomeActivity.this, StudentTimetable.class);

                startActivityForResult(intent6,1);

            }
        });

        stuHolidays.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast toast3 = Toast.makeText(getApplicationContext(), "Student Holidayd", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent6 = new Intent(StudentHomeActivity.this, StudentHoliday.class);

                startActivityForResult(intent6,2);

            }
        });
    }
}