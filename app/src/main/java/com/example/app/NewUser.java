package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Observable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.app.Retrofit.NodeJS;
import com.example.app.Retrofit.RetrofitClient;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NewUser extends AppCompatActivity {
    String studentNo,Degree_id;
    EditText student,degree;
    NodeJS myAPI;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);

        Button addDegree = findViewById(R.id.AddDegree);
        student = findViewById(R.id.studentNo);
        degree = findViewById(R.id.degreeID);

        retrofit = RetrofitClient.getInstance();
        myAPI = retrofit.create(NodeJS.class);

        addDegree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentNo = student.getText().toString();
                Degree_id = degree.getText().toString();
                addStudent(studentNo,Degree_id);
            }
        });
    }

    public void addStudent(String studentNo,String Degree_id){
           Call<String> call = myAPI.addNewStudent(studentNo," "," ",Degree_id);
           call.enqueue(new Callback<String>() {
               @Override
               public void onResponse(Call<String> call, Response<String> response) {
                   if(response.isSuccessful()){
                       Log.i("","post Submitted to API ");
                       startActivity(new Intent(NewUser.this,MainActivity.class));
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
}