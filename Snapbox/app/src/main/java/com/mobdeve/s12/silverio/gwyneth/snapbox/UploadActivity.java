package com.mobdeve.s12.silverio.gwyneth.snapbox;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class UploadActivity extends AppCompatActivity {

    private Button btnGallery;
    private Button btnCamera;
    private Button btnBack;
    private Uri uriImage;
    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        initComponents();
    }

    private void initComponents() {
        this.btnGallery = findViewById(R.id.btn_upload_gallery);
        this.btnCamera = findViewById(R.id.btn_upload_camera);
        this.btnBack = findViewById(R.id.btn_upload_back);
        this.ivImage = findViewById(R.id.iv_upload_image);

        this.btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent(UploadActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });

        this.btnCamera.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }
        });

        this.btnGallery.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
    }

    private void chooseImage() {
        Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(galleryIntent, 1);
    }


}