package com.example.audiochat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private EditText username , password , email;
    private Button register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        username = findViewById(R.id.signup_username_editText);
        password = findViewById(R.id.signup_pass_editText);
        email = findViewById(R.id.signup_email_editText);

        register = findViewById(R.id.button_register);


    }

    public void goLogin(View view) {
        Intent intent = new Intent(SignupActivity.this , LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void HandleSignUp(){
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseDatabase.getInstance().getReference("Users/"+FirebaseAuth.getInstance().getCurrentUser()
                            .getUid()).setValue(new User(
                        username.getText().toString(),
                            email.getText().toString(),
                            ""
                    ));
                    Toast.makeText(SignupActivity.this, "Account is registered",Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(SignupActivity.this  , LoginActivity.class));
                }else{
                    Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void registerUser(View view) {
        HandleSignUp();

    }
}