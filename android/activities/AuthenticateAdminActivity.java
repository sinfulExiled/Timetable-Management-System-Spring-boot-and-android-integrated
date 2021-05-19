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
import com.application.tms.models.Users;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticateAdminActivity extends AppCompatActivity {
    Button nextbtn;
    String getAdminEmail, getAdminPassword;
    TextInputEditText adminnameTv, adminpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate_admin);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(this.getResources().getColor(R.color.colorPrimary));
        }
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        adminnameTv = (TextInputEditText) findViewById(R.id.adminNameAuthenticate);
        adminpass = findViewById(R.id.adminPasswordAuthenticate);
        nextbtn = findViewById(R.id.nextAdminButtonAuthenticate);
        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAdminEmail = adminnameTv.getText().toString().trim();
                getAdminPassword = adminpass.getText().toString().trim();

                ProgressDialog pDialog;
                pDialog = new ProgressDialog(AuthenticateAdminActivity.this);
                pDialog.setMessage("Loading Data.. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Call<List<Users>> Adminslist = ApiClient.getUsersServices().getAllUsers();
                List<Users> adminList = new ArrayList<>();
                Adminslist.enqueue(new Callback<List<Users>>() {
                    @Override
                    public void onResponse(Call<List<Users>> call, Response<List<Users>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Users> userResponses = response.body();
                                for (Users t : userResponses) {
                                    String adminname = t.getUserName();
                                    String password = t.getPassword();
                                    String role = t.getRoles();

                                    Users adminObj = new Users(adminname, password, role);
                                    adminList.add(adminObj);
                                }
                                pDialog.dismiss();
                                String getEmail = "";
                                String getPassword = "";
                                for (Users list : adminList) {

                                    getEmail = list.getUserName().trim();
                                    getPassword = list.getPassword().trim();
                                    if (getAdminEmail.equals(list.getUserName().trim()) && getAdminPassword.equals(list.getPassword().trim())) {
                                        Toast toast3 = Toast.makeText(getApplicationContext(), "Admin Home", Toast.LENGTH_SHORT);
                                        toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                        toast3.show();
                                        Intent intent6 = new Intent(AuthenticateAdminActivity.this, AdminHome.class);
                                        AuthenticateAdminActivity.this.startActivity(intent6);
                                        break;
                                    }
                                }


                                if (!getAdminEmail.equals(getEmail) && getAdminPassword.equals(getPassword)) {
                                    Toast toast3 = Toast.makeText(getApplicationContext(), "Incorrect Admin Email", Toast.LENGTH_SHORT);
                                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast3.show();
                                }

                                if (getAdminEmail.equals(getEmail) && !getAdminPassword.equals(getPassword)) {
                                    Toast toast3 = Toast.makeText(getApplicationContext(), "Incorrect Password", Toast.LENGTH_SHORT);
                                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast3.show();
                                }

                                if (!getAdminEmail.equals(getEmail) && !getAdminPassword.equals(getPassword)) {
                                    Toast toast3 = Toast.makeText(getApplicationContext(), "This forum is admin access only", Toast.LENGTH_SHORT);
                                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast3.show();


                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Users>> call, Throwable t) {
                        Log.e("failure", t.getLocalizedMessage());
                    }
                });


            }
        });

    }
}