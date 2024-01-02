package com.androidproject.activity.laureate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.CustomAdapter;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.activity.laureate.skill.AddSkills;
import com.androidproject.activity.laureate.skill.ListSkills;
import com.androidproject.activity.laureate.skill.SkillsData;
import com.androidproject.models.Laureate.Laureate;
import com.androidproject.models.Laureate.LaureateExperience;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class SchoolDashboardActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Laureate> laureates=new ArrayList<>();
    private FloatingActionButton addCourseFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView courseRV;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    ImageView empty_imageview;
    TextView no_data;
    private ArrayList<String> laureate_names , laureate_trainings , laureate_cities;
    private CustomLaureateAdapter customLaureateAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_dashboard);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);
        recyclerView = findViewById(R.id.recyclerView);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("laureates");
        loadingPB = findViewById(R.id.idPBLoading);
        laureate_names = new ArrayList<>();
        laureate_cities = new ArrayList<>();
        laureate_trainings = new ArrayList<>();
        getLaureates();
    }

    private void getLaureates() {
        //on below line clearing our list.
        laureates.clear();
        //on below line we are calling add child event listener method to read the data.
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //on below line we are hiding our progress bar.
                loadingPB.setVisibility(View.GONE);
                //adding snapshot to our array list on below line.
                laureates.add(snapshot.getValue(Laureate.class));
                //notifying our adapter that data has changed.
                //courseRVAdapter.notifyDataSetChanged();
                LaureateData.laureateList = laureates;
                storeDataInArrays();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //this method is called when new child is added we are notifying our adapter and making progress bar visibility as gone.
                loadingPB.setVisibility(View.GONE);
                //courseRVAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //notifying our adapter when child is removed.
                //courseRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //notifying our adapter when child is moved.
                //courseRVAdapter.notifyDataSetChanged();
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    void storeDataInArrays(){
        if (LaureateData.laureateList.isEmpty()){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }
        else {
                laureate_names.clear();
                laureate_trainings.clear();
                laureate_cities.clear();
                for (Laureate laureate : LaureateData.laureateList) {
                    laureate_names.add(laureate.getName());
                    laureate_trainings.add(laureate.getTraining());
                    laureate_cities.add(laureate.getCity());
                }
                customLaureateAdapter = new CustomLaureateAdapter(SchoolDashboardActivity.this,this,
                        laureate_names, laureate_trainings,
                        laureate_cities);
                recyclerView.setAdapter(customLaureateAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(SchoolDashboardActivity.this));
        }
    }


    public void goToAddExperience(View view) {
        finish();
        Intent intent = new Intent(SchoolDashboardActivity.this, AddLaureareActivity.class);
        startActivity(intent);
    }
}