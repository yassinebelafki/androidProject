package com.androidproject.activity.laureate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.androidproject.R;
import com.google.android.material.textfield.TextInputEditText;

public class EditLaureateActivity extends AppCompatActivity {
    private TextInputEditText laureateNameInput,laureateAgeInput,laureateEmailInput
            , laureatePhoneInput , laureateCityInput , laureateTrainingInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_laureate);
        laureateNameInput = findViewById(R.id.idEdtLaureateNameUpdate);
        laureateAgeInput = findViewById(R.id.idEdtLaureateAgeUpdate);
        laureateEmailInput = findViewById(R.id.idEdtLaureateEmailUpdate);
        laureatePhoneInput = findViewById(R.id.idEdtLaureatePhoneUpdate);
        laureateCityInput = findViewById(R.id.idEdtLaureateCityUpdate);
        laureateTrainingInput = findViewById(R.id.idEdtLaureateTrainingUpdate);
    }

    public void updateLaureate(View view) {
    }
}