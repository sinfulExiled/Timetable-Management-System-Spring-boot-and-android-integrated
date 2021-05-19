package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Teachers;
import com.application.tms.models.Holidays;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddTeachersActivity extends AppCompatActivity {

    String userName,staffID,emailAddress,teachingModel,password,dob,number,gender;
    TextInputEditText userNameTi,staffIDTi,emailAddressTi,teachingModelTi,passwordTi,dobTi,numberTi,genderTi;
    Toolbar toolbar;
    Button submitTeacher;
    public void init(){
        toolbar = findViewById(R.id.toolbar);

        userNameTi = findViewById(R.id.userNameOne);
        staffIDTi = findViewById(R.id.staffIDTwo);
        emailAddressTi = findViewById(R.id.emailAddressThree);
        teachingModelTi = findViewById(R.id.teachingModelFour);
        passwordTi = findViewById(R.id.passwordFive);
        dobTi = findViewById(R.id.dobSix);
        numberTi = findViewById(R.id.numberSeven);
        genderTi = findViewById(R.id.genderEight);
      
        submitTeacher = findViewById(R.id.addTeachersBtn);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_teachers);
        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Teachers");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        Teachers teachers = (Teachers) intent.getSerializableExtra("data");

        userNameTi.setText(teachers.getUserName());
        staffIDTi.setText(teachers.getStaffID());
        emailAddressTi.setText(teachers.getEmailAddress());
        teachingModelTi.setText(teachers.getTeachingModel());
        passwordTi.setText(teachers.getPassword());
        dobTi.setText(teachers.getDob());
        numberTi.setText(teachers.getNumber());
        genderTi.setText(teachers.getGender());

        submitTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userName = userNameTi.getText().toString();
                staffID = staffIDTi.getText().toString();
                emailAddress = emailAddressTi.getText().toString();
                teachingModel = teachingModelTi.getText().toString();
                password = passwordTi.getText().toString();
                dob = dobTi.getText().toString();
                number = numberTi.getText().toString();
                gender = genderTi.getText().toString();

                if(teachers.getId() != null){
                    deleteTeachers(teachers.getId());

                }

                Teachers teachersadd = new Teachers(userName,staffID,emailAddress,teachingModel,password,dob,number,gender);
                updateorAddTeachers(teachersadd);


                Toast toast3 = Toast.makeText(getApplicationContext(), "Operation Successful", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminAddTeachersActivity.this, AdminHome.class);

                startActivityForResult(intent, 4);

            }
        });

    }

    public void updateorAddTeachers(Teachers teachers){

        Call<Teachers> call  = ApiClient.getTeachersService().createTeacher(teachers);

        call.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {

                Teachers getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminAddTeachersActivity.this,"Successfully Added Teacher",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) {

            }
        });
    }
    public void deleteTeachers(int id){

        Call<Teachers> call  = ApiClient.getTeachersService().deleteTeacher(id);

        call.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {


            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) {

            }
        });
    }
}