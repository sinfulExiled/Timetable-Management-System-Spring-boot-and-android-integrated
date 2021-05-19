package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Teachers;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeachersDetailsActivity extends AppCompatActivity {

    Teachers teachers;
    TextView userName,staffID,emailAddress,teachingModel,password,dob,number,gender;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers_details);

        userName  = findViewById(R.id.userName);
        staffID  = findViewById(R.id.staffID);
        emailAddress  = findViewById(R.id.emailAddress);
        teachingModel  = findViewById(R.id.teachingModel);
        password  = findViewById(R.id.password);
        dob  = findViewById(R.id.dob);
        number  = findViewById(R.id.number);
        gender  = findViewById(R.id.gender);

        editBtn  = findViewById(R.id.editTeacherBtn);

        boolean btnEditRestponce = AdminManageTimetable.setBtnEdit;

        System.out.println("zzzzzzzzzz "+btnEditRestponce);


        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            teachers = (Teachers) intent.getSerializableExtra("data");

            String SuserName = teachers.getUserName();
            String SstaffID = teachers.getStaffID();
            String SemailAddress = teachers.getEmailAddress();
            String SteachingModel = teachers.getTeachingModel();

            System.out.println("ssssssssssssss "  + teachers.toString());
            String Spassword = teachers.getPassword();
            String Sdob = teachers.getDob();
            String Snumber = teachers.getNumber();
            String Sgender = teachers.getGender();



            userName.setText(SuserName);
            staffID.setText(SstaffID);
            emailAddress.setText(SemailAddress);
            teachingModel.setText(SteachingModel);
            password.setText(Spassword);
            dob.setText(Sdob);
            number.setText(Snumber);
            gender.setText(Sgender);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Edit Teachers", Toast.LENGTH_SHORT);
                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast3.show();

                    startActivity(new Intent(TeachersDetailsActivity.this, AdminAddTeachersActivity.class).putExtra("data", teachers));

                }
            });



        }

    }


}