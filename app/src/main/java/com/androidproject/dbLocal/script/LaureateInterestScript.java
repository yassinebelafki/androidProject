package com.androidproject.dbLocal.script;

public class LaureateInterestScript {
    public  static final String TABLE_NAME = "interest";
    public  static final String ID_COLUMN = "id_interest";
    public  static final String NAME_COLUMN = "name";
    public  static final String ID_LAUREATE_COLUMN = "id_laureate";

    public static final String CREATE_INTEREST_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_COLUMN + " TEXT, " +
            ID_LAUREATE_COLUMN + " INTEGER, " +
            "FOREIGN KEY(id_laureate) REFERENCES laureate(id_laureate) ON DELETE CASCADE); ";


}
