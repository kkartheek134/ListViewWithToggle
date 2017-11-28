package com.example.kartheek.listviewwithtoggle;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView view;
    RetrofitInterface retrofit;
    Call<List<MyModel>> call;

    List<MyModel> list = new ArrayList();
    List myList= new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
    }

    private void initializeViews() {
        view = (ListView) findViewById(R.id.list);

        Retrofit retrofitt = new Retrofit.Builder().baseUrl(RetrofitInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        retrofit = retrofitt.create(RetrofitInterface.class);
        call =  retrofit.getJsonData();
        call.enqueue(new Callback<List<MyModel>>() {
            @Override
            public void onResponse(Call<List<MyModel>> call, Response<List<MyModel>> response) {
                list = response.body();

                for(int i = 0;i<list.size();i++) {
                    String name = list.get(i).getName();
                    MyModel model = new MyModel(name);
                    myList.add(model);
                }
                CustomAdapter adapter  = new CustomAdapter(MainActivity.this,myList);
                view.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<MyModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
