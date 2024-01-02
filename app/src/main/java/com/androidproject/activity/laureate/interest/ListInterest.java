package com.androidproject.activity.laureate.interest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.CustomAdapter;
import com.androidproject.activity.laureate.skill.AddSkills;
import com.androidproject.activity.laureate.skill.CustomSkillAdapter;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateInterests;
import com.androidproject.models.Laureate.LaureateSkill;

import java.util.ArrayList;

public class ListInterest extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    ArrayList<String> interest_names;
    CustomInterestAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_interest);
        empty_imageview = findViewById(R.id.empty_imageInterestview);
        no_data = findViewById(R.id.no_dataInterest);
        recyclerView = findViewById(R.id.recyclerInterestView);
        interest_names = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomInterestAdapter(ListInterest.this,this,
                interest_names);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListInterest.this));
    }
    void storeDataInArrays(){
        if (InterestData.laureateInterests.isEmpty()){

            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {
            for (LaureateInterests interests : InterestData.laureateInterests) {
                // Add values to respective lists
                interest_names.add(interests.getName());
            }
        }


    }

    public void goToAddInterest(View view) {
        Intent intent = new Intent(ListInterest.this, AddInterest.class);
        startActivity(intent);
    }
}