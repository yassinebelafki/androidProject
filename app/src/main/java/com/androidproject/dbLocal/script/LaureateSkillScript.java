package com.androidproject.dbLocal.script;

public class LaureateSkillScript {

    public  static final String TABLE_NAME = "skill";
    public  static final String ID_COLUMN = "id_skill";
    public  static final String NAME_COLUMN = "name";
    public  static final String TYPE_COLUMN = "type";
    public  static final String ID_LAUREATE_COLUMN = "id_laureate";

    public static final String CREATE_SKILL_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_COLUMN + " TEXT, " +
            TYPE_COLUMN + " TEXT, " +
            ID_LAUREATE_COLUMN + " INTEGER, " +
            "FOREIGN KEY(id_laureate) REFERENCES laureate(id_laureate) ON DELETE CASCADE); ";


}
