package com.example.identity.ui;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.identity.databinding.ActivityMainBinding;
import com.example.identity.util.PreferencesManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private String nidNumber, dob;
    private ActivityMainBinding binding;
    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.dobButton.setOnClickListener(v -> {
            new DatePickerDialog(this, datePicker, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        binding.nextButton.setOnClickListener(v -> {
            nidNumber = Objects.requireNonNull(binding.nidTIEditText.getText()).toString();

            if (nidNumber != null && !TextUtils.isEmpty(nidNumber.trim()) && dob != null) {
                PreferencesManager.getInstance(this).setNidAndDob(nidNumber, dob);

                startActivity(new Intent(this, CameraActivity.class));
            } else {
                if (nidNumber == null || TextUtils.isEmpty(nidNumber.trim())) {
                    Toast.makeText(this, "NID number field can not be empty", Toast.LENGTH_SHORT).show();
                } else if (dob == null) {
                    Toast.makeText(this, "Dob field invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    DatePickerDialog.OnDateSetListener datePicker = (view, year, month, dayOfMonth) -> {
         myCalendar.set(Calendar.YEAR, year);
        myCalendar.set(Calendar.MONTH, month);
        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        this.dob = sdf.format(myCalendar.getTime());
    };
}
