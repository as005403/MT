package com.dobrolinskiy.mt_lab2.resource;

import com.dobrolinskiy.mt_lab2.model.Pic;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "https://api.publicapis.org/";

    @GET("entries")
    Call<Pic> getModel();
}


