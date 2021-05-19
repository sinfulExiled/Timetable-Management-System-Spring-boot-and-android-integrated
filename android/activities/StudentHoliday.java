package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
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
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentHoliday extends AppCompatActivity {

    CalendarPickerView datePicker;
    List<Holidays> getHolidayNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_holiday);

        datePicker = findViewById(R.id.calendar);

        getHolidayNames = new ArrayList<>();

        Date today = new Date();
        Calendar nextYear = Calendar.getInstance();
        nextYear.add(Calendar.YEAR, 1);

        datePicker.init(today, nextYear.getTime())
        //        .inMode(CalendarPickerView.SelectionMode.MULTIPLE)
                .withSelectedDate(today);

        getHolidayNames = getAllHolidays();


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
                    String teacherRequested = holiday.getTeacherRequested();
                    System.out.println("date " +dateD);
                    System.out.println("name " +dateN);
                    if(dateD.equals(todayAsString)&&teacherRequested.equals("none")){
                        Toast.makeText(StudentHoliday.this, dateN, Toast.LENGTH_SHORT).show();
                    }
                }

            }
            @Override
            public void onDateUnselected(Date date) {
            }
        });
    }

    public List<Holidays>  getAllHolidays() {



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
                            String TeacherRequested = r.getTeacherRequested();
                            Date HolidaysDate = new SimpleDateFormat("dd/MM/yyyy").parse(holidayDate);


                            holidayNameList.add(r);
                            if(TeacherRequested.equals("none")){
                                dateList.add(HolidaysDate);
                            }


                            cal.setTime(HolidaysDate);

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
}