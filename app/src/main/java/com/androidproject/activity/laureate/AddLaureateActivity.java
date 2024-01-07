package com.androidproject.activity.laureate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.AddExperienceActivity;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.interest.InterestData;
import com.androidproject.activity.laureate.interest.ListInterest;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.Laureate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddLaureateActivity extends AppCompatActivity {
    private TextInputEditText laureateNameInput,laureateAgeInput,laureateEmailInput
            , laureatePhoneInput , laureateCityInput , laureateTrainingInput;
    String laureateName,laureateAge,laureateEmail
            , laureatePhone , laureateCity , laureateTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_laureare);
        laureateNameInput = findViewById(R.id.idEdtLaureateName);
        laureateAgeInput = findViewById(R.id.idEdtLaureateAge);
        laureateEmailInput = findViewById(R.id.idEdtLaureateEmail);
        laureatePhoneInput = findViewById(R.id.idEdtLaureatePhone);
        laureateCityInput = findViewById(R.id.idEdtLaureateCity);
        laureateTrainingInput = findViewById(R.id.idEdtLaureateTraining);
        ExperienceData.laureateExperienceList.clear();
        SkillsData.laureateSkills.clear();
        InterestData.laureateInterests.clear();
    }

    public void goToExperienceList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListExperienceActivity.class);
        startActivity(intent);
    }

    public void goToSkillsList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListSkills.class);
        startActivity(intent);
    }

    public void goToInterestsList(View view) {
        Intent intent = new Intent(AddLaureateActivity.this, ListInterest.class);
        startActivity(intent);
    }

    public void addNewLaureate(View view) {
        laureateName = laureateNameInput.getText().toString();
        laureateEmail = laureateEmailInput.getText().toString();
        laureateAge = laureateAgeInput.getText().toString();
        laureatePhone = laureatePhoneInput.getText().toString();
        laureateTraining = laureateTrainingInput.getText().toString();
        laureateCity = laureateCityInput.getText().toString();
        Laureate laureate = new Laureate(laureateName , Integer.parseInt(laureateAge) , laureateEmail , laureatePhone , laureateTraining
                , laureateCity , ExperienceData.laureateExperienceList , InterestData.laureateInterests , SkillsData.laureateSkills);
        // Get a reference to your Firebase database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference laureateRef = database.getReference("laureates"); // 'laureates' is the name of your node in the database

        // Push the Laureate object to the database
      //  String newLaureateKey = laureateRef.push().getKey(); // Generate a unique key for the new record
       // laureateRef.child(newLaureateKey).setValue(laureate);

        // Optionally, you can add an onCompleteListener to check if the operation was successful

        MyDatabaseHelper myDB = new MyDatabaseHelper(AddLaureateActivity.this);
        myDB.addLaureateWithDetails(laureate);
        SkillsData.laureateSkills.clear();
        InterestData.laureateInterests.clear();
        ExperienceData.laureateExperienceList.clear();
        Toast.makeText(getApplicationContext(), "New Laureate is added successfully!", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent = new Intent(AddLaureateActivity.this, SchoolDashboardActivity.class);
        startActivity(intent);

//        laureateRef.child(newLaureateKey).setValue(laureate)
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        SkillsData.laureateSkills.clear();
//                        InterestData.laureateInterests.clear();
//                        ExperienceData.laureateExperienceList.clear();
//                        Toast.makeText(getApplicationContext(), "New Laureate is added successfully!", Toast.LENGTH_SHORT).show();
//                        finish();
//                        Intent intent = new Intent(AddLaureateActivity.this, SchoolDashboardActivity.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Error while adding new Laureate!", Toast.LENGTH_SHORT).show();
//                    }
//                });
    }
}