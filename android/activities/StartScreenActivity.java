package com.application.tms.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.application.tms.R;
import com.application.tms.activities.MainActivity;
import com.application.tms.models.ApiClient;
import com.application.tms.models.Students;
import com.application.tms.models.Users;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartScreenActivity extends AppCompatActivity {


    Button signUp;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        mAuth = FirebaseAuth.getInstance();

        signUp = findViewById(R.id.signUp);
        firebaseUser = mAuth.getCurrentUser();

        if(firebaseUser != null){
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(StartScreenActivity.this, StudentRegistration.class);

                startActivityForResult(intent, 1);


            }
        });


        mGoogleSignInClient = GoogleSignIn.getClient(this,gso);
        signin = (SignInButton) findViewById(R.id.sign_in_button);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mGoogleSignInClient.signOut();


                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);

            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);

                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately

                e.printStackTrace();
                // ...
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {


        AuthCredential credential =  GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task< AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            String personEmail = acct.getEmail();
                            System.out.println("qqqqqqqq "+personEmail);
                            ValidateUser(personEmail);
                            //FirebaseUser user = mAuth.getCurrentUser();


                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    public void ValidateUser(String gEmail){


                ProgressDialog pDialog;
                pDialog = new ProgressDialog(StartScreenActivity.this);
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
                                String useremail = "d";
                                for (Users t : userResponses) {
                                    useremail = t.getUserName();

                                    System.out.println(useremail);
                                    if(useremail.equals(gEmail)){
                                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                                        startActivity(i);
                                        finish();
                                        Toast.makeText(getApplicationContext(),"User Logged In Successfully",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                pDialog.dismiss();



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

}


