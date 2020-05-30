package com.example.identity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.example.identity.R;
import com.example.identity.databinding.ActivityDisplayBinding;
import com.example.identity.model.FaceMatchResult;
import com.example.identity.model.NIDResponse;
import com.example.identity.model.Voter;
import com.example.identity.util.PreferencesManager;

public class DisplayActivity extends AppCompatActivity {

    private ActivityDisplayBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDisplayBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (getIntent() != null) {
            NIDResponse nidResponse = (NIDResponse) getIntent().getSerializableExtra("key_data");

            if (nidResponse != null) {
                setUpUI(nidResponse);
            }
        }
    }

    private void setUpUI(NIDResponse nidResponse) {
        Voter voter = nidResponse.getVoter();
        if (voter != null) {
            binding.nameBnTV.setText(voter.getName());
            binding.nameEnTV.setText(voter.getNameEn());
            binding.genderTV.setText(voter.getGender());
            binding.dobTV.setText(voter.getDob());
            binding.fatherNameTV.setText(voter.getFather());
            binding.motherNameTV.setText(voter.getMother());

            FaceMatchResult result = voter.getFaceMatchResult();
            if (result != null) {
                binding.faceMatchedTV.setText(String.valueOf(result.getMatched()));
                binding.matchPercentageTV.setText(result.getPercentage() + "%");
            }

            if (voter.getPhoto() != null) {
                showImage(nidResponse.getVoter().getPhoto());
            }
        }

        PreferencesManager.getInstance(this).clearNidAndDob();
    }

    private void showImage(String base64ImageString) {
        byte[] imageBytes = Base64.decode(base64ImageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);

        Glide.with(this)
                .load(decodedImage)
                .apply(new RequestOptions().format(DecodeFormat.PREFER_ARGB_8888))
                .into(binding.userImageView);
    }


}
