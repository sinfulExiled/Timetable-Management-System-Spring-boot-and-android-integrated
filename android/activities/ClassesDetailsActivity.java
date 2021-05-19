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
import com.application.tms.models.Classes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ClassesDetailsActivity extends AppCompatActivity {

    Classes classes;
    TextView date,day,time,endtime,venue,lecturerName,batch,module,degreeType;
    Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_details);

        date  = findViewById(R.id.date);
        day  = findViewById(R.id.day);
        time  = findViewById(R.id.time);
        endtime  = findViewById(R.id.end);
        venue  = findViewById(R.id.venue);
        lecturerName  = findViewById(R.id.lecturerName);
        batch  = findViewById(R.id.batch);
        module  = findViewById(R.id.module);
        degreeType  = findViewById(R.id.degreeType);

        editBtn  = findViewById(R.id.editClassesBtn);
       editBtn.setVisibility(View.INVISIBLE);
        boolean btnEditRestponce = AdminManageTimetable.setBtnEdit;

        System.out.println("zzzzzzzzzz "+btnEditRestponce);
        if(btnEditRestponce == true){
            editBtn.setVisibility(View.VISIBLE);
            AdminManageTimetable.setBtnEdit = false;
        }

        Intent intent = getIntent();
        if(intent.getExtras() !=null){
            classes = (Classes) intent.getSerializableExtra("data");

            String SDate = classes.getDate();
            String Sday = classes.getDay();
            String Stime = classes.getTime();
            String SendTime = classes.getEndTime();

            System.out.println("ssssssssssssss "  + classes.toString());
            String Svenue = classes.getVenue();
            String SlecturerName = classes.getLecturerName();
            String Sbatch = classes.getBatch();
            String Smodule = classes.getModule();
            String SdegreeType = classes.getDegreeType();


            date.setText(SDate);
            day.setText(Sday);
            time.setText(Stime);
            endtime.setText(SendTime);
            venue.setText(Svenue);
            lecturerName.setText(SlecturerName);
            batch.setText(Sbatch);
            module.setText(Smodule);
            degreeType.setText(SdegreeType);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast3 = Toast.makeText(getApplicationContext(), "Edit Classes", Toast.LENGTH_SHORT);
                    toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                    toast3.show();

                    startActivity(new Intent(ClassesDetailsActivity.this, AdminAddClassesActivity.class).putExtra("data", classes));

                }
            });



        }

    }


}