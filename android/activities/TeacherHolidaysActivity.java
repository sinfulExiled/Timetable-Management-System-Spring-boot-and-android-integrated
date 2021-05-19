package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Holidays;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherHolidaysActivity extends AppCompatActivity {

    CalendarPickerView datePicker;
    List<Holidays> getHolidayNames;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    String userfromMain = MainActivity.user;
    String teacherReq = "";
    String userfromAuth = AuthenticateTeacherActivity.authuserteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_holidays);

        datePicker = findViewById(R.id.calendar);

        getHolidayNames = new ArrayList<>();


        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        datePicker.init(today, nextYear.getTime())
                //        .inMode(CalendarPickerView.SelectionMode.MULTIPLE)
                .withSelectedDate(today);



        if(userfromMain.equals(userfromAuth)){
            System.out.println("calling main  auth "+userfromMain);
            getHolidayNames = getAllHolidays(userfromMain);
            teacherReq = userfromMain;
        }
        if(!userfromMain.equals(userfromAuth)){
            System.out.println("calling teacher auth "+userfromAuth);
            getHolidayNames = getAllHolidays(userfromAuth);
            teacherReq = userfromAuth;
        }




        datePicker.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {
                //String selectedDate = DateFormat.getDateInstance(DateFormat.FULL).format(date);
                Calendar calSelected = Calendar.getInstance();
                calSelected.setTime(date);

                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                String todayAsString = df.format(date);

                System.out.println(date);


                System.out.println(todayAsString);

                System.out.println();
                for (Holidays holiday : getHolidayNames){
                    String dateD = holiday.getHolidayDate();
                    String dateN = holiday.getHolidayName();
                    System.out.println("date " +dateD);
                    System.out.println("name " +dateN);
                    if(dateD.equals(todayAsString)){
                        Toast.makeText(TeacherHolidaysActivity.this, dateN, Toast.LENGTH_SHORT).show();
                    }
                }


                Calendar cal = Calendar.getInstance();
                int year = calSelected.get(Calendar.YEAR);
                int month = calSelected.get(Calendar.MONTH);
                int day = calSelected.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(TeacherHolidaysActivity.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                        mDatesetListener,
                        year, month, day);
                // dialog.updateDate(1995,01,01);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
            @Override
            public void onDateUnselected(Date date) {
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
                String requestedday = modDay + "/" + modmonth + "/" + year;

                Holidays hol = new Holidays(requestedday,teacherReq,"pending");
                updateorAddHolidays(hol);

                Toast.makeText(TeacherHolidaysActivity.this,"Requested Holiday", Toast.LENGTH_SHORT).show();


            }

        };
    }

    public List<Holidays>  getAllHolidays(String username) {



        Call<List<Holidays>> Holidayslist = ApiClient.getHolidaysService().getAllHolidays();

        List<Holidays> holidayNameList = new ArrayList<>();


        Holidayslist.enqueue(new Callback<List<Holidays>>() {

            @Override
            public void onResponse(Call<List<Holidays>> call, Response<List<Holidays>> response) {

                try {

                    if (response.isSuccessful()) {
                        List<Holidays> userResponses = response.body();

                        Calendar cal = Calendar.getInstance();
                        List<Date> dateList = new ArrayList<Date>();



                        for (Holidays r : userResponses) {


                            String holidayDate = r.getHolidayDate();
                            String holidayName = r.getHolidayName();
                            String teacherRequested = r.getTeacherRequested();
                            Date HolidaysDate = new SimpleDateFormat("dd/MM/yyyy").parse(holidayDate);
                            Holidays hol = new Holidays(holidayDate,holidayName,teacherRequested);
                            holidayNameList.add(hol);

                            cal.setTime(HolidaysDate);
                            System.out.println(teacherRequested+"zzzzzzzzzzzzzzz");
                            System.out.println(username+"aaaaaaaaaaaaaaa");
                            if(holidayName.equals(username) && teacherRequested.equals("approved")){
                                dateList.add(HolidaysDate);

                            }



                        }

                        datePicker.highlightDates(dateList);

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<List<Holidays>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });

        return holidayNameList;

    }

    public void updateorAddHolidays(Holidays holidays){


        Call<Holidays> call  = ApiClient.getHolidaysService().createHolidays(holidays);

        call.enqueue(new Callback<Holidays>() {
            @Override
            public void onResponse(Call<Holidays> call, Response<Holidays> response) {

                Holidays getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(TeacherHolidaysActivity.this,"Successfully Added Holiday",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Holidays> call, Throwable t) {

            }
        });
    }




}