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

public class LoginActivity extends AppCompatActivity {
    private EditText username , password;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_pass);
        buttonLogin = findViewById(R.id.button_login);

        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Intent intent = new Intent(this , ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void goRegisterActivity(View view) {
        Intent intent = new Intent(LoginActivity.this , SignupActivity.class);
        startActivity(intent);
        finish();
    }

    private void HandleLogin(){
        FirebaseAuth.getInstance().signInWithEmailAndPassword(username.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this , HomeActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void goHome(View view) {
        HandleLogin();
    }
}