package com.example.app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Thursday#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Thursday extends Fragment {

    //////////////////////////////////
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<ClassModel> cList;
    List<StudentModel> student;
    ArrayList<ClassModel> classList;

    ListView mListView;
//////////////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Thursday() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Thursday.
     */
    // TODO: Rename and change types and number of parameters
    public static Thursday newInstance(String param1, String param2) {
        Thursday fragment = new Thursday();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        classList = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_monday, container, false);

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(NodeJS.class);

        mListView = (ListView) view.findViewById(R.id.listView);
        initTimeTable();
        getClasses();
        ///////////////////////////////////////////////////////
        return view;
    }

    public void initTimeTable(){

        //classList
        for(int i = 8;i<18;i++ ){
            classList.add((new ClassModel("","",i+":00","")));
        }
    }

    public void getClasses(){
        Call<List<StudentModel>> call = myAPI.getStudentClasses("20002000");

        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                ///////////////////////////////////

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
//        for(StudentModel s: student) {
//            for (ClassModel model : s.getClasses()) {
//                //Log.i("log stuff", model.getModule());
//                //if(model.getDay()=="Mon") {
//                    classList.add(new ClassModel(model.getModule(), model.getDay(), model.getTime(), model.getVenue()));
//                    switch(model.getTime()){
//
//                    }
//                }
//            }
//        }

//        while(classList.size()!=10){
//            classList.add(new ClassModel( "TBD"," MONDAY"," "," E8 101"));
//        }

        ClassListAdapter classAdapter = new ClassListAdapter(getActivity(), classList);
        mListView.setAdapter(classAdapter);
    }
}