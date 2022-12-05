package com.quotes.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseUrl = "https://type.fit/api/";
    private static Retrofit retro;

    public static Retrofit connectRetrofit(){
        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retro;
    }

}
