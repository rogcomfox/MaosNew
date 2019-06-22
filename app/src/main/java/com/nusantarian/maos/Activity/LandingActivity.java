package com.nusantarian.maos.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nusantarian.maos.R;

public class LandingActivity extends AppCompatActivity {

    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        Button btn_login = findViewById(R.id.btn_login);
        Button btn_register = findViewById(R.id.btn_register);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, LoginActivity.class));
                finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LandingActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (user != null){
            startActivity(new Intent(LandingActivity.this, MainActivity.class));
            finish();
        }
    }
}
