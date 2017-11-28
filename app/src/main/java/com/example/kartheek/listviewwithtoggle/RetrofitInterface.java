package com.example.kartheek.listviewwithtoggle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Kartheek on 11/27/2017.
 */

public interface RetrofitInterface {
    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

        @GET("users")

        Call<List<MyModel>> getJsonData();


}
