package com.androidproject.activity.laureate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.AddExperienceActivity;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;

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
}