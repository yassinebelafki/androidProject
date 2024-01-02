package com.androidproject.activity.laureate.skill;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.androidproject.R;
import com.androidproject.activity.laureate.experience.EditExperienceActivity;
import com.androidproject.activity.laureate.experience.ExperienceData;
import com.androidproject.activity.laureate.experience.ListExperienceActivity;
import com.androidproject.dbLocal.MyDatabaseHelper;
import com.androidproject.models.Laureate.LaureateExperience;
import com.androidproject.models.Laureate.LaureateSkill;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Arrays;
import java.util.Optional;

public class EditSkills extends AppCompatActivity {

    private TextInputEditText skillNameInput;
    private Spinner spinner;
    private  String skillType;
    private LaureateSkill laureateSkill;

    private String[] options;

    LaureateExperience laureateExperience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_skills);

        spinner = findViewById(R.id.skillTypeUpdate);
        skillNameInput = findViewById(R.id.idEdtskillNameUpdate);



        spinner = findViewById(R.id.skillTypeUpdate);
         options = new String[]{"Option 1", "Option 2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                skillType = options[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });
        getAndSetIntentData();
    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("skill_name")){
            Optional<LaureateSkill> result = SkillsData.laureateSkills.stream().filter(
                    skill -> skill.getName().equals(getIntent().getStringExtra("skill_name")) &&
                            skill.getType().equals(getIntent().getStringExtra("skill_type"))).findFirst();
            if (result.isPresent()){
                laureateSkill = result.get();
                //Setting Intent Data
                spinner.setSelection(Arrays.asList(options).indexOf(laureateSkill.getType()));
                skillNameInput.setText(laureateSkill.getName());
            }
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateSkill(View view) {
        laureateSkill.setName(skillNameInput.getText().toString());
        laureateSkill.setType(skillType);
        finish();
        Intent intent = new Intent(EditSkills.this, ListSkills.class);
        startActivity(intent);

    }

    public void deleteSkill(View view) {
        confirmDialog();
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + skillNameInput.getText().toString() + " ?");
        builder.setMessage("Are you sure you want to delete " + "it" + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                    MyDatabaseHelper myDB = new MyDatabaseHelper(EditExperienceActivity.this);
//                    myDB.deleteOneRow(String.valueOf(laureateExperience.getId()));
                SkillsData.laureateSkills.remove(laureateSkill);
                finish();
                Intent intent = new Intent(EditSkills.this, ListSkills.class);
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