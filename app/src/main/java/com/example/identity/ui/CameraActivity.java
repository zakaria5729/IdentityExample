package com.example.identity.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.identity.R;
import com.example.identity.databinding.ActivityCameraBinding;
import com.example.identity.model.NIDInfo;
import com.example.identity.model.NIDResponse;
import com.example.identity.networks.ApiClient;
import com.example.identity.networks.ApiService;
import com.example.identity.util.PreferencesManager;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = "CameraActivity";
    private ActivityCameraBinding binding;
    private String base64ImageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCameraBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.takeImageButton.setOnClickListener(v ->
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setActivityTitle("Select the area which you want to crop")
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setAspectRatio(1, 1)
                        .setCropMenuCropButtonTitle("Done")
                        .start(this));

        binding.callApiButton.setOnClickListener(v -> {
            callApi();
        });
    }

    private void callApi() {
        PreferencesManager pref = PreferencesManager.getInstance(this);
        String nidNumber = pref.getNid();
        String dob = pref.getDob();
        String teamXId = "dhakabank.com.bd";

        if (nidNumber != null && dob != null) {
            NIDInfo nidInfo = new NIDInfo(base64ImageString, nidNumber, teamXId, true, dob);
            ApiService apiService = ApiClient.getApiClientInstance().getApiService();
            binding.progressBar.setVisibility(View.VISIBLE);

            apiService.checkUserWithPhoto(nidInfo).enqueue(new Callback<NIDResponse>() {
                @Override
                public void onResponse(@NonNull Call<NIDResponse> call,
                                       @NonNull Response<NIDResponse> response) {
                    binding.progressBar.setVisibility(View.GONE);

                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            NIDResponse nidResponse = response.body();

                            Intent intent = new Intent(CameraActivity.this, DisplayActivity.class);
                            intent.putExtra("key_data", nidResponse);
                            startActivity(intent);
                        } else {
                            Toast.makeText(CameraActivity.this, "Error: response is null", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(CameraActivity.this, "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NIDResponse> call, @NonNull Throwable t) {
                    Toast.makeText(CameraActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } else {
            Toast.makeText(CameraActivity.this, "Error: NID number and DOB filed null", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {
                Uri imageUri = result.getUri();

                if (imageUri != null) {
                    binding.callApiButton.setVisibility(View.VISIBLE);
                    base64ImageString = getBase64ImageString(imageUri);

                    Glide.with(this)
                            .load(imageUri)
                            .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                            .into(binding.preViewImageView);
                } else if (result.getError() != null) {
                    Toast.makeText(this, "Error: " + result.getError().getMessage(), Toast.LENGTH_LONG).show();
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Error: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private String getBase64ImageString(Uri imageUri) {
        String base64String = null;

        try {
            InputStream imageStream = getContentResolver().openInputStream(imageUri);
            final Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();
            base64String = Base64.encodeToString(b, Base64.DEFAULT);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return base64String;
    }
}
