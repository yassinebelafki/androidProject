package com.androidproject.activity.laureate.experience;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidproject.R;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.google.android.material.textfield.TextInputEditText;

public class AddExperienceActivity extends AppCompatActivity {

    private TextInputEditText experienceTitle, experienceDescription, experienceStartDate
            , experienceEndDate, courseImgEdt, courseLinkEdt;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_experience);
    }

    public void addNewExperience(View view) {
        experienceTitle = findViewById(R.id.idEdtexperienceTitle);
        experienceDescription = findViewById(R.id.idEdtexperienceDescription);
        experienceStartDate = findViewById(R.id.idEdtexpStartDate);
        experienceEndDate = findViewById(R.id.idEdtexpEndDate);
        LaureateExperience laureateExperience = new LaureateExperience(experienceTitle.getText().toString(),
                experienceDescription.getText().toString(),
                                                        experienceStartDate.getText().toString(),experienceEndDate.getText().toString());
        MyDatabaseHelper myDB = new MyDatabaseHelper(AddExperienceActivity.this);
        myDB.addExperience(laureateExperience);
        //using local list :
        ExperienceData.laureateExperienceList.add(laureateExperience);
        Intent intent = new Intent(AddExperienceActivity.this, ListExperienceActivity.class);
        startActivity(intent);
    }
}