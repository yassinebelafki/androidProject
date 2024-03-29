package com.androidproject.activity.laureate.experience;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListExperienceActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MyDatabaseHelper myDB;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String> experience_ids, experience_titles,experience_descriptions, experience_start_dates, experience_end_dates;
    CustomAdapter customAdapter;
    FloatingActionButton floatingActionButton;
    String no_detail_shown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_experience);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("List of experiences");
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        recyclerView = findViewById(R.id.recyclerView);
        myDB = new MyDatabaseHelper(ListExperienceActivity.this);
        experience_ids = new ArrayList<>();
        experience_titles = new ArrayList<>();
        experience_start_dates = new ArrayList<>();
        experience_end_dates = new ArrayList<>();
        experience_descriptions = new ArrayList<>();
        floatingActionButton = findViewById(R.id.add_button);
        if (getIntent().hasExtra("no_add_btn")){
            floatingActionButton.setVisibility(View.INVISIBLE);
            no_detail_shown = "true";
        }
        else {
            no_detail_shown = "false";
        }
        storeDataInArrays();
        customAdapter = new CustomAdapter(ListExperienceActivity.this,this,
                 experience_titles, experience_start_dates,
                experience_end_dates,no_detail_shown);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListExperienceActivity.this));



    }
    void storeDataInArrays(){
        if (ExperienceData.laureateExperienceList == null || ExperienceData.laureateExperienceList.isEmpty()){

            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {
            for (LaureateExperience experience : ExperienceData.laureateExperienceList) {
                // Add values to respective lists
                experience_titles.add(experience.getTitle());
                experience_descriptions.add(experience.getDescription());
                experience_start_dates.add(experience.getStart_date());
                experience_end_dates.add(experience.getEnd_date());
            }
        }


    }

    public void goToAddExperience(View view) {
        finish();
        Intent intent = new Intent(ListExperienceActivity.this, AddExperienceActivity.class);
        startActivity(intent);
    }

    public void goToAddLaureate(View view) {
    }
}