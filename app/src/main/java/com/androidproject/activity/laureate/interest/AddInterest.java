package com.androidproject.activity.laureate.interest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidproject.R;
import com.androidproject.activity.laureate.skill.AddSkills;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.models.Laureate.LaureateInterests;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.textfield.TextInputEditText;

public class AddInterest extends AppCompatActivity {

    private TextInputEditText interestNameInput ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_interest);
    }

    public void addNewInterest(View view) {
        interestNameInput = findViewById(R.id.idEdtinterestName);
        InterestData.laureateInterests.add(new LaureateInterests(interestNameInput.getText().toString()));
        Intent intent = new Intent(AddInterest.this, ListInterest.class);
        startActivity(intent);
    }
}