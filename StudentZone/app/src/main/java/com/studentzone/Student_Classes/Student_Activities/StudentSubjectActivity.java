package com.studentzone.Student_Classes.Student_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Button;

import com.studentzone.Data_Base.My_DB;
import com.studentzone.R;
import com.studentzone.Student_Classes.Student_Models.RegestrationModel.SubjectRegestrationModel;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectAdapter;
import com.studentzone.Student_Classes.Student_Models.SubjectModel.SubjectModel;

import java.util.ArrayList;

public class StudentSubjectActivity extends AppCompatActivity {
    Button btn_back;
    My_DB my_db=new My_DB( this);
    ArrayList<SubjectModel>arrayList =new ArrayList<>();

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_subject);
        Intent intent = getIntent();
        ArrayList<SubjectModel> checkedSubjects =intent.getParcelableExtra("checkedSubjects") ;
// Now you can use the checkedSubjects ArrayList as needed
        buttonBackAction();
        recyclerView=findViewById(R.id.student_subject_recycleview );//add item in recycler
         arrayList=my_db.Get_all_courses_for_student_afterRegist();//Method to get all courses name and courses code
        SubjectAdapter subjectAdapter=new SubjectAdapter(this,arrayList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(subjectAdapter); //add model to recycler view



        }

    public void buttonBackAction(){
        btn_back = findViewById(R.id.activity_student_subject_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(StudentSubjectActivity.this, StudentHomeActivity.class)));
    }



}