package com.studentzone.Student_Classes.Student_Models.SubjectModel;

public class SubjectModel {

String subject_name , subject_description ;
int subject_icon ;

    public SubjectModel(String subject_name, String subject_description, int subject_icon) {
        this.subject_name = subject_name;
        this.subject_description = subject_description;
        this.subject_icon = subject_icon;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_description() {
        return subject_description;
    }

    public void setSubject_description(String subject_description) {
        this.subject_description = subject_description;
    }

    public int getSubject_icon() {
        return subject_icon;
    }

    public void setSubject_icon(int subject_icon) {
        this.subject_icon = subject_icon;
    }
}
