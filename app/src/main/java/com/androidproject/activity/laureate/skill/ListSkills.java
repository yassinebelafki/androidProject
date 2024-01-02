package com.androidproject.activity.laureate.skill;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.AddExperienceActivity;
import com.androidproject.activity.laureate.experience.CustomAdapter;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.androidproject.models.Laureate.LaureateSkill;

import java.util.ArrayList;

public class ListSkills extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String> skill_names, skill_types;
    CustomSkillAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_skills);
        empty_imageview = findViewById(R.id.empty_imageSkillview);
        no_data = findViewById(R.id.no_dataSkill);
        recyclerView = findViewById(R.id.recyclerSkillView);
        skill_names = new ArrayList<>();
        skill_types = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomSkillAdapter(ListSkills.this,this,
                skill_names, skill_types);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListSkills.this));
    }

    void storeDataInArrays(){
        if (SkillsData.laureateSkills.isEmpty()){

            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {
            for (LaureateSkill skill : SkillsData.laureateSkills) {
                // Add values to respective lists
                skill_names.add(skill.getName());
                skill_types.add(skill.getType());
            }
        }


    }

    public void goToAddSkill(View view) {
        Intent intent = new Intent(ListSkills.this, AddSkills.class);
        startActivity(intent);
    }
}