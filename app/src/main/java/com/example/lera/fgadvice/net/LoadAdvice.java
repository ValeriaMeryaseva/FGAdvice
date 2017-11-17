package com.example.lera.fgadvice.net;

import com.example.lera.fgadvice.model.Advice;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LoadAdvice {
    @GET("api/random/censored")
    Call<Advice> getAdvice();
}
