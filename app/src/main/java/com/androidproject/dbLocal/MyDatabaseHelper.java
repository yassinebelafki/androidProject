package com.androidproject.dbLocal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidproject.dbLocal.script.LaureateExperienceScript;
import com.androidproject.dbLocal.script.LaureateInterestScript;
import com.androidproject.dbLocal.script.LaureateScript;
import com.androidproject.dbLocal.script.LaureateSkillScript;
import com.androidproject.models.Laureate.Laureate;
import com.androidproject.models.Laureate.LaureateExperience;
import com.androidproject.models.Laureate.LaureateInterests;
import com.androidproject.models.Laureate.LaureateSkill;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Laureatedb";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "experiences";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_START_DATE = "start_date";
    private static final String COLUMN_END_DATE = "end_date";
    private static final String COLUMN_DESCRIPTION = "description";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LaureateScript.CREATE_LAUREATE_TABLE);
        db.execSQL(LaureateExperienceScript.CREATE_EXPERIENCE_TABLE);
        db.execSQL(LaureateSkillScript.CREATE_SKILL_TABLE);
        db.execSQL(LaureateInterestScript.CREATE_INTEREST_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + LaureateInterestScript.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LaureateSkillScript.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LaureateExperienceScript.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LaureateScript.TABLE_NAME);
        db.execSQL("PRAGMA foreign_keys=ON");
        onCreate(db);
    }

    public long addLaureateWithDetails(Laureate laureate) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Step 1: Insert basic laureate information
        ContentValues laureateValues = new ContentValues();
        laureateValues.put(LaureateScript.NAME_COLUMN, laureate.getName());
        laureateValues.put(LaureateScript.EMAIL_COLUMN, laureate.getEmail());
        laureateValues.put(LaureateScript.PHONE_COLUMN, laureate.getPhone());
        laureateValues.put(LaureateScript.TRAINING_COLUMN, laureate.getTraining());
        laureateValues.put(LaureateScript.CITY_COLUMN, laureate.getCity());
        laureateValues.put(LaureateScript.AGE_COLUMN, laureate.getAge());

        long laureateId = db.insert(LaureateScript.TABLE_NAME, null, laureateValues);

        // Step 2: Insert experiences
        if (laureate.getLaureateExperiences() != null) {
            for (LaureateExperience experience : laureate.getLaureateExperiences()) {
               addExperience(experience,laureateId);
            }
        }
        // Step 3: Insert interests
        if (laureate.getLaureateInterests() != null) {
            for (LaureateInterests interest : laureate.getLaureateInterests()) {
                addInterest(interest,laureateId);
            }
        }
        // Step 4: Insert skills
        if (laureate.getLaureateSkills() != null) {
            for (LaureateSkill skill : laureate.getLaureateSkills()) {
                addSkill(skill,laureateId);
            }
        }
        db.close();
        return laureateId;
    }


    public void addSkill(LaureateSkill laureateSkill , Long laureate_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateSkillScript.NAME_COLUMN , laureateSkill.getName() );
        cv.put(LaureateSkillScript.TYPE_COLUMN , laureateSkill.getType() );
        cv.put(LaureateSkillScript.ID_LAUREATE_COLUMN , laureate_id );

        long result = db.insert(LaureateSkillScript.TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }
    public void addInterest(LaureateInterests laureateInterest , Long laureate_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateInterestScript.NAME_COLUMN , laureateInterest.getName() );
        cv.put(LaureateInterestScript.ID_LAUREATE_COLUMN , laureate_id );

        long result = db.insert(LaureateInterestScript.TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    public List<Laureate> getAllLaureates() {
        List<Laureate> laureateList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {LaureateScript.ID_COLUMN, LaureateScript.NAME_COLUMN,LaureateScript.AGE_COLUMN, LaureateScript.EMAIL_COLUMN,
                LaureateScript.PHONE_COLUMN, LaureateScript.TRAINING_COLUMN, LaureateScript.CITY_COLUMN};

        Cursor cursor = db.query(LaureateScript.TABLE_NAME, columns, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Integer laureateId = cursor.getInt(cursor.getColumnIndex(LaureateScript.ID_COLUMN));
                String name = cursor.getString(cursor.getColumnIndex(LaureateScript.NAME_COLUMN));
                String email = cursor.getString(cursor.getColumnIndex(LaureateScript.EMAIL_COLUMN));
                String phone = cursor.getString(cursor.getColumnIndex(LaureateScript.PHONE_COLUMN));
                String training = cursor.getString(cursor.getColumnIndex(LaureateScript.TRAINING_COLUMN));
                String city = cursor.getString(cursor.getColumnIndex(LaureateScript.CITY_COLUMN));
                Integer age = cursor.getInt(cursor.getColumnIndex(LaureateScript.AGE_COLUMN));
                // Retrieve experiences, interests, and skills for the current laureate
                List<LaureateExperience> experiences = getExperiencesForLaureate(laureateId);
                List<LaureateInterests> interests = getInterestsForLaureate(laureateId);
                List<LaureateSkill> skills = getSkillsForLaureate(laureateId);

                Laureate laureate = new Laureate(laureateId,name,age, email, phone, training, city, experiences, interests, skills);
                laureateList.add(laureate);

            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return laureateList;
    }




    public void addExperience(LaureateExperience laureateExperience , Long laureate_id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateExperienceScript.TITLE_COLUMN , laureateExperience.getTitle() );
        cv.put(LaureateExperienceScript.DESCRIPTION_COLUMN , laureateExperience.getDescription() );
        cv.put(LaureateExperienceScript.START_DATE_COLUMN , laureateExperience.getStart_date() );
        cv.put(LaureateExperienceScript.END_DATE_COLUMN , laureateExperience.getEnd_date() );
        cv.put(LaureateExperienceScript.ID_LAUREATE_COLUMN , laureate_id );

        long result = db.insert(LaureateExperienceScript.TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("Range")
    public List<LaureateExperience> readAllExperiences(){
        String query = "SELECT * FROM " + LaureateExperienceScript.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        List<LaureateExperience> laureateExperiences=new ArrayList<>();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    LaureateExperience laureateExperience = new LaureateExperience();
                    laureateExperience.setId(cursor.getInt(cursor.getColumnIndex(LaureateExperienceScript.ID_COLUMN)));
                    laureateExperience.setTitle(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.TITLE_COLUMN)));
                    laureateExperience.setStart_date(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.START_DATE_COLUMN)));
                    laureateExperience.setEnd_date(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.END_DATE_COLUMN)));
                    laureateExperiences.add(laureateExperience);
                } while (cursor.moveToNext());
            }

            db.close();
        }
        return laureateExperiences;
    }

    public void updateExperience(LaureateExperience laureateExperience){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateExperienceScript.TITLE_COLUMN , laureateExperience.getTitle() );
        cv.put(LaureateExperienceScript.DESCRIPTION_COLUMN , laureateExperience.getDescription() );
        cv.put(LaureateExperienceScript.START_DATE_COLUMN , laureateExperience.getStart_date() );
        cv.put(LaureateExperienceScript.END_DATE_COLUMN , laureateExperience.getEnd_date() );
        long result = db.update(LaureateExperienceScript.TABLE_NAME, cv, LaureateExperienceScript.ID_COLUMN+"=?", new String[]{String.valueOf(laureateExperience.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Experience Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
    public void updateSkill(LaureateSkill laureateSkill){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateSkillScript.NAME_COLUMN , laureateSkill.getName() );
        cv.put(LaureateSkillScript.TYPE_COLUMN , laureateSkill.getType() );
        long result = db.update(LaureateSkillScript.TABLE_NAME, cv, LaureateSkillScript.ID_COLUMN+"=?", new String[]{String.valueOf(laureateSkill.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Skill Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void updateInterest(LaureateInterests laureateInterest){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(LaureateInterestScript.NAME_COLUMN , laureateInterest.getName() );
        long result = db.update(LaureateInterestScript.TABLE_NAME, cv, LaureateInterestScript.ID_COLUMN+"=?", new String[]{String.valueOf(laureateInterest.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Skill Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneElement(String row_id,String table_name,String id_column_name){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(table_name, id_column_name+"=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(context, "Element Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(String table_name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + table_name);
    }
    @SuppressLint("Range")
    public LaureateExperience getExperienceById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        LaureateExperience laureateExperience = null;

        String[] columns = {
                LaureateExperienceScript.ID_COLUMN,
                LaureateExperienceScript.TITLE_COLUMN,
                LaureateExperienceScript.DESCRIPTION_COLUMN,
                LaureateExperienceScript.START_DATE_COLUMN,
                LaureateExperienceScript.END_DATE_COLUMN
        };

        Cursor cursor = db.query(LaureateExperienceScript.TABLE_NAME, columns, LaureateExperienceScript.ID_COLUMN + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            laureateExperience = new LaureateExperience();
            laureateExperience.setId(cursor.getInt(cursor.getColumnIndex(LaureateExperienceScript.ID_COLUMN)));
            laureateExperience.setTitle(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.TITLE_COLUMN)));
            laureateExperience.setDescription(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.DESCRIPTION_COLUMN)));
            laureateExperience.setStart_date(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.START_DATE_COLUMN)));
            laureateExperience.setEnd_date(cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.END_DATE_COLUMN)));

            cursor.close();
        }

        return laureateExperience;
    }

    @SuppressLint("Range")
    public List<LaureateExperience> getExperiencesForLaureate(int laureateId) {
        List<LaureateExperience> experienceList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {LaureateExperienceScript.ID_COLUMN, LaureateExperienceScript.TITLE_COLUMN,
                LaureateExperienceScript.START_DATE_COLUMN, LaureateExperienceScript.END_DATE_COLUMN,
                LaureateExperienceScript.DESCRIPTION_COLUMN};

        String selection = LaureateExperienceScript.ID_LAUREATE_COLUMN + "=?";
        String[] selectionArgs = {String.valueOf(laureateId)};

        Cursor cursor = db.query(LaureateExperienceScript.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(LaureateExperienceScript.ID_COLUMN));
                String title = cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.TITLE_COLUMN));
                String startDate = cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.START_DATE_COLUMN));
                String endDate = cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.END_DATE_COLUMN));
                String description = cursor.getString(cursor.getColumnIndex(LaureateExperienceScript.DESCRIPTION_COLUMN));

                LaureateExperience experience = new LaureateExperience(id, title, startDate, endDate, description);
                experienceList.add(experience);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return experienceList;
    }

    @SuppressLint("Range")
    public List<LaureateInterests> getInterestsForLaureate(int laureateId) {
        List<LaureateInterests> interestList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {LaureateInterestScript.NAME_COLUMN};

        String selection = LaureateInterestScript.ID_LAUREATE_COLUMN + "=?";
        String[] selectionArgs = {String.valueOf(laureateId)};

        Cursor cursor = db.query(LaureateInterestScript.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(LaureateInterestScript.ID_COLUMN));
                String name = cursor.getString(cursor.getColumnIndex(LaureateInterestScript.NAME_COLUMN));
                LaureateInterests interest = new LaureateInterests(id,name);
                interestList.add(interest);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return interestList;
    }

    @SuppressLint("Range")
    public List<LaureateSkill> getSkillsForLaureate(int laureateId) {
        List<LaureateSkill> skillList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {LaureateSkillScript.ID_COLUMN,
                LaureateSkillScript.TYPE_COLUMN, LaureateSkillScript.NAME_COLUMN};

        String selection = LaureateSkillScript.ID_LAUREATE_COLUMN + "=?";
        String[] selectionArgs = {String.valueOf(laureateId)};

        Cursor cursor = db.query(LaureateSkillScript.TABLE_NAME, columns, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(LaureateSkillScript.ID_COLUMN));
                String type = cursor.getString(cursor.getColumnIndex(LaureateSkillScript.TYPE_COLUMN));
                String name = cursor.getString(cursor.getColumnIndex(LaureateSkillScript.NAME_COLUMN));

                LaureateSkill skill = new LaureateSkill(id, type, name);
                skillList.add(skill);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();

        return skillList;
    }

    public void updateLaureate(Laureate laureate, Laureate oldLaureate) {
        //update the static fields of Laureate
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues laureateValues = new ContentValues();
        laureateValues.put(LaureateScript.NAME_COLUMN, laureate.getName());
        laureateValues.put(LaureateScript.EMAIL_COLUMN, laureate.getEmail());
        laureateValues.put(LaureateScript.PHONE_COLUMN, laureate.getPhone());
        laureateValues.put(LaureateScript.TRAINING_COLUMN, laureate.getTraining());
        laureateValues.put(LaureateScript.CITY_COLUMN, laureate.getCity());
        laureateValues.put(LaureateScript.AGE_COLUMN, laureate.getAge());
        long result = db.update(LaureateScript.TABLE_NAME, laureateValues, LaureateScript.ID_COLUMN+"=?", new String[]{String.valueOf(laureate.getLaureateId())});
        if(result == -1){
            Toast.makeText(context, "Failed to update laureate!", Toast.LENGTH_SHORT).show();
        }else {
            //Experiences
            //delete The Missing values from the old laureate
            deleteMissingExperiences(laureate.getLaureateExperiences() , oldLaureate.getLaureateExperiences());
            //update and add the new Experiences
            updateAndAddNewExperiences(laureate.getLaureateExperiences(),laureate.getLaureateId());

            //Skills
            //delete The Missing values from the old Skills
            deleteMissingSkills(laureate.getLaureateSkills() , oldLaureate.getLaureateSkills());
            //update and add the new Experiences
            updateAndAddNewExperiences(laureate.getLaureateExperiences(),laureate.getLaureateId());

            //delete The Missing values from the old laureate
            deleteMissingExperiences(laureate.getLaureateExperiences() , oldLaureate.getLaureateExperiences());
            //update and add the new Experiences
            updateAndAddNewExperiences(laureate.getLaureateExperiences(),laureate.getLaureateId());


        }
    }

    private void deleteMissingSkills(List<LaureateSkill> laureateSkills, List<LaureateSkill> laureateSkills1) {
        List<Integer> oldIds = fetchExperienceIds(oldLaureateExperiences);
        List<Integer> newIds = fetchExperienceIds(newLaureateExperiences);
        List<Integer> missingIds = new ArrayList<>(oldIds);
        missingIds.removeAll(newIds);
        if (missingIds.isEmpty())
            return;
        for (Integer idToDelete : missingIds){
            deleteOneElement(String.valueOf(idToDelete), LaureateExperienceScript.TABLE_NAME , LaureateExperienceScript.ID_COLUMN );
        }
    }

    private void updateAndAddNewExperiences(List<LaureateExperience> laureateExperiences, Integer laureateId) {
        for (LaureateExperience laureateExperience:laureateExperiences) {
            if (laureateExperience.getId() == null){
                addExperience(laureateExperience , Long.valueOf(laureateId));
            }
            else {
                updateExperience(laureateExperience);
            }
        }
    }

    private void deleteMissingExperiences(List<LaureateExperience> newLaureateExperiences, List<LaureateExperience> oldLaureateExperiences) {
        List<Integer> oldIds = fetchExperienceIds(oldLaureateExperiences);
        List<Integer> newIds = fetchExperienceIds(newLaureateExperiences);
        List<Integer> missingIds = new ArrayList<>(oldIds);
        missingIds.removeAll(newIds);
        if (missingIds.isEmpty())
            return;
        for (Integer idToDelete : missingIds){
            deleteOneElement(String.valueOf(idToDelete), LaureateExperienceScript.TABLE_NAME , LaureateExperienceScript.ID_COLUMN );
        }
    }

    private List<Integer> fetchExperienceIds(List<LaureateExperience> laureateExperiences) {
        return laureateExperiences.stream()
                .filter(laureateExperience -> laureateExperience.getId() != null)
                .map(LaureateExperience::getId)
                .collect(Collectors.toList());
    }
}
