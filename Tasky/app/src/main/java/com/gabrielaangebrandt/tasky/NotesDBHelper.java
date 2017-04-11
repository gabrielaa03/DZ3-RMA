package com.gabrielaangebrandt.tasky;/*
package com.gabrielaangebrandt.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

*/

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Gabriela on 11.4.2017..
 */

public class NotesDBHelper extends SQLiteOpenHelper {
    private  static NotesDBHelper mNotesDBHelper = null ;




    static final String CREATE_TABLE_TASKS = " CREATE TABLE " + Schema.TABLE_TASKS + " ( "
            + Schema.TITLE + " TEXT, " + Schema.DESCRIPTION + " TEXT, " + Schema.PRIORITY + " TEXT); ";

    static final String DROP_TABLE_TASKS = " DROP TABLE IF EXISTS " + Schema.TABLE_TASKS;

    static final String SELECT_ALL_TASKS = " SELECT " + Schema.TITLE + " , " + Schema.DESCRIPTION + " , "
            +
    Schema.PRIORITY + " FROM " + Schema.TABLE_TASKS;




    private NotesDBHelper (Context context){
        super(context.getApplicationContext(), Schema.DATABASE_NAME, null, Schema.SCHEMA_VERSION);
    }

    public  static synchronized  NotesDBHelper getInstance(Context context){
        if(mNotesDBHelper == null){
            mNotesDBHelper = new NotesDBHelper(context);
        }
        return mNotesDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASKS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_TASKS);
        this.onCreate(db);
    }

    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.TITLE, task.getNaslov());
        contentValues.put(Schema.DESCRIPTION, task.getOpis());
        contentValues.put(Schema.PRIORITY, task.getPrioritet());
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.insert(Schema.TABLE_TASKS, Schema.TITLE, contentValues);
        writableDatabase.close();
    }
    public ArrayList<Task> getAllNotes(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery(SELECT_ALL_TASKS, null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do {
                String title = cursor.getString(0);
                String description = cursor.getString(1);
                Integer priority = cursor.getInt(2);
                tasks.add(new Task(title, description, priority));
            } while (cursor.moveToNext());
        }
        cursor.close();
        writableDatabase.close();
        return tasks;
    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 1;
        private static final String DATABASE_NAME = "Tasks.db";

        static final String TABLE_TASKS = "Tasks1";
        static final String TITLE = "Tasks";
        static final String DESCRIPTION = "About";
        static final String PRIORITY = "Priority";
    }

}

