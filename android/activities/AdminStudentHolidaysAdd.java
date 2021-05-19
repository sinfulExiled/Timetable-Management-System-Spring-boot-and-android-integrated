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
import com.application.tms.models.Holidays;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminStudentHolidaysAdd extends AppCompatActivity {

    private DatePickerDialog.OnDateSetListener mDatesetListener;
    TextView holidayDate,holidayName;
    String holidayDateGet,endDay,holidayNameGet;
    Button addHolidayBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_student_holidays_add);

        holidayDate = findViewById(R.id.holidayDate);
        holidayName = findViewById(R.id.holidayName);
        addHolidayBtn = findViewById(R.id.addHolidayBtn);


        holidayDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Calendar cal2 = Calendar.getInstance();
                    int year = cal2.get(Calendar.YEAR);
                    int month = cal2.get(Calendar.MONTH);
                    int day = cal2.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog2 = new DatePickerDialog(AdminStudentHolidaysAdd.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                            mDatesetListener,
                            year, month, day);
                    // dialog2.updateDate(1995,01,01);
                    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog2.show();


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
                endDay = modDay + "/" + modmonth + "/" + year;
                holidayDate.setText(endDay);

            }
        };

        addHolidayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holidayDateGet = holidayDate.getText().toString();

                holidayNameGet = holidayName.getText().toString();

                Holidays holidays = new Holidays(holidayDateGet,holidayNameGet,"none");
                updateorAddHolidays(holidays);

                Intent intent = new Intent(AdminStudentHolidaysAdd.this, AdminHolidaysManagement.class);

                startActivityForResult(intent, 1);
            }
        });
    }

    public void updateorAddHolidays(Holidays holidays){


        Call<Holidays> call  = ApiClient.getHolidaysService().createHolidays(holidays);

        call.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {

                Holidays getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminStudentHolidaysAdd.this,"Successfully Added Holiday",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {

            }
        });
    }
}