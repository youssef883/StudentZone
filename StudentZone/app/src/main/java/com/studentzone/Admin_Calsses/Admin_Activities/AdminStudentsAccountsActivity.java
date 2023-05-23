package com.studentzone.Admin_Calsses.Admin_Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.appsearch.SearchResult;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.studentzone.Data_Base.My_DB;
import com.studentzone.Data_Base.Students;
//import com.studentzone.Admin_Calsses.Admin_Models.Admin_Student_Model.studentRecyclerViewAdapter;
import com.studentzone.R;

import java.util.ArrayList;

public class AdminStudentsAccountsActivity extends AppCompatActivity {
    Button btn_add, btn_back, btm_sheet_dia_btn_save, btm_sheet_dia_btn_close;
    EditText btm_sheet_dia_et_student_name, btm_sheet_dia_et_student_password, btm_sheet_dia_et_student_email, btm_sheet_dia_et_student_aid;
    TextView tv_search_for_students;
    RadioGroup btm_sheet_dia_rg;
    RadioButton btm_sheet_dia_rb_male, btm_sheet_dia_rb_female;

    String aid, name, email, password, gender;
    RecyclerView rv;

    int genderId;

    BottomSheetDialog bottomSheetDialog;
    View bottomSheetDialogView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_students_accounts);

        inflate();
//        showAllStudents();
        buttonAddAction();
        saveStudentData();
        closeBottomSheet();
        buttonBackAction();
    }


    /**inflate
     **********************************************************************************************/
    public void inflate() {
        btn_add = findViewById(R.id.activity_admin_students_accounts_btn_add);
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
//        tv_search_for_students = findViewById(R.id.activity_admin_students_accounts_et_search_for_students);

        bottomSheetDialog = new BottomSheetDialog(AdminStudentsAccountsActivity.this, R.style.BottomSheetStyle);
        bottomSheetDialogView = getLayoutInflater().inflate(R.layout.fragment_admin_new_student_account, null, false);

        btm_sheet_dia_btn_save = bottomSheetDialogView.findViewById(R.id.fragment_new_student_btn_save);
        btm_sheet_dia_btn_close = bottomSheetDialogView.findViewById(R.id.fragment_admin_new_student_btn_close);

        btm_sheet_dia_et_student_name = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_studet_name);
        btm_sheet_dia_et_student_aid = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_aid);
        btm_sheet_dia_et_student_password = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_password);
        btm_sheet_dia_et_student_email = bottomSheetDialogView.findViewById(R.id.fragment_new_student_et_student_email);

        RadioGroup btm_sheet_dia_rg = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rg_student_kind);
        btm_sheet_dia_rb_male = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rb_male);
        btm_sheet_dia_rb_female = bottomSheetDialogView.findViewById(R.id.fragment_new_student_rb_female);

//        rv = findViewById(R.id.activity_admin_students_accounts_rv);
    }


    /**show Bottom Sheet Dialog()
     **********************************************************************************************/
    public void buttonAddAction() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.setContentView(bottomSheetDialogView);
                bottomSheetDialog.show();
            }
        });
    }


    /** close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void closeBottomSheet() {
        btm_sheet_dia_btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
                nullEditTexts();
//                showAllStudents();
            }
        });
    }

    /**check Validation Of Entered Data And save It()
     **********************************************************************************************/
    public void saveStudentData() {
        btm_sheet_dia_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = btm_sheet_dia_et_student_name.getText().toString();
                email = btm_sheet_dia_et_student_email.getText().toString();
                password = btm_sheet_dia_et_student_password.getText().toString();
                aid = btm_sheet_dia_et_student_aid.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    btm_sheet_dia_et_student_name.setError("Is Required !");
                    return;
                }
                if (TextUtils.isEmpty(aid)) {
                    btm_sheet_dia_et_student_aid.setError("Is Required !");
                    return;
                }

                if (TextUtils.isEmpty(email)) {
                    btm_sheet_dia_et_student_email.setError("Is Required !");
                    return;
                }
                if (!emailShouldEndsWithEdu(email)) {
                    btm_sheet_dia_et_student_email.setError("Should End With .edu");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    btm_sheet_dia_et_student_password.setError("Is Required !");
                    return;
                }

//                addNewStudent();
                nullEditTexts();


            }
        });
    }


    /**Back To The Previous Activity()
     **********************************************************************************************/
    public void buttonBackAction() {
        btn_back = findViewById(R.id.activity_admin_students_accounts_btn_back);
        btn_back.setOnClickListener(v -> startActivity(new Intent(getBaseContext(), AdminHomeActivity.class)));
    }


    /**
     * radioButtonGroupAction
     * kindCheckedId, Get Index Of Checked Student kind
     ******************************************************************************************/
//    public void radioButtonGroupAction(){
//        btm_sheet_dia_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                genderId = group.indexOfChild(findViewById(checkedId));
//                Toast.makeText(getBaseContext(),""+ genderId, Toast.LENGTH_SHORT).show();
//            }
//        });
//    }


    /**add New Student
     ******************************************************************************************/
    public void addNewStudent() {

        My_DB db = new My_DB(getBaseContext());

        aid = btm_sheet_dia_et_student_aid.getText().toString().trim();
        name = btm_sheet_dia_et_student_name.getText().toString().trim();
        email = btm_sheet_dia_et_student_email.getText().toString().trim();
        password = btm_sheet_dia_et_student_password.getText().toString().trim();
        gender = "Male";


//        Students student = new Students(aid, name, name, gender, email, password);
//        Boolean added = db.addStudent(student);

//        if(added){
//            Toast.makeText(this, name + "Is Successfully Saved ✔️", Toast.LENGTH_SHORT).show();
//            bottomSheetDialog.dismiss();
//        }

//        showAllStudents();
    }


    /**
     * Special Validation For Email To End With .edu
     **********************************************************************************************/
    public static boolean emailShouldEndsWithEdu(String input) {
        return TextUtils.isEmpty(input) ? false : input.endsWith(".edu");
    }

    /**FillOut  EditTexts After Add New Student Or After Close Bottom Sheet Dialog()
     **********************************************************************************************/
    public void nullEditTexts() {

        btm_sheet_dia_et_student_name.setText("");
        btm_sheet_dia_et_student_email.setText("");
        btm_sheet_dia_et_student_password.setText("");
        btm_sheet_dia_et_student_aid.setText("");

    }

    /**Show All Students
     **********************************************************************************************/
    public void showAllStudents() {

        My_DB db = new My_DB(getBaseContext());


//        ArrayList<Students> studentsArrayList = db.showStudents();

//        studentRecyclerViewAdapter adapter = new studentRecyclerViewAdapter(studentsArrayList);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
//        rv.setAdapter(adapter);
    }


//    /**Search For Students
//     **********************************************************************************************/
//    public void searchForStudents() {
//
//
//        My_DB db = new My_DB(getBaseContext());
//
//
//        ArrayList<Students> studentsArrayList = db.searchForStudents(tv_search_for_students.getText().toString());
//
//        studentRecyclerViewAdapter adapter = new studentRecyclerViewAdapter(studentsArrayList);
//        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
//
//        rv.setHasFixedSize(true);
//        rv.setLayoutManager(lm);
//        rv.setAdapter(adapter);
//    }




}