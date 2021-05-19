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
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.models.ApiClient;
import com.application.tms.adapters.ClassesAdapter;
import com.application.tms.models.Classes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminManageTimetable extends AppCompatActivity implements ClassesAdapter.ClickedItem {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ClassesAdapter usersAdapter;
    public static Boolean setBtnEdit = false;


    public void init() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleradmintimetable);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manage_timetable);

        init();

        setBtnEdit = true;

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        usersAdapter = new ClassesAdapter(this::ClickedClass);

        getAllClasses();


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


                AlertDialog.Builder builder = new AlertDialog.Builder(AdminManageTimetable.this);
                builder.setMessage("Are you sure to delete?");
                builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        int s = viewHolder.getAdapterPosition();
                        int getidofitem = usersAdapter.deleteClassCall(s);

                        deleteClasses(getidofitem);

                        System.out.println("xxxxxxxxxxxxxxxxxxxx "+s);
                        Toast.makeText(AdminManageTimetable.this,"delete",Toast.LENGTH_LONG).show();


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


    public void getAllClasses() {

        ProgressDialog pDialog;
        pDialog = new ProgressDialog(AdminManageTimetable.this);
        pDialog.setMessage("Loading Data.. Please wait...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        Call<List<Classes>> classeslist = ApiClient.getClassesService().getAllClasses();

        List<Classes> batchNameList = new ArrayList<>();

        classeslist.enqueue(new Callback<List<Classes>>() {

            @Override
            public void onResponse(Call<List<Classes>> call, Response<List<Classes>> response) {
                try {
                    if (response.isSuccessful()) {
                        List<Classes> userResponses = response.body();
                        List<Classes> sortedClasses = new ArrayList<>();
                        for (Classes r : userResponses) {
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
            public void onFailure(Call<List<Classes>> call, Throwable t) {
                Log.e("failure", t.getLocalizedMessage());

            }
        });
    }
    @Override
    public void ClickedClass(Classes userResponse) {

        startActivity(new Intent(this, ClassesDetailsActivity.class).putExtra("data", userResponse));

    }

    public void deleteClasses(int id){

        Call<Classes> call  = ApiClient.getClassesService().deleteClasses(id);

        call.enqueue(new Callback<Classes>() {
            @Override
            public void onResponse(Call<Classes> call, Response<Classes> response) {

                Classes getresponse = response.body();
                System.out.println("response code "+ getresponse);
                System.out.println("response code "+ response.code());
                Toast.makeText(AdminManageTimetable.this,"Successfully Deleted",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Classes> call, Throwable t) {

            }
        });
    }


}