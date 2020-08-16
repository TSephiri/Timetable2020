package com.example.app.Retrofit;

import android.database.Observable;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface NodeJS {
    @POST("/")
    @FormUrlEncoded
    Observable<String> addNewStudent(@Field("StudentNo")String StudentNo,
                                     @Field("Password")String Password,
                                     @Field("Email")String Email,
                                     @Field("Degree_id")String Degree_id);

    @GET("api/students/{studentNo}")
    Call<List<StudentModel>> getStudentClasses(@Path("studentNo")String studentNo);

}
