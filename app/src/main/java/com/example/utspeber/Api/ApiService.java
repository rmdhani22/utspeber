package com.example.utspeber.Api;

import android.content.ClipData;

import com.example.utspeber.data.Items;
import com.example.utspeber.data.Respon;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    String TOKEN = "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";
    @GET("search/users")
    @Headers("Authorization: Bearer " + TOKEN)
    Call<Respon> callUser(@Query("q") String username);
    @GET("users/{username}")
    Call<Items> callDetailUser(@Path("username") String username);

}
