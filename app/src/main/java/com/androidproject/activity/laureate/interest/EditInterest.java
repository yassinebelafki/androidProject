package com.androidproject.activity.laureate.interest;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.EditExperienceActivity;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.androidproject.models.Laureate.LaureateInterests;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Optional;

public class EditInterest extends AppCompatActivity {
    private TextInputEditText interestNameInput ;
    private LaureateInterests laureateInterests;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_interest);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Edit Interest");
        interestNameInput = findViewById(R.id.idEdtinterestNameUpdate);
        getAndSetIntentData();
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setTitle((CharSequence) interestNameInput);
//        }
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("interest_name")){
//                 laureateExperience = myDatabaseHelper
//                        .getExperienceById(getIntent().getStringExtra("experience_id"));
            Optional<LaureateInterests> result = InterestData.laureateInterests.stream().filter(
                        interests -> interests.getName().equals(getIntent().getStringExtra("interest_name"))).findFirst();
            if (result.isPresent()){
                laureateInterests = result.get();
                //Setting Intent Data
                interestNameInput.setText(laureateInterests.getName());
            }
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateInterest(View view) {
        laureateInterests.setName(interestNameInput.getText().toString());
        finish();
        Intent intent = new Intent(EditInterest.this, ListInterest.class);
        startActivity(intent);
    }

    public void deleteInterest(View view) {
        confirmDialog();
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + laureateInterests.getName() + " ?");
        builder.setMessage("Are you sure you want to delete " + "it" + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                InterestData.laureateInterests.remove(laureateInterests);
                Intent intent = new Intent(EditInterest.this, ListInterest.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}