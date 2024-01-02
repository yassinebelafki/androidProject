    package com.androidproject.activity.laureate.experience;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Optional;

    public class EditExperienceActivity extends AppCompatActivity {
        private TextInputEditText experienceTitle, experienceDescription, experienceStartDate
                , experienceEndDate, courseImgEdt, courseLinkEdt;
        private MyDatabaseHelper myDatabaseHelper;
        LaureateExperience laureateExperience;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_experience);
        myDatabaseHelper = new MyDatabaseHelper(EditExperienceActivity.this);
        experienceTitle = findViewById(R.id.idEdtexperienceTitle);
        experienceDescription = findViewById(R.id.idEdtexperienceDescription);
        experienceStartDate = findViewById(R.id.idEdtexpStartDate);
        experienceEndDate = findViewById(R.id.idEdtexpEndDate);
        getAndSetIntentData();
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
                ab.setTitle((CharSequence) experienceTitle);
        }


    }
        void getAndSetIntentData(){
            if(getIntent().hasExtra("experience_title")){
//                 laureateExperience = myDatabaseHelper
//                        .getExperienceById(getIntent().getStringExtra("experience_id"));
                Optional<LaureateExperience> result = ExperienceData.laureateExperienceList.stream().filter(
                        laureateExperience1 -> laureateExperience1.getTitle().equals(getIntent().getStringExtra("experience_title")) &&
                                laureateExperience1.getStart_date().equals(getIntent().getStringExtra("experience_start_date")) &&
                                laureateExperience1.getEnd_date().equals(getIntent().getStringExtra("experience_end_date"))).findFirst();
                if (result.isPresent()){
                    laureateExperience = result.get();
                    //Setting Intent Data
                    experienceTitle.setText(laureateExperience.getTitle());
                    experienceDescription.setText(laureateExperience.getDescription());
                    experienceStartDate.setText(laureateExperience.getStart_date());
                    experienceEndDate.setText(laureateExperience.getEnd_date());
                }
            }else{
                Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
            }
        }

        public void updateExperience(View view) {
//
//            LaureateExperience laureateExperience =
//                    new LaureateExperience(experienceTitle.getText().toString(),experienceDescription.getText().toString(),
//                    experienceStartDate.getText().toString(),experienceEndDate.getText().toString());
           // myDatabaseHelper.updateExperience(laureateExperience);
            laureateExperience.setTitle(experienceTitle.getText().toString());
            laureateExperience.setDescription(experienceDescription.getText().toString());
            laureateExperience.setStart_date(experienceStartDate.getText().toString());
            laureateExperience.setEnd_date(experienceEndDate.getText().toString());
            finish();
            Intent intent = new Intent(EditExperienceActivity.this, ListExperienceActivity.class);
            startActivity(intent);
        }

        public void deleteExperience(View view) {
            confirmDialog();

        }
        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete " + experienceTitle + "Experience ?");
            builder.setMessage("Are you sure you want to delete " + "it" + " ?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
//                    MyDatabaseHelper myDB = new MyDatabaseHelper(EditExperienceActivity.this);
//                    myDB.deleteOneRow(String.valueOf(laureateExperience.getId()));
                    ExperienceData.laureateExperienceList.remove(laureateExperience);
                   finish();
                    Intent intent = new Intent(EditExperienceActivity.this, ListExperienceActivity.class);
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