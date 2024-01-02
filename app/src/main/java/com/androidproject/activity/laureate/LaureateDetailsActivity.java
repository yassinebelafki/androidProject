package com.androidproject.activity.laureate;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.interest.InterestData;
import com.androidproject.activity.laureate.interest.ListInterest;
import com.androidproject.activity.laureate.skill.EditSkills;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.models.Laureate.Laureate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Optional;

public class LaureateDetailsActivity extends AppCompatActivity {
    TextView textViewName , textViewAge , textViewCity , textViewTraining , textViewPhone , textViewEmail ,
            textViewExperience , textViewSkill , textViewInterest ;
    Laureate myLaureate = new Laureate();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    String laureateName;
    String uniqueIdentifier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laureate_details);
        textViewName = findViewById(R.id.textViewName);
        textViewCity = findViewById(R.id.textViewCity);
        textViewAge = findViewById(R.id.textViewAge);
        textViewTraining = findViewById(R.id.textViewTraining);
        textViewPhone = findViewById(R.id.textViewPhone);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewExperience = findViewById(R.id.textViewExperience);
        textViewInterest = findViewById(R.id.textViewInterest);
        textViewSkill = findViewById(R.id.textViewSkills);
        getAndSetIntentData();

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("laureates");

        String laureateNameToDelete = myLaureate.getName();
        // Create a query to find the Laureate with a specific name
        Query query = databaseReference.orderByChild("name").equalTo(laureateNameToDelete);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Retrieve the key for the Laureate with the specified name
                     uniqueIdentifier = snapshot.getKey();
                    databaseReference = firebaseDatabase.getReference("laureates").child(uniqueIdentifier);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("laureate_name")){
            Optional<Laureate> result = LaureateData.laureateList.stream().filter(
                    laureate -> laureate.getName().equals(getIntent().getStringExtra("laureate_name")) &&
                            laureate.getTraining().equals(getIntent().getStringExtra("laureate_training")) &&
                            laureate.getCity().equals(getIntent().getStringExtra("laureate_city"))).findFirst();
            if (result.isPresent()){
                myLaureate = result.get();
                ExperienceData.laureateExperienceList = myLaureate.getLaureateExperiences();
                SkillsData.laureateSkills = myLaureate.getLaureateSkills();
                InterestData.laureateInterests = myLaureate.getLaureateInterests();
                //Setting Intent Data
                textViewName.setText(myLaureate.getName());
                textViewPhone.setText(myLaureate.getPhone());
                textViewTraining.setText(myLaureate.getTraining());
                textViewAge.setText(myLaureate.getAge().toString());
                textViewCity.setText(myLaureate.getCity());
                textViewEmail.setText(myLaureate.getEmail());
                laureateName = myLaureate.getName();
            }
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToExperienceList(View view) {
        Intent intent = new Intent(LaureateDetailsActivity.this, ListExperienceActivity.class);
        intent.putExtra("no_add_btn", "no_add_btn");
        startActivity(intent);
    }

    public void goToSkillsList(View view) {
        Intent intent = new Intent(LaureateDetailsActivity.this, ListSkills.class);
        intent.putExtra("no_add_btn", "no_add_btn");
        startActivity(intent);
    }

    public void goToInterestsList(View view) {
        Intent intent = new Intent(LaureateDetailsActivity.this, ListInterest.class);
        intent.putExtra("no_add_btn", "no_add_btn");
        startActivity(intent);
    }

    public void deleteLaureate(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + myLaureate.getName() + " ?");
        builder.setMessage("Are you sure you want to delete " + "it" + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                executeDeletion();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }

    public void executeDeletion(){
        //on below line calling a method to delete the course.
        databaseReference.removeValue().addOnSuccessListener(aVoid -> {
            Toast.makeText(this, "Laureate Deleted..", Toast.LENGTH_SHORT).show();
        }).addOnFailureListener(e -> {
            Toast.makeText(this, "Error while deleting Laureate..", Toast.LENGTH_SHORT).show();
        });;
        startActivity(new Intent(LaureateDetailsActivity.this, SchoolDashboardActivity.class));

    }


    public void editLaureate(View view) {
    }
}