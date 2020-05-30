package com.example.identity.networks;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "https://porichoy.azurewebsites.net";
    private static final String API_KEY = "d8e8459b-89c4-4226-8573-cff9baab878d";
    private static ApiClient instance;
    private Retrofit retrofit;

    private ApiClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {
                        Request originalRequest = chain.request();

                        Request.Builder requestBuilder = originalRequest.newBuilder()
                                .addHeader("x-api-key", API_KEY);

                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                }).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
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
