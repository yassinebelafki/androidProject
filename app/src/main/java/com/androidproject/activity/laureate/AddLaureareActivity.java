package com.androidproject.activity.laureate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.AddExperienceActivity;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.interest.ListInterest;
import com.androidproject.activity.laureate.skill.ListSkills;

public class AddLaureareActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laureare);

    }

    public void goToExperienceList(View view) {
        Intent intent = new Intent(AddLaureareActivity.this, ListExperienceActivity.class);
        startActivity(intent);
    }

    public void goToSkillsList(View view) {
        Intent intent = new Intent(AddLaureareActivity.this, ListSkills.class);
        startActivity(intent);
    }

    public void goToInterestsList(View view) {
        Intent intent = new Intent(AddLaureareActivity.this, ListInterest.class);
        startActivity(intent);
    }
}