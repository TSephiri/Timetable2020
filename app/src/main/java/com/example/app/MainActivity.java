package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.app.Retrofit.ClassModel;
import com.example.app.Retrofit.NodeJS;
import com.example.app.Retrofit.RetrofitClient;
import com.example.app.Retrofit.StudentModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "mainActivity";

    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<ClassModel> cList;
    List<StudentModel> student;
    ArrayList<ClassModel> classList = new ArrayList<>();

    ListView mListView;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate : Started");


        //Init API
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(NodeJS.class);


        mListView = (ListView) findViewById(R.id.listView);

        getClasses();
//        //********************************
//        ListView mListView = (ListView) findViewById(R.id.listView);
//
//        //Create the ClassModel objects
//        ClassModel c1 = new ClassModel("itrw322","monday","7:45","c464");
//        ClassModel c2 = new ClassModel("itrw322","monday","7:45","c464");
//        ClassModel c3 = new ClassModel("itrw322","monday","7:45","c464");
//        ClassModel c4 = new ClassModel("itrw322","monday","7:45","c464");
//        ClassModel c5 = new ClassModel("itrw322","monday","7:45","c464");
//
//        //Add the StudentModel objects to an ArrayList
//        ArrayList<ClassModel> cList = new ArrayList<>();
//        cList.add(c1);
//        cList.add(c2);
//        cList.add(c3);
//        cList.add(c4);
//        cList.add(c5);
//
//        ClassListAdapter adapter = new ClassListAdapter(this, R.layout.adapter_view_layout,cList);
//        mListView.setAdapter(adapter);
//
//        //*************************************

    }

    public void getClasses(){
        Call<List<StudentModel>> call = myAPI.getStudentClasses("20202020");

        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {

                student = response.body();

                addTextViews();
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                Log.i("call failure ",t.getMessage());
            }
        });
    }

    public void addTextViews()
    {
        for(StudentModel s: student) {
            for (ClassModel model : s.getClasses()) {
                //Log.i("log stuff", model.getModule());
                classList.add(new ClassModel(model.getModule(), model.getDay(), model.getTime(), model.getVenue()));
            }

        }
        ClassListAdapter classAdapter = new ClassListAdapter(MainActivity.this, classList);
        mListView.setAdapter(classAdapter);
    }

}