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
import com.application.tms.models.Classes;
import com.application.tms.models.Holidays;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAddClassesActivity extends AppCompatActivity {

    String date,day,time,endTime,venue,lecturerName,batch,module,degreeType;
    TextInputEditText dateTi,dayTi,timeTi,endTimeTi,venueTi,lecturerNameTi,batchTi,moduleTi,degreeTypeTi;
    Toolbar toolbar;
    Button submitClass;
    public void init(){
        toolbar = findViewById(R.id.toolbar);

        dateTi = findViewById(R.id.dateOne);
        dayTi = findViewById(R.id.dayTwo);
        timeTi = findViewById(R.id.startTimeThree);
        endTimeTi = findViewById(R.id.endTimeFour);
        venueTi = findViewById(R.id.venueFive);
        lecturerNameTi = findViewById(R.id.lecturerNameSix);
        batchTi = findViewById(R.id.batchSeven);
        moduleTi = findViewById(R.id.moduleEight);
        degreeTypeTi = findViewById(R.id.degreeTypeNine);
        submitClass = findViewById(R.id.addClassesBtn);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_classes);
        init();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Classes");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Intent intent = getIntent();
        Classes classes = (Classes) intent.getSerializableExtra("data");

        dateTi.setText(classes.getDate());
        dayTi.setText(classes.getDay());
        timeTi.setText(classes.getTime());
        endTimeTi.setText(classes.getEndTime());
        venueTi.setText(classes.getVenue());
        lecturerNameTi.setText(classes.getLecturerName());
        batchTi.setText(classes.getBatch());
        moduleTi.setText(classes.getModule());
        degreeTypeTi.setText(classes.getDegreeType());

        submitClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                date = dateTi.getText().toString();
                day = dayTi.getText().toString();
                time = timeTi.getText().toString();
                endTime = endTimeTi.getText().toString();
                venue = venueTi.getText().toString();
                lecturerName = lecturerNameTi.getText().toString();
                batch = batchTi.getText().toString();
                module = moduleTi.getText().toString();
                degreeType = degreeTypeTi.getText().toString();


                if(classes.getId() != null){
                    deleteClasses(classes.getId());

                }

                Classes classesadd = new Classes(date,day,time,endTime,venue,lecturerName,batch,module,degreeType);
                updateorAddClasses(classesadd);


                Toast toast3 = Toast.makeText(getApplicationContext(), "Operation Successful", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();

                Intent intent = new Intent(AdminAddClassesActivity.this, AdminHome.class);

                startActivityForResult(intent, 1);

            }
        });

    }

    public void updateorAddClasses(Classes classes){

        Call<Classes> call  = ApiClient.getClassesService().createClass(classes);

        call.enqueue(new Callback<Classes>() {
            @Override
            public void onResponse(Call<Classes> call, Response<Classes> response) {

                Classes getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminAddClassesActivity.this,"Successfully Added Class",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Classes> call, Throwable t) {

            }
        });
    }
    public void deleteClasses(int id){

        Call<Classes> call  = ApiClient.getClassesService().deleteClasses(id);

        call.enqueue(new Callback<Classes>() {
            @Override
            public void onResponse(Call<Classes> call, Response<Classes> response) {


            }

            @Override
            public void onFailure(Call<Classes> call, Throwable t) {

            }
        });
    }
}