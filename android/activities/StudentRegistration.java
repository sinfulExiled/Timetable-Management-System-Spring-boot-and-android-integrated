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
import com.application.tms.models.Students;
import com.application.tms.models.Holidays;
import com.application.tms.models.Students;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentRegistration extends AppCompatActivity {

    String username,studentRef,emailaddress,courseStudy,password,DOB,number,gender;
    TextInputEditText usernameT,studentRefT,emailaddressT,courseStudyT,passwordT,DOBT,numberT,genderT;
    Toolbar toolbar;
    Button submitStudent;
    public void init(){
        toolbar = findViewById(R.id.toolbar);

        usernameT = findViewById(R.id.username);
        studentRefT = findViewById(R.id.studentRef);
        emailaddressT = findViewById(R.id.emailaddress);
        courseStudyT = findViewById(R.id.courseStudy);
        passwordT = findViewById(R.id.password);
        DOBT = findViewById(R.id.DOB);
        numberT = findViewById(R.id.number);
        genderT = findViewById(R.id.gender);
        submitStudent = findViewById(R.id.registerBtn);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Register");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);



        submitStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernameT.getText().toString();
                studentRef = studentRefT.getText().toString();
                emailaddress = emailaddressT.getText().toString();
                courseStudy = courseStudyT.getText().toString();
                password = passwordT.getText().toString();
                DOB = DOBT.getText().toString();
                number = numberT.getText().toString();
                gender = genderT.getText().toString();
                System.out.println("aaaaaaaaa"+username);
                System.out.println("aaaaaaaaa"+studentRef);
                System.out.println("aaaaaaaaa"+emailaddress);
                System.out.println("aaaaaaaaa"+courseStudy);
                System.out.println("aaaaaaaaa"+password);
                Students Studentsadd = new Students(username,studentRef,emailaddress,courseStudy,password,DOB,number,gender);
                updateorAddStudents(Studentsadd);


                Toast toast3 = Toast.makeText(getApplicationContext(), "Operation Successful", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(StudentRegistration.this, StartScreenActivity.class);

                startActivityForResult(intent, 1);

            }
        });

    }

    public void updateorAddStudents(Students students){

        System.out.println("zzzzzzzzzzzzzzzz"+students.getUserName());
        Call<Students> call  = ApiClient.getStudentService().createStudent(students);

        call.enqueue(new Callback<Students>() {
            @Override
            public void onResponse(Call<Students> call, Response<Students> response) {

                Students getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(StudentRegistration.this,"Successfully Added Student",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Students> call, Throwable t) {

            }
        });
    }

}