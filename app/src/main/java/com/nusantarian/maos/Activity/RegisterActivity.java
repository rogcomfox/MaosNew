package com.nusantarian.maos.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nusantarian.maos.R;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText et_name, et_email, et_password, et_confpass, et_phone;
    private ProgressBar progressBar;
    private String name, email, phone, password, confpass, uid;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        et_phone = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        et_confpass = findViewById(R.id.et_confpass);
        progressBar = findViewById(R.id.progressbar);
        Button btn_register = findViewById(R.id.btn_register);
        checkBox = findViewById(R.id.checkbox);

        progressBar = new ProgressBar(RegisterActivity.this);
        progressBar.setIndeterminate(true);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = et_name.getText().toString();
                email = et_email.getText().toString();
                phone = et_phone.getText().toString();
                password = et_password.getText().toString();
                confpass = et_confpass.getText().toString();
                boolean checked = checkBox.isChecked();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(email) && TextUtils.isEmpty(phone) && TextUtils.isEmpty(password) && TextUtils.isEmpty(confpass)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Please Fill Your Identity", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(name)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Please Fill Your Name", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(email)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Please Fill Your Email", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(phone)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Please Fill Your Phone Number", Toast.LENGTH_SHORT).show();
                }
                else if (TextUtils.isEmpty(password)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Please Fill Your Password", Toast.LENGTH_SHORT).show();
                }
                else if (!password.equals(confpass)){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Password didn't match", Toast.LENGTH_SHORT).show();
                }
                else if (password.length() < 6){
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getProgress();
                    Toast.makeText(RegisterActivity.this, "Password Too Short, At Least 6 Characters", Toast.LENGTH_SHORT).show();
                }else {
                    if (!checked) {
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.getProgress();
                        Toast.makeText(RegisterActivity.this, "Please Accept License and Agreement", Toast.LENGTH_SHORT).show();
                    }else{
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
                                    progressBar.setVisibility(View.VISIBLE);
                                    progressBar.getProgress();
                                    mDatabase.child("name").setValue(name);
                                    mDatabase.child("phone").setValue(phone);
                                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                                    finish();
                                }else {
                                    progressBar.setVisibility(View.VISIBLE);
                                    progressBar.getProgress();
                                    Toast.makeText(RegisterActivity.this, "Failed To Create User", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
