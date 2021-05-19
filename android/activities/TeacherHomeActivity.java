package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;

public class TeacherHomeActivity extends AppCompatActivity {

    Button teacherTimetableBtn,teacherHolidayBtn,teacherRoomAvailabiltyeBtn,teacherRoomsReservationBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_home);

        teacherTimetableBtn = findViewById(R.id.teacherTimetableBtn);
        teacherHolidayBtn = findViewById(R.id.teacherHolidayBtn);
        teacherRoomsReservationBtn = findViewById(R.id.teacherRoomsReservationBtn);

        teacherTimetableBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Timetable", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(TeacherHomeActivity.this, TeacherTimetable.class);

                startActivityForResult(intent,1);

            }
        });

        teacherHolidayBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Holidays", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(TeacherHomeActivity.this, TeacherHolidaysActivity.class);

                startActivityForResult(intent,1);

            }
        });

        teacherRoomsReservationBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v){
                Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Rooms ReservationBtn", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(TeacherHomeActivity.this, TeacherReserveRoomsActivity.class);

                startActivityForResult(intent,1);

            }
        });


    }
}