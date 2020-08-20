package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateInfo extends AppCompatActivity {

    String module,time,venue,day;
    EditText eModule,eTime,eVenue,eDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        eModule = findViewById(R.id.module);
        eDay = findViewById(R.id.day);
        eTime = findViewById(R.id.time);
        eVenue = findViewById(R.id.venue);
        Button saveInfo = findViewById(R.id.updateInfo);
        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module = eModule.getText().toString();
                //time should be received from fragment based on selected item
                time = eTime.getText().toString();
                venue = eVenue.getText().toString();
                //day of the week should also be from the fragment
                day = eDay.getText().toString();

                startActivity(new Intent(UpdateInfo.this,MainActivity.class));
                finish();
            }
        });

    }
}