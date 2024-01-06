package com.androidproject.dbLocal.script;

public class LaureateScript {
     public static final String TABLE_NAME = "laureate";
     public static final String ID_COLUMN = "id_laureate";
     public static final String NAME_COLUMN = "name";
     public static final String EMAIL_COLUMN = "email";
     public static final String PHONE_COLUMN = "phone";
     public static final String TRAINING_COLUMN = "training";
     public static final String CITY_COLUMN = "city";
     public static final String CREATE_LAUREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_COLUMN + " TEXT, " +
            EMAIL_COLUMN + " TEXT, " +
            PHONE_COLUMN + " TEXT, " +
             CITY_COLUMN + " TEXT, " +
            TRAINING_COLUMN + " TEXT); " ;


}
