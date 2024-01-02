package com.androidproject.activity.laureate.skill;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.AddExperienceActivity;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.textfield.TextInputEditText;

public class AddSkills extends AppCompatActivity {
    private  String skillType;
    private TextInputEditText skillNameInput;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skills);

        spinner = findViewById(R.id.skillType);
        String[] options = {"Option 1", "Option 2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                skillType = options[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
    }

    public void addNewSkill(View view) {
        skillNameInput = findViewById(R.id.idEdtskillName);
        SkillsData.laureateSkills.add(new LaureateSkill(skillType , skillNameInput.getText().toString()));
        finish();
        Intent intent = new Intent(AddSkills.this, ListSkills.class);
        startActivity(intent);
    }
}