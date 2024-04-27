package com.example.utspeber;


import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.utspeber.data.Items;
import com.example.utspeber.data.Respon;
import com.example.utspeber.Api.Api;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private final List<Items> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView = findViewById(R.id.rvUser);

        String username = "rmdhani";
        Call<Respon> responseCall = Api.getApi().callUser(username);

        responseCall.enqueue(new Callback<Respon>() {
            @Override
            public void onResponse(@NonNull Call<Respon> call, @NonNull retrofit2.Response<Respon> response) {
                if (response.isSuccessful()){
                    list.addAll(response.body().getItems());

                    UserAdapter userAdapter = new UserAdapter(list);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerView.setAdapter(userAdapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Respon> call, @NonNull Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });


    }
}