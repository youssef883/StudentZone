package com.studentzone.Doctor_Classes.Doctor_Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.studentzone.Admin_Classes.Admin_Activities.AdminProfileActivity;
import com.studentzone.R;

public class DoctorProfileActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        inflate();
        setEditPhotoTextViewAction();
    }

    /**
     * Inflate
     **********************************************************************************************/
    public void inflate() {
        imageView = findViewById(R.id.activity_doctor_profile_shiv_doctor_photo);
        textView = findViewById(R.id.activity_doctor_profile_tv_edit_photo);
    }

    /** setEditPhotoTextViewAction()
     * set Edit Photo TextView Action
     **********************************************************************************************/
    public void setEditPhotoTextViewAction() {

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.with(DoctorProfileActivity.this)
                        .crop()	    			//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        imageView.setImageURI(uri);
    }
}