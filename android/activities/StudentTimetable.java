package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.adapters.ClassesAdapter;
import com.application.tms.models.Classes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentTimetable extends AppCompatActivity implements ClassesAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ClassesAdapter usersAdapter;
    TextView startET, endET;
    String startDay, endDay, getDrate;
    Spinner spinner;
    Boolean mIsSpinnerFirstCall = true;
    List<String> batchList;
    Date classesDate, fromDate, toDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    private DatePickerDialog.OnDateSetListener mDatesetListener2;
    int JSONday, JSONmonth, JSONyear, Fromday, Frommonth, Fromyear, Today, Tomonth, Toyear, daynum;

    public void init(){
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_viewStu);
        startET = findViewById(R.id.startET);
        endET = findViewById(R.id.endET);
        spinner = (Spinner) findViewById(R.id.spinner);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_timetable);

        init();

        batchList = new ArrayList<String>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        usersAdapter = new ClassesAdapter(this::ClickedClass);




        batchList = getAllClasses("01/01/1995", "31/01/2224", 1000, "void");

        ArrayAdapter<CharSequence> adapters = ArrayAdapter.createFromResource(this,
                R.array.batch_arrays, android.R.layout.simple_spinner_item);

        String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};



//        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, batchList);


        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setPrompt("Select Class");
        spinner.setAdapter(adapters);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(!mIsSpinnerFirstCall) {
                    String s = spinner.getSelectedItem().toString();

                    System.out.println(s);
                    getClassesforBatch(s);
                }
                mIsSpinnerFirstCall = false;


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        startET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    System.out.println("hasFocus");
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog = new DatePickerDialog(StudentTimetable.this, android.R.style.Theme_Holo_Dialog_MinWidth,
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
                startDay = modDay + "/" + modmonth + "/" + year;

                startET.setText(startDay);

            }

        };


        endET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Calendar cal2 = Calendar.getInstance();
                    int year = cal2.get(Calendar.YEAR);
                    int month = cal2.get(Calendar.MONTH);
                    int day = cal2.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog dialog2 = new DatePickerDialog(StudentTimetable.this, android.R.style.Theme_Holo_Dialog_MinWidth,
                            mDatesetListener2,
                            year, month, day);
                    // dialog2.updateDate(1995,01,01);
                    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog2.show();


                }

            }
        });
        mDatesetListener2 = new DatePickerDialog.OnDateSetListener() {
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
                endET.setText(endDay);
                startDay = startET.getText().toString();
                System.out.println("sssssssssss" + startDay);
                getAllClasses(startDay, endDay, 10, "void");
            }
        };


    }

    public void buttonClicked(View view) {
        switch (view.getId()) {
            case R.id.mon:
                getDailyClasses(2);
                break;

            case R.id.tue:
                getDailyClasses(3);
                break;

            case R.id.wed:
                getDailyClasses(4);
                break;

            case R.id.thu:
                getDailyClasses(5);
                break;

            case R.id.fri:
                getDailyClasses(6);
                break;

            // Do something
        }
    }


    public void getClassesforBatch(String Callbatch) {

        ProgressDialog pDialog2;
        pDialog2 = new ProgressDialog(StudentTimetable.this);
        pDialog2.setMessage("Loading Data.. Please wait...");
        pDialog2.setIndeterminate(false);
        pDialog2.setCancelable(false);
        pDialog2.show();
        Call<List<Classes>> classeslist = ApiClient.getClassesService().getAllClasses();

        List<String> batchNameList = new ArrayList<>();



        classeslist.enqueue(new Callback<List<Classes>>() {
            @Override
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {

                try {


                    if (response.isSuccessful()) {
                        List<Classes> userResponses = response.body();
                        List<Classes> sortedClasses = new ArrayList<>();

                        String batchName;


                        for (Classes r : userResponses) {

                            batchName = r.getBatch();


                            if (batchName.equals(Callbatch)) {
                                System.out.println(batchName + " " + Callbatch);
                                sortedClasses.add(r);
                            }


                        }


                        usersAdapter.setData(sortedClasses);
                        recyclerView.setAdapter(usersAdapter);


                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

                pDialog2.dismiss();


            }

            @Override
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });




    }

    public List<String> getAllClasses(String fromDay, String toDay, int allcall, String Callbatch) {

         ProgressDialog pDialog;
        pDialog = new ProgressDialog(StudentTimetable.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Classes>> classeslist = ApiClient.getClassesService().getAllClasses();

        List<String> batchNameList = new ArrayList<>();
        List<String> removeDupicate = new ArrayList<>();

        classeslist.enqueue(new Callback<List<Classes>>() {

            @Override
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {

                try {



                    if (response.isSuccessful()) {
                        List<Classes> userResponses = response.body();
                        List<Classes> sortedClasses = new ArrayList<>();


                        fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(fromDay);
                        toDate = new SimpleDateFormat("dd/MM/yyyy").parse(toDay);

                        String batchName;


                        Calendar cal = Calendar.getInstance();
                        Calendar calFrom = Calendar.getInstance();
                        Calendar calTo = Calendar.getInstance();

                        calFrom.setTime(fromDate);
                        calTo.setTime(toDate);


                        Fromday = calFrom.get(Calendar.DAY_OF_MONTH);
                        Frommonth = calFrom.get(Calendar.MONTH) + 1;
                        Fromyear = calFrom.get(Calendar.YEAR);

                        Today = calTo.get(Calendar.DAY_OF_MONTH);
                        Tomonth = calTo.get(Calendar.MONTH) + 1;
                        Toyear = calTo.get(Calendar.YEAR);

                        for (Classes r : userResponses) {
                            getDrate = r.getDate();
                            batchName = r.getBatch();
                            classesDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDrate);

                            cal.setTime(classesDate);
                            JSONday = cal.get(Calendar.DAY_OF_MONTH);
                            JSONmonth = cal.get(Calendar.MONTH) + 1;
                            JSONyear = cal.get(Calendar.YEAR);

                            batchNameList.add(batchName);


                            for (String b : batchNameList) {
                                if (!removeDupicate.contains(b)) {
                                    removeDupicate.add(b);

                                }
                            }
                            if (allcall == 1000) {
                                sortedClasses.add(r);
                            }
                            if (JSONday >= Fromday && JSONday <= Today) {
                                sortedClasses.add(r);
                            }

                            if (batchName.equals(Callbatch)) {
                                System.out.println(batchName + " " + Callbatch);
                                sortedClasses.add(r);
                            }


                        }


                        usersAdapter.setData(sortedClasses);
                        recyclerView.setAdapter(usersAdapter);


                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }


                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });


        return removeDupicate;
    }

    @Override
    public void ClickedClass(Classes userResponse) {

        startActivity(new Intent(this, ClassesDetailsActivity.class).putExtra("data", userResponse));

    }


    public void getDailyClasses(int number) {  // must use same name as in XML

        Call<List<Classes>> classeslist = ApiClient.getClassesService().getAllClasses();


        classeslist.enqueue(new Callback<List<Classes>>() {
            @Override
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {

                try {


                    startET = findViewById(R.id.startET);
                    endET = findViewById(R.id.endET);

                    startDay = startET.getText().toString();
                    endDay = startET.getText().toString();
                    if (response.isSuccessful()) {
                        List<Classes> userResponses = response.body();
                        List<Classes> sortedClasses = new ArrayList<>();


                        fromDate = new SimpleDateFormat("dd/MM/yyyy").parse(startDay);
                        toDate = new SimpleDateFormat("dd/MM/yyyy").parse(endDay);


                        Calendar cal = Calendar.getInstance();
                        Calendar calFrom = Calendar.getInstance();
                        Calendar calTo = Calendar.getInstance();

                        calFrom.setTime(fromDate);
                        calTo.setTime(toDate);

                        Fromday = calFrom.get(Calendar.DAY_OF_MONTH);
                        Frommonth = calFrom.get(Calendar.MONTH) + 1;
                        Fromyear = calFrom.get(Calendar.YEAR);

                        Today = calTo.get(Calendar.DAY_OF_MONTH);
                        Tomonth = calTo.get(Calendar.MONTH) + 1;
                        Toyear = calTo.get(Calendar.YEAR);

                        System.out.println(daynum);
                        System.out.println(number);
                        String getDayName;
                        for (Classes r : userResponses) {
                            getDrate = r.getDate();

                            classesDate = new SimpleDateFormat("dd/MM/yyyy").parse(getDrate);

                            cal.setTime(classesDate);
                            JSONday = cal.get(Calendar.DAY_OF_MONTH);
                            daynum = cal.get(Calendar.DAY_OF_WEEK);

                            System.out.println(classesDate);

                            System.out.println(JSONday);
                            System.out.println(daynum);


                            System.out.println("daynum " + daynum + " == " + "number " + number + " && " + "JSONday " + JSONday + " >= " + "Fromday" + Fromday + " && " + "JSONday " + JSONday
                                    + " <= " + "Today " + Today);
                            if (daynum == number) {

                                sortedClasses.add(r);

                            }
                        }

                        usersAdapter.setData(sortedClasses);
                        recyclerView.setAdapter(usersAdapter);


                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }

            }

            @Override
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });


    }



    private void displayUserData(String user) {
        String name = user;

        String userData = "Batch: " + name;
        Toast.makeText(this, userData, Toast.LENGTH_LONG).show();
    }


}