package com.androidproject.dbLocal;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.androidproject.models.Laureate.LaureateExperience;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Laureate.db";
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
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_START_DATE + " TEXT, " +
                        COLUMN_END_DATE + " TEXT); " ;
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addExperience(LaureateExperience laureateExperience){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TITLE , laureateExperience.getTitle() );
        cv.put(COLUMN_DESCRIPTION , laureateExperience.getDescription() );
        cv.put(COLUMN_START_DATE , laureateExperience.getStart_date() );
        cv.put(COLUMN_END_DATE , laureateExperience.getEnd_date() );

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Experience Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateExperience(LaureateExperience laureateExperience){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, laureateExperience.getTitle());
        cv.put(COLUMN_DESCRIPTION , laureateExperience.getDescription() );
        cv.put(COLUMN_START_DATE , laureateExperience.getStart_date() );
        cv.put(COLUMN_END_DATE , laureateExperience.getEnd_date() );

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{String.valueOf(laureateExperience.getId())});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Experience Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Experience Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
    @SuppressLint("Range")
    public LaureateExperience getExperienceById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        LaureateExperience laureateExperience = null;

        String[] columns = {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_DESCRIPTION,
                COLUMN_START_DATE,
                COLUMN_END_DATE
        };

        Cursor cursor = db.query(TABLE_NAME, columns, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            laureateExperience = new LaureateExperience();
            laureateExperience.setId( cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
            laureateExperience.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
            laureateExperience.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            laureateExperience.setStart_date(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE)));
            laureateExperience.setEnd_date(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE)));

            cursor.close();
        }

        return laureateExperience;
    }


}
