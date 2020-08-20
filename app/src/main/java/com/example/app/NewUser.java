package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewUser extends AppCompatActivity {
    String studentNo,Degree_id;
    EditText student,degree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button addDegree = findViewById(R.id.AddDegree);
        student = findViewById(R.id.studentNo);
        degree = findViewById(R.id.degreeID);
        addDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNo = student.getText().toString();
                Degree_id = degree.getText().toString();
            }
        });
    }
}