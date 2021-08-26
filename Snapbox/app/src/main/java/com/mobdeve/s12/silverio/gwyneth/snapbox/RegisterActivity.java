package com.mobdeve.s12.silverio.gwyneth.snapbox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private TextView tvLogin;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegister;
    private ProgressBar pbRegister;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initFirebase();
        this.initComponents();
    }

    private void initFirebase() {
        this.mAuth = FirebaseAuth.getInstance();
    }

    private void initComponents() {
        this.tvLogin = findViewById(R.id.tv_register_login);
        this.etEmail = findViewById(R.id.et_register_email);
        this.etPassword = findViewById(R.id.et_register_password);
        this.btnRegister = findViewById(R.id.btn_register_register);
        this.pbRegister = findViewById(R.id.pb_register);

        this.tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        this.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (!checkEmpty(email, password)) {
                    //do something
                    //add new user to db
                    User user = new User(email, password);
                    storeUser(user);
                }
            }
        });
    }

    private boolean checkEmpty(String email, String password) {
        boolean hasEmpty = false;

        if(email.isEmpty()) {
            this.etEmail.setError("Required field.");
            hasEmpty = true;
        }

        if(password.isEmpty()) {
            this.etPassword.setError("Required field.");
            hasEmpty = true;
        }

        return hasEmpty;
    }

    private void storeUser(User user) {
        this.pbRegister.setVisibility(View.VISIBLE);

        this.mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            successfulRegistration();
                        }
                        else {
                            failedRegistration();
                        }
                    }
                });

    }

    private void successfulRegistration() {
        this.pbRegister.setVisibility(View.GONE);
        Toast.makeText(this, "User successfully registered.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void  failedRegistration() {
        this.pbRegister.setVisibility(View.GONE);
        Toast.makeText(this, "User registration failed.", Toast.LENGTH_SHORT).show();
    }
}