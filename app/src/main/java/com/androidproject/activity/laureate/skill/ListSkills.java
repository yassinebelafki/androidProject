package com.androidproject.activity.laureate.skill;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListSkills extends AppCompatActivity {

    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String> skill_names, skill_types;
    CustomSkillAdapter customAdapter;
    FloatingActionButton floatingActionButton;

    String no_detail_shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_skills);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List of Skills");

        empty_imageview = findViewById(R.id.empty_imageSkillview);
        no_data = findViewById(R.id.no_dataSkill);
        recyclerView = findViewById(R.id.recyclerSkillView);
        skill_names = new ArrayList<>();
        skill_types = new ArrayList<>();

        storeDataInArrays();

        floatingActionButton = findViewById(R.id.add_button);
        if (getIntent().hasExtra("no_add_btn")){
            floatingActionButton.setVisibility(View.INVISIBLE);
            no_detail_shown = "true";
        }
        else {
            no_detail_shown = "false";
        }
        customAdapter = new CustomSkillAdapter(ListSkills.this,this,
                skill_names, skill_types , no_detail_shown);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListSkills.this));

    }

    void storeDataInArrays(){
        if (SkillsData.laureateSkills == null || SkillsData.laureateSkills.isEmpty()){

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
        finish();
        Intent intent = new Intent(ListSkills.this, AddSkills.class);
        startActivity(intent);
    }

    public void backToLaureate(View view) {
        finish();
    }
}