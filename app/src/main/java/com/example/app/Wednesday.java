package com.example.app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.app.Retrofit.ClassModel;
import com.example.app.Retrofit.NodeJS;
import com.example.app.Retrofit.RetrofitClient;
import com.example.app.Retrofit.StudentModel;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Wednesday#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Wednesday extends Fragment {

    //////////////////////////////////
    NodeJS myAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    List<ClassModel> cList;
    List<StudentModel> student;
    ArrayList<ClassModel> classList;

    ListView gListView;
//////////////////////////////////////

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Wednesday() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Wednesday.
     */
    // TODO: Rename and change types and number of parameters
    public static Wednesday newInstance(String param1, String param2) {
        Wednesday fragment = new Wednesday();
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

        View view = inflater.inflate(R.layout.fragment_monday, container, false);


        return initAPI(view,classList,myAPI,gListView,R.id.listView,"Wednesday");
        // return inflater.inflate(R.layout.fragment_monday, container, false);
    }

    //From here till the end of the class can be moved into a separate interface
    public View initAPI(View view, ArrayList<ClassModel> classList, NodeJS myAPI, ListView gListView, int Id, String day){
        classList = new ArrayList<>();

        //Init API
        Retrofit retrofit = RetrofitClient.getInstance_get();
        myAPI = retrofit.create(NodeJS.class);

        gListView = (ListView) view.findViewById(Id);
        //method for when you click on each item to alter the info
        gListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent addInfo =  new Intent(getActivity(),UpdateInfo.class);
                addInfo.putExtra("Day","Wednesday");
                addInfo.putExtra("TimeSlot",position);

                startActivity(addInfo);
            }
        });
        initTimeTable(classList);
        getClasses(myAPI,classList,gListView,day);
        ///////////////////////////////////////////////////////
        return view;
    }

    public void initTimeTable(ArrayList<ClassModel> classList){

        //classList
        for(int i = 8;i<18;i++ ){
            classList.add((new ClassModel("","",i+":00","")));
        }
    }

    public void getClasses(NodeJS myAPI, final ArrayList<ClassModel> classList,final ListView mListView,final String day){
        Call<List<StudentModel>> call = myAPI.getStudentClasses("12345678");

        call.enqueue(new Callback<List<StudentModel>>() {
            @Override
            public void onResponse(Call<List<StudentModel>> call, Response<List<StudentModel>> response) {
                ///////////////////////////////////

                List<StudentModel>student = response.body();

                addTextViews(student,classList,mListView,day);
            }

            @Override
            public void onFailure(Call<List<StudentModel>> call, Throwable t) {
                Log.i("call failure ",t.getMessage());
            }
        });
    }

    public void addTextViews(List<StudentModel> student,ArrayList<ClassModel> classList,ListView mListView,String day)
    {
        int index=-1;
        for(StudentModel s: student) {
            for (ClassModel model : s.getClasses()) {
//                ClassModel temp = new ClassModel("","", model.getTime(),"");
//                index = classList.indexOf(temp);
                //Log.i("log stuff", model.getModule());
                if(model.getDay().equals(day))
                    classList.set(getIndex(model.getTime()),new ClassModel(model.getModule(), model.getDay(), model.getTime(), model.getVenue()));
            }
        }

        updateView(classList,mListView);

    }

    public int getIndex(String time){
        int index = -1;
        switch (time)
        {
            case "8:00":
                index = 0;
                break;
            case "9:00":
                index = 1;
                break;
            case "10:00":
                index = 2;
                break;
            case "11:00":
                index = 3;
                break;
            case "12:00":
                index = 4;
                break;
            case "13:00":
                index = 5;
                break;
            case "14:00":
                index = 6;
                break;
            case "15:00":
                index = 7;
                break;
            case "16:00":
                index = 8;
                break;
            case "17:00":
                index = 9;
                break;
        }
        return index;
    }

    public void updateView(ArrayList<ClassModel> classList,ListView mListView)
    {
        ClassListAdapter classAdapter = new ClassListAdapter(getActivity(), classList);
        mListView.setAdapter(classAdapter);
    }
}