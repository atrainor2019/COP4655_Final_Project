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

public class Register extends AppCompatActivity {
    private static Context context;
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;

    private static Driver driver = new Driver();
    private Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = getApplicationContext();

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.email3);
        mPassword = findViewById(R.id.password3);
        mPhone = findViewById(R.id.phone2);
        mRegisterBtn = findViewById(R.id.createText);
        mLoginBtn = findViewById(R.id.createText2);

        //Firebase Auth
        fAuth = FirebaseAuth.getInstance();

        //Onclick Listener for if the user wants to login instead and does not need to register.

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        if(fAuth.getCurrentUser() != null){
            FirebaseAuth.getInstance().signOut();
            Intent i = new Intent(getApplication(), Register.class);
            startActivity(i);
            finish();
        }

        //set onclick listener for User Registration and verify fields
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

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

                //create the User with Email and password
                fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        //registration is successful
                        if(task.isSuccessful()){
                            profile = driver.create(email, password);
                            Toast.makeText(Register.this, "User Created.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(context, SecondaryActivity.class);
                            intent.putExtra("profileobj", profile);
                            startActivity(intent);

                        //Registration error
                        }else{
                            Toast.makeText(Register.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }

                    }
                });
            }
        });
    }
}
