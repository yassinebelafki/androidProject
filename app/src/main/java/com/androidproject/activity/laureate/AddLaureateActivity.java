package com.androidproject.activity.laureate;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.interest.InterestData;
import com.androidproject.activity.laureate.interest.ListInterest;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.Laureate;
import com.google.android.material.textfield.TextInputEditText;

public class AddLaureateActivity extends AppCompatActivity {
    private TextInputEditText laureateNameInput,laureateAgeInput,laureateEmailInput
            , laureatePhoneInput , laureateCityInput , laureateTrainingInput;
    String laureateName,laureateAge,laureateEmail
            , laureatePhone , laureateCity , laureateTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laureare);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Add new Laureate");
        laureateNameInput = findViewById(R.id.idEdtLaureateName);
        laureateAgeInput = findViewById(R.id.idEdtLaureateAge);
        laureateEmailInput = findViewById(R.id.idEdtLaureateEmail);
        laureatePhoneInput = findViewById(R.id.idEdtLaureatePhone);
        laureateCityInput = findViewById(R.id.idEdtLaureateCity);
        laureateTrainingInput = findViewById(R.id.idEdtLaureateTraining);
        ExperienceData.laureateExperienceList.clear();
        SkillsData.laureateSkills.clear();
        InterestData.laureateInterests.clear();
    }

    public void goToExperienceList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListExperienceActivity.class);
        startActivity(intent);
    }

    public void goToSkillsList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListSkills.class);
        startActivity(intent);
    }

    public void goToInterestsList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListInterest.class);
        startActivity(intent);
    }

    public void addNewLaureate(View view) {
        laureateName = laureateNameInput.getText().toString();
        laureateEmail = laureateEmailInput.getText().toString();
        laureateAge = laureateAgeInput.getText().toString();
        laureatePhone = laureatePhoneInput.getText().toString();
        laureateTraining = laureateTrainingInput.getText().toString();
        laureateCity = laureateCityInput.getText().toString();
        Laureate laureate = new Laureate(laureateName , Integer.parseInt(laureateAge) , laureateEmail , laureatePhone , laureateTraining
                , laureateCity , ExperienceData.laureateExperienceList , InterestData.laureateInterests , SkillsData.laureateSkills);


        MyDatabaseHelper myDB = new MyDatabaseHelper(AddLaureateActivity.this);
        myDB.addLaureateWithDetails(laureate);
        SkillsData.laureateSkills.clear();
        InterestData.laureateInterests.clear();
        ExperienceData.laureateExperienceList.clear();
        Toast.makeText(getApplicationContext(), "New Laureate is added successfully!", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(AddLaureateActivity.this, SchoolDashboardActivity.class);
        startActivity(intent);

    }
}