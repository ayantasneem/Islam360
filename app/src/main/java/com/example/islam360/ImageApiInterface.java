package com.example.islam360;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImageApiInterface {
    @GET("NFYsXW")
    Call<ImageApiModel> getData();
}
