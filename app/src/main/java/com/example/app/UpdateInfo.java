package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app.Retrofit.NodeJS;
import com.example.app.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdateInfo extends AppCompatActivity {

    String module,time,venue,day;
    EditText eModule,eTime,eVenue,eDay;
    NodeJS myAPI;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        eModule = findViewById(R.id.module);
        eDay = findViewById(R.id.day);
        eTime = findViewById(R.id.time);
        eVenue = findViewById(R.id.venue);
        Button saveInfo = findViewById(R.id.updateInfo);

        Intent intent = getIntent();
        eTime.setText(getTime(intent.getStringExtra("TimeSlot")));
        eDay.setText(intent.getStringExtra("Day"));

        retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        saveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                module = eModule.getText().toString();
                //time should be received from fragment based on selected item
                time = eTime.getText().toString();
                venue = eVenue.getText().toString();
                //day of the week should also be from the fragment
                day = eDay.getText().toString();

                update();

            }
        });

    }
    //Change student number to a parameter pass from each class
    public void update(){
        Call<String> call = myAPI.updateClasses("12345678",module,time,venue,day);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.i("","Updated Student ");
                    Toast.makeText(UpdateInfo.this,"Saved Info",Toast.LENGTH_SHORT);
                    Intent newIntent = new Intent(UpdateInfo.this,MainActivity.class);
                    startActivity(newIntent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(call.isCanceled()) {
                    Log.e("", "request was aborted");
                }else {
                    Log.e("", "Unable to submit post to API.");
                }
                //showErrorMessage();

            }
        });
    }
    //convert position to time
    public String getTime(String position){
        String time = " ";
        switch (position)
        {
            case "0":
             time = "8:00";
                break;
            case "1":
                time = "9:00";
                break;
            case "2":
                time = "10:00";
                break;
            case "3":
                time = "11:00";
                break;
            case "4":
                time = "12:00";
                break;
            case "5":
                time = "13:00";
                break;
            case "6":
                time = "14:00";
                break;
            case "7":
                time = "15:00";
                break;
            case "8":
                time = "16:00";
                break;
            case "9":
                time = "17:00";
                break;
        }
        return time;
    }
}