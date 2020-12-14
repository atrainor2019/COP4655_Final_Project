package com.andrewtrainor.compoundapp.UserData;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.andrewtrainor.compoundapp.BusinessData.Driver;
import com.andrewtrainor.compoundapp.R;
import com.andrewtrainor.compoundapp.SecondaryActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private static Context context;
    EditText mEmail, mPassword;
    Button mLoginBtn, mRegisterbtn;
    TextView mCreateBtn, forgotTextLink;
    FirebaseAuth fAuth;

    private static Driver driver = new Driver();
    private Profile profile;

    //on Activity creation, outline layouts and set up onclicks to ping when the user wants to log in.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();

        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        fAuth = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);

        //On click, the user is sent to the Registration Activity. This is for the case that the user
        //is wrongly at the login screen and does not have an account.
        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Register.class));
            }
        });

        //On click for login, error checking for if the user did not enter an email or password
        //Password must be greater than 6 characters.
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                //Error checking for empty Email, empty password and Password Length.
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required.");
                    return;
                }

                if(password.length()< 6){
                    mPassword.setError("Password must be greater than 6 characters.");
                    return;
                }

                //Call signInWithEmailAndPassword to authenticate user sign in
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //User Sign in is successful
                        if(task.isSuccessful()){
                            profile = driver.create(email, password);
                            Toast.makeText(Login.this, "User sucessfully logged in.", Toast.LENGTH_SHORT).show();

                            //Prepare and send the user to the next activity to access application features.
                            Intent intent = new Intent(context, SecondaryActivity.class);
                            intent.putExtra("profileobj", profile);
                            startActivity(intent);

                            //login error
                        }else {
                            Toast.makeText(Login.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }
}
