package com.cristovancamilo.apoiobm.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.1.1.242:8095")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


}
