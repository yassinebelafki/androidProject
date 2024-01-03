package com.androidproject.activity.laureate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.interest.InterestData;
import com.androidproject.activity.laureate.interest.ListInterest;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditLaureateActivity extends AppCompatActivity {
    private TextInputEditText laureateNameInput,laureateAgeInput,laureateEmailInput
            , laureatePhoneInput , laureateCityInput , laureateTrainingInput;

    private String uniqueLaureateId;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_laureate);
        laureateNameInput = findViewById(R.id.idEdtLaureateNameUpdate);
        laureateAgeInput = findViewById(R.id.idEdtLaureateAgeUpdate);
        laureateEmailInput = findViewById(R.id.idEdtLaureateEmailUpdate);
        laureatePhoneInput = findViewById(R.id.idEdtLaureatePhoneUpdate);
        laureateCityInput = findViewById(R.id.idEdtLaureateCityUpdate);
        laureateTrainingInput = findViewById(R.id.idEdtLaureateTrainingUpdate);

        laureateNameInput.setText(LaureateData.editedLaureate.getName());
        laureateAgeInput.setText(LaureateData.editedLaureate.getAge().toString());
        laureateCityInput.setText(LaureateData.editedLaureate.getCity());
        laureateEmailInput.setText(LaureateData.editedLaureate.getEmail());
        laureateTrainingInput.setText(LaureateData.editedLaureate.getTraining());
        laureatePhoneInput.setText(LaureateData.editedLaureate.getPhone());

        uniqueLaureateId = LaureateData.laureateUniqueIdentifier;
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("laureates");

    }

    public void updateLaureate(View view) {
        LaureateData.editedLaureate.setName(laureateNameInput.getText().toString());
        LaureateData.editedLaureate.setAge(Integer.valueOf(laureateAgeInput.getText().toString()));
        LaureateData.editedLaureate.setCity(laureateCityInput.getText().toString());
        LaureateData.editedLaureate.setPhone(laureatePhoneInput.getText().toString());
        LaureateData.editedLaureate.setEmail(laureateEmailInput.getText().toString());
        LaureateData.editedLaureate.setTraining(laureateTrainingInput.getText().toString());
        LaureateData.editedLaureate.setLaureateExperiences(ExperienceData.laureateExperienceList);
        LaureateData.editedLaureate.setLaureateInterests(InterestData.laureateInterests);
        LaureateData.editedLaureate.setLaureateSkills(SkillsData.laureateSkills);
        databaseReference = firebaseDatabase.getReference("laureates").child(uniqueLaureateId);
        databaseReference.setValue(LaureateData.editedLaureate).addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Laureate Updated..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditLaureateActivity.this, SchoolDashboardActivity.class);
            startActivity(intent);
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error while Updating Laureate..", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(EditLaureateActivity.this, SchoolDashboardActivity.class);
            startActivity(intent);
        });
    }

    public void goToExperienceList(View view) {
        Intent intent = new Intent(EditLaureateActivity.this, ListExperienceActivity.class);
        startActivity(intent);
    }

    public void goToSkillsList(View view) {
        Intent intent = new Intent(EditLaureateActivity.this, ListSkills.class);
        startActivity(intent);
    }

    public void goToInterestsList(View view) {
        Intent intent = new Intent(EditLaureateActivity.this, ListInterest.class);
        startActivity(intent);
    }
}