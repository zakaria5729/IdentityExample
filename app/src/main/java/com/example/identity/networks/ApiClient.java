package com.example.identity.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "";
    private static ApiClient instance;
    private Retrofit retrofit;

    private ApiClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiClient getApiClientInstance() {
        if (instance == null) {
            synchronized (ApiClient.class) {  //double check lock with thread safety
                if (instance == null) {
                    instance = new ApiClient();
                }
            }
        }
        return instance;
    }

   public ApiService getApiService() {
        return retrofit.create(ApiService.class);
    }
}
