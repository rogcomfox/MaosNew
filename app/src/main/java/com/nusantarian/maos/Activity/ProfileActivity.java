package com.nusantarian.maos.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nusantarian.maos.R;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private String uid, name, email, birth, status, date;
    private TextView tv_txtname, tv_txtbirth, tv_txtxstatus, tv_date, tv_name, tv_email;
    private CircleImageView img_profpic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.my_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_name = findViewById(R.id.tv_name);
        tv_email = findViewById(R.id.tv_email);
        tv_txtname = findViewById(R.id.tv_txtname);
        tv_txtbirth = findViewById(R.id.tv_txtbirth);
        tv_txtxstatus = findViewById(R.id.tv_txtstatus);
        tv_date = findViewById(R.id.tv_date);
        img_profpic = findViewById(R.id.img_profpic);

        mAuth = FirebaseAuth.getInstance();
        uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
//        mStorage = FirebaseStorage.getInstance().getReference().child("Profil Images").child(uid);
//
//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//                    name = Objects.requireNonNull(dataSnapshot.getValue()).toString();
//                    email = mAuth.getCurrentUser().getEmail();
//
//                    tv_name.setText(name);
//                    tv_email.setText(email);
//                }else {
//                    tv_name.setText("-");
//                    tv_email.setText("-");
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        mDatabase.child("Patients").child(date).child("").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()){
//
//                }else{
//                    tv_txtname.setText("-");
//                    tv_txtbirth.setText("-");
//                    tv_txtxstatus.setText("-");
//                    tv_date.setText(R.string.null_date);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_pass:
                startActivity(new Intent(ProfileActivity.this, UpdatePassActivity.class));
                break;
            case R.id.nav_editprofil:
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
