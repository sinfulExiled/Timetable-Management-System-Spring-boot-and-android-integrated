package com.application.tms.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Teachers;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateTeacherActivity extends AppCompatActivity {

    Button nextbtn;
    String getteacherEmail,getTeacherPassword;
    TextInputEditText adminnameTv,adminpass;
    public static String authuserteacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_teacher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        adminnameTv = (TextInputEditText) findViewById(R.id.teacherNameAuthenticate);
        adminpass = findViewById(R.id.teacherPasswordAuthenticate);
        nextbtn = findViewById(R.id.nextButtonAuthenticate);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getteacherEmail = adminnameTv.getText().toString().trim();
                getTeacherPassword = adminpass.getText().toString().trim();

                ProgressDialog pDialog;
                pDialog = new ProgressDialog(AuthenticateTeacherActivity.this);
                pDialog.setMessage("Loading Data.. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Call<List<Teachers>> Teacherslist = ApiClient.getTeachersService().getAllTeachers();
                List<Teachers> teacherList = new ArrayList<>();
                Teacherslist.enqueue(new Callback<List<Teachers>>() {
                    @Override
                    public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Teachers> userResponses = response.body();
                                for (Teachers t : userResponses) {
                                    String teachername = t.getUserName();
                                    String staffID = t.getStaffID();
                                    String teacherEmail = t.getEmailAddress();
                                    String teachingModel = t.getTeachingModel();
                                    String password = t.getPassword();
                                    String dob = t.getDob();
                                    String number = t.getNumber();
                                    String gender = t.getGender();
                                    Teachers teacherObj = new Teachers(teachername, staffID, teacherEmail, teachingModel, password, dob, number
                                            , gender);
                                    teacherList.add(teacherObj);
                                }
                                pDialog.dismiss();
                                String getEmail = "";
                                String getPassword = "";
                                for(Teachers list : teacherList){
                                    getEmail = list.getEmailAddress().trim();
                                    getPassword = list.getPassword().trim();
                                    System.out.println("print getteacherEmail:" + getteacherEmail);
                                    System.out.println("print getTeacherPassword:" + getTeacherPassword);

                                    System.out.println("print getEmail:" + getEmail);
                                    System.out.println("print getPassword:" + getPassword);
                                    if(getteacherEmail.equals(list.getEmailAddress()) && getTeacherPassword.equals(list.getPassword())){
                                        authuserteacher = list.getUserName();
                                        Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Home", Toast.LENGTH_SHORT);
                                        toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast3.show();
                                        Intent intent6 = new Intent(AuthenticateTeacherActivity.this, TeacherHomeActivity.class);
                                        AuthenticateTeacherActivity.this.startActivity(intent6);
                                    }

                                }



                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onFailure(Call<List<Teachers>> call, Throwable t) {
                        Log.e("failure", t.getLocalizedMessage());
                    }
                });



            }
        });

    }
}