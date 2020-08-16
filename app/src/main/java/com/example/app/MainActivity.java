package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.app.Retrofit.ClassModel;
import com.example.app.Retrofit.NodeJS;
import com.example.app.Retrofit.RetrofitClient;
import com.example.app.Retrofit.StudentModel;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

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

        //***********************************************
          TabLayout tabLayout = findViewById(R.id.tabBar);
//        TabItem tabMon = findViewById(R.id.Mon);
//        TabItem tabTue = findViewById(R.id.Tue);
//        TabItem tabWed = findViewById(R.id.Wed);
//        TabItem tabThur = findViewById(R.id.Thur);
//        TabItem tabFri = findViewById(R.id.Fri);

        final ViewPager viewPager = findViewById(R.id.viewPager);

        PagerAdapter pagerAdapter = new
                PagerAdapter(this,getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
               // viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //viewPager.setCurrentItem(tab.getPosition());
            }
        });

        //***************************************************

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(NodeJS.class);


        //mListView = (ListView) findViewById(R.id.listView);

        //getClasses();

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