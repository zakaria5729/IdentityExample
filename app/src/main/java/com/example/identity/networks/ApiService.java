package com.example.identity.networks;

import com.example.identity.model.NIDInfo;
import com.example.identity.model.NIDResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/Kyc/nid-person-values-image-match")
    Call<NIDResponse> checkUserWithPhoto(@Body NIDInfo nidInfo);
}
