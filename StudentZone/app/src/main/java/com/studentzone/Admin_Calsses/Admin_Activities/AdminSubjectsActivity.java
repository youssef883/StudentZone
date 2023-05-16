package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.R;

public class AdminSubjectsActivity extends AppCompatActivity {
    Button activity_admin_subjects_btn_add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_subjects);
        showBottomSheetDialog();
    }

    public void showBottomSheetDialog() {
        activity_admin_subjects_btn_add = findViewById(R.id.activity_admin_subjects_btn_add);
        activity_admin_subjects_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(AdminSubjectsActivity.this, R.style.BottomSheetStyle);
                View bottomSheetDialogView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.fragment_admin_new_subject, (LinearLayout)findViewById(R.id.fragment_admin_new_subject_ll_main));
                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }
}