package com.nusantarian.maos.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.nusantarian.maos.R;

public class UpdatePassActivity extends AppCompatActivity {

    private FirebaseUser mUser;
    private EditText et_newpass, et_confpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pass);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        et_newpass = findViewById(R.id.et_newpass);
        et_confpass = findViewById(R.id.et_confpass);
        Button btn_update = findViewById(R.id.btn_update);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newpass = et_newpass.getText().toString();
                String confpass = et_confpass.getText().toString();

                if (TextUtils.isEmpty(newpass) || TextUtils.isEmpty(confpass)){
                    Toast.makeText(UpdatePassActivity.this, R.string.please_fill_your_new_password, Toast.LENGTH_SHORT).show();
                }
                else if (!newpass.equals(confpass)){
                    Toast.makeText(UpdatePassActivity.this, "Password Didn't Match", Toast.LENGTH_SHORT).show();
                }
                else if (newpass.length() < 6){
                    Toast.makeText(UpdatePassActivity.this, "Password Too Short, At Least 6 Characters", Toast.LENGTH_SHORT).show();
                }else {
                    mUser.updatePassword(newpass).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(UpdatePassActivity.this, "Password Successfully Change", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(UpdatePassActivity.this, "Failed To Change Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
