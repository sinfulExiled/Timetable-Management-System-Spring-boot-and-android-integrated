package com.application.tms.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Teachers;
import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    ImageView imageView;
    TextView username, useremail;

    public static String user;

    FirebaseAuth auth;
    String personEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button studentB = findViewById(R.id.buttonStudent);
        Button teacherB = findViewById(R.id.buttonTeacher);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                //.setDrawerLayout(drawer)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        /* navigationView.bringToFront(); */


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {

                    case R.id.nav_home:
                        Toast toast = Toast.makeText(getApplicationContext(), "Home", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast.show();

                        break;

                    case R.id.nav_gallery:
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Student Home", Toast.LENGTH_SHORT);
                        toast2.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast2.show();

                        Intent intent1 = new Intent(MainActivity.this, StudentHomeActivity.class);
                        MainActivity.this.startActivity(intent1);
                        break;

                    case R.id.nav_slideshow:
                        Toast toast3 = Toast.makeText(getApplicationContext(), "Teacher Home", Toast.LENGTH_SHORT);
                        toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast3.show();
                        break;
                    case R.id.nav_admin:
                        Toast toast4 = Toast.makeText(getApplicationContext(), "Admin LogIm", Toast.LENGTH_SHORT);
                        toast4.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                        toast4.show();

                        Intent intent6 = new Intent(MainActivity.this, AuthenticateAdminActivity.class);
                        MainActivity.this.startActivity(intent6);

                        break;


                }

                drawer.closeDrawers();
                return false;
            }
        });


        View navView = navigationView.getHeaderView(0);
        //reference to views


        username = (TextView) navView.findViewById(R.id.tName);
        useremail = (TextView) navView.findViewById(R.id.tEmail);
        imageView = (ImageView) navView.findViewById(R.id.imageViewNav);
        //TextView username = findViewById(R.id.tName);
        //TextView useremail = findViewById(R.id.tEmail);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            username.setText(personName);
            useremail.setText(personEmail);

            user = personName;

            // Glide.with(getApplicationContext()).load(personPhoto).into(imageViewn);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
            //Glide.with(this).load("http://goo.gl/gEgYUd").into(imageView);

            // navigationView.setNavigationItemSelectedListener(this);

        }

        studentB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast3 = Toast.makeText(getApplicationContext(), "Student Home", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();
                Intent intent6 = new Intent(MainActivity.this, StudentHomeActivity.class);
                MainActivity.this.startActivity(intent6);
            }
        });

        teacherB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog pDialog;
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setMessage("Loading Data.. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(false);
                pDialog.show();
                Call<List<Teachers>> Teacherslist = ApiClient.getTeachersService().getAllTeachers();
                List<Teachers> teacherListlog = new ArrayList<>();
                Teacherslist.enqueue(new Callback<List<Teachers>>() {
                    @Override
                    public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
                        try {
                            if (response.isSuccessful()) {
                                List<Teachers> userResponseslog = response.body();
                                for (Teachers t : userResponseslog) {
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
                                    teacherListlog.add(teacherObj);
                                }
                                pDialog.dismiss();
                                String getEmail = "";
                                String getUserName = "";
                                for(Teachers list : teacherListlog){

                                     getEmail = list.getEmailAddress();
                                    getUserName = list.getUserName();

                                    System.out.println("pppppppppp "+getEmail+" "+getUserName);
                                }
                                System.out.println("yyyyyyyy "+getEmail+" "+personEmail);
                                if(getEmail.equals(personEmail)){
                                    String message = "Welcome "+getUserName+" sir";
                                    AuthenticateTeacherActivity.authuserteacher = user;
                                    Toast toast3 = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast3.show();
                                    Intent intent1 = new Intent(MainActivity.this, TeacherHomeActivity.class);
                                    MainActivity.this.startActivity(intent1);

                                }
                                if(!getEmail.equals(personEmail)){
                                    String message = "Please Log in "+getUserName;

                                    Toast toast3 = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
                                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                                    toast3.show();
                                    Intent intent1 = new Intent(MainActivity.this, AuthenticateTeacherActivity.class);
                                    MainActivity.this.startActivity(intent1);

                                }

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    @Override
                    public void onFailure(Call<List<Teachers>> call, Throwable t) {

                    }
                });
            }
        });


        Button signout = (Button) navView.findViewById(R.id.signoutButton);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    // ...
                    case R.id.signoutButton:
                        signOut();
                        break;
                    // ...
                }


            }
        });


    }


    private void signOut() {

        auth.getInstance().signOut();
        finish();


        Intent finishlogout = new Intent(this, StartScreenActivity.class);
        startActivity(finishlogout);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
