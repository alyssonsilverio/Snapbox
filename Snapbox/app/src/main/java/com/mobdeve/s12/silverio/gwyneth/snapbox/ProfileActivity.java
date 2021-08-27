package com.mobdeve.s12.silverio.gwyneth.snapbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;


public class ProfileActivity extends AppCompatActivity {

    private ImageButton btnUpload;
    private Button btnLogOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initComponents();
    }

    private void initComponents() {
        this.btnUpload = findViewById(R.id.btn_profile_upload);
        this.btnLogOut = findViewById(R.id.btn_profile_logout);
        this.mAuth = FirebaseAuth.getInstance();

        this.btnUpload.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, UploadActivity.class);
                startActivity(i);
                finish();
            }
        });

        this.btnLogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                signOut();
            }
        });
    }

    private void signOut() {
        this.mAuth.signOut();
        Toast.makeText(this, "Successfully signed out.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }
}