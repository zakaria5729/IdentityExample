package com.example.identity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.identity.R;
import com.example.identity.databinding.ActivityCameraBinding;
import com.example.identity.networks.ApiClient;
import com.example.identity.networks.ApiService;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private File imageFile;
    private String nidNumber;
    private ActivityCameraBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            nidNumber = getIntent().getStringExtra("key_nid_number");
        }

        binding.takeImageButton.setOnClickListener(v -> {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setActivityTitle("Select the area which you want to crop")
                    .setCropShape(CropImageView.CropShape.RECTANGLE)
                    .setAspectRatio(1, 1)
                    .setCropMenuCropButtonTitle("Done")
                    .setRequestedSize(100, 100)
                    .start(this);
        });

        binding.callApiButton.setOnClickListener(v -> {
            if (nidNumber != null && imageFile != null) {
                callApi();
            }
        });
    }

    private void callApi() {
        RequestBody fileRequestBody = RequestBody.create(MediaType.parse("image/*"), this.imageFile);
        MultipartBody.Part imageMultipart = MultipartBody.Part.createFormData(
                "upload", imageFile.getName(), fileRequestBody);

        ApiService apiService = ApiClient.getApiClientInstance().getApiService();
        apiService.upload(nidNumber, imageMultipart).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.d(TAG, "onResponse: "+response.message());
                    }
                } else {
                    Log.d(TAG, "onResponse: " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri imageUri = result.getUri();

                if (imageUri != null && imageUri.getPath() != null) {
                    this.imageFile = new File(imageUri.getPath());
                    binding.callApiButton.setVisibility(View.VISIBLE);

                    Glide.with(this)
                            .load(imageUri)
                            .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                            .into(binding.preViewImageView);
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Error: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
