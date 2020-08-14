package com.example.app.Retrofit;
import java.util.List;
public class StudentModel {
    List<ClassModel> classes;
    String StudentNo,Password,Email,Degree_id;


    public String getStudentNo() {
        return StudentNo;
    }
//    //***********************
//    public StudentModel(String n, String r,String p){
//        setStudentNo(n);
//        setDegree_id(r);
//        setEmail(p);
//    }
//    //************************
    public void setStudentNo(String studentNo) {
        StudentNo = studentNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDegree_id() {
        return Degree_id;
    }

    public void setDegree_id(String degree_id) {
        Degree_id = degree_id;
    }

    public List<ClassModel> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassModel> classes) {
        this.classes = classes;
    }
}
