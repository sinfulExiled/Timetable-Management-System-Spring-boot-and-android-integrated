package com.application.tms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.adapters.ClassesAdapter;
import com.application.tms.adapters.TeachersAdapter;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Classes;
import com.application.tms.models.Teachers;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminTeacherManagement extends AppCompatActivity implements TeachersAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    TeachersAdapter usersAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_teacher_management);
        recyclerView = findViewById(R.id.recycler_view);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        usersAdapter = new TeachersAdapter(this::ClickedClass);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Admin Menu");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        Button toolbaraddBtn = findViewById(R.id.toolbaraddBtn);
        toolbaraddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Teachers teacheradd = new Teachers();
                startActivity(new Intent(AdminTeacherManagement.this, AdminAddTeachersActivity.class).putExtra("data", teacheradd));

                Toast toast3 = Toast.makeText(getApplicationContext(), "Add Teacher", Toast.LENGTH_SHORT);
                toast3.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast3.show();


            }
        });

        getAllTeachers();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                AlertDialog.Builder builder = new AlertDialog.Builder(AdminTeacherManagement.this);
                builder.setMessage("Are you sure to delete?");
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        int s = viewHolder.getAdapterPosition();
                        int getidofitem = usersAdapter.deleteClassCall(s);

                        deleteTeachers(getidofitem);


                        System.out.println("xxxxxxxxxxxxxxxxxxxx "+getidofitem);
                        System.out.println("xxxxxxxxxxxxxxxxxxxx "+s);
                        Toast.makeText(AdminTeacherManagement.this,"delete",Toast.LENGTH_LONG).show();


                        return;
                    }
                }).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {  //not removing items if cancel is done
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//
                        usersAdapter.notifyItemRemoved(viewHolder.getAdapterPosition() + 1);
                        usersAdapter.notifyItemRangeChanged(viewHolder.getAdapterPosition(), usersAdapter.getItemCount());

                        return;
                    }
                }).show();  //show alert dialog

            }
        }).attachToRecyclerView(recyclerView);


    }


    public void getAllTeachers() {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(AdminTeacherManagement.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Teachers>> teacherslist = ApiClient.getTeachersService().getAllTeachers();

        List<Teachers> batchNameList = new ArrayList<>();

        teacherslist.enqueue(new Callback<List<Teachers>>() {

            @Override
            public void onResponse(Call<List<Teachers>> call, Response<List<Teachers>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Teachers> userResponses = response.body();
                        List<Teachers> sortedTeachers = new ArrayList<>();
                        for (Teachers r : userResponses) {
                            batchNameList.add(r);
                        }
                        usersAdapter.setData(batchNameList);
                        recyclerView.setAdapter(usersAdapter);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                pDialog.dismiss();

            }

            @Override
            public void onFailure(Call<List<Teachers>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    }

    @Override
    public void ClickedClass(Teachers userResponse) {

        startActivity(new Intent(this, TeachersDetailsActivity.class).putExtra("data", userResponse));

    }

    public void deleteTeachers(int id){

        Call<Teachers> call  = ApiClient.getTeachersService().deleteTeacher(id);

        call.enqueue(new Callback<Teachers>() {
            @Override
            public void onResponse(Call<Teachers> call, Response<Teachers> response) {

                Teachers getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminTeacherManagement.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Teachers> call, Throwable t) {

            }
        });
    }


}