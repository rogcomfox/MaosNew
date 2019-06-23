package com.nusantarian.maos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.nusantarian.maos.R;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText et_email, et_password;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        TextView tv_forgot = findViewById(R.id.tv_forgot);
        Button btn_login = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressbar);

        progressBar = new ProgressBar(LoginActivity.this);
        progressBar.setIndeterminate(true);


        tv_forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, ForgotActivity.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.getProgress();
                
                if (TextUtils.isEmpty(email) && TextUtils.isEmpty(password)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(LoginActivity.this, "Please Fill Your Identity", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(email)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(LoginActivity.this, "Please Fill Your Email", Toast.LENGTH_LONG).show();
                }
                else if (TextUtils.isEmpty(password)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(LoginActivity.this, "Please Fill Your Password", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(LoginActivity.this, "Password Too Short, At Least 6 Characters", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                progressBar.setVisibility(View.VISIBLE);
                                progressBar.getProgress();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }else {
                                progressBar.setVisibility(View.VISIBLE);
                                progressBar.getProgress();
                                Toast.makeText(LoginActivity.this, "Failed To Sign In, Please Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
