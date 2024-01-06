package com.androidproject.dbLocal.script;

public class LaureateExperienceScript {

    public  static final String TABLE_NAME = "experience";
    public  static final String ID_COLUMN = "id_exp";
    public  static final String TITLE_COLUMN = "title";
    public  static final String DESCRIPTION_COLUMN = "description";
    public  static final String START_DATE_COLUMN = "start_date";
    public  static final String END_DATE_COLUMN = "end_date";
    public  static final String ID_LAUREATE_COLUMN = "id_laureate";

    public static final String CREATE_EXPERIENCE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE_COLUMN + " TEXT, " +
            DESCRIPTION_COLUMN + " TEXT, " +
            START_DATE_COLUMN + " TEXT, " +
            END_DATE_COLUMN + " TEXT, " +
            ID_LAUREATE_COLUMN + " INTEGER, " +
            "FOREIGN KEY(id_laureate) REFERENCES laureate(id_laureate) ON DELETE CASCADE); ";



}
