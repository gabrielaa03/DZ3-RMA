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


    static final String CREATE_TABLE_TASKS1= "CREATE TABLE " + Schema.TABLE_TASKS1 +
            " (" + Schema.ID + " INTEGER," + Schema.TITLE + " TEXT," + Schema.DESCRIPTION + " TEXT," + Schema.PRIORITY + " INTEGER);";
    static final String DROP_TABLE_TASKS1 = "DROP TABLE IF EXISTS " + Schema.TABLE_TASKS1;
    static final String SELECT_ALL_TASKS1 = "SELECT " + Schema.ID + "," + Schema.TITLE + "," + Schema.DESCRIPTION + ","
            + Schema.PRIORITY + " FROM " + Schema.TABLE_TASKS1;



    public NotesDBHelper(Context context){
        super(context.getApplicationContext(), Schema.DATABASE_NAME, null, Schema.SCHEMA_VERSION);
    }

    public  static synchronized NotesDBHelper getInstance(Context context){
        if(mNotesDBHelper == null){
            mNotesDBHelper = new NotesDBHelper(context);
        }
        return mNotesDBHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TASKS1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_TASKS1);
        this.onCreate(db);
    }

    public void insertTask(Task task){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Schema.ID, task.getID());
        contentValues.put(Schema.TITLE, task.getNaslov());
        contentValues.put(Schema.DESCRIPTION, task.getOpis());
        contentValues.put(Schema.PRIORITY, task.getPrioritet());
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        writableDatabase.insert(Schema.TABLE_TASKS1, Schema.TITLE, contentValues);
        writableDatabase.close();
    }
    public ArrayList<Task> getAllNotes(){
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery(SELECT_ALL_TASKS1, null);
        ArrayList<Task> tasks = new ArrayList<>();
        if(cursor.moveToFirst()) {
            do
            {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String description = cursor.getString(2);
                int priority = cursor.getInt(3);

                tasks.add(new Task(id, title, description, priority));
            } while (cursor.moveToNext());
        }
        cursor.close();
        writableDatabase.close();
        return tasks;
    }

    public void deleteEntry(int id) {

        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Schema.TABLE_TASKS1, Schema.ID + "=?", new String[] {String.valueOf(id)});
        db.close();

    }
    public static class Schema{
        private static final int SCHEMA_VERSION = 2;
        private static final String DATABASE_NAME = "Task.db";

        static final String TABLE_TASKS1 = "Zadaci";
        static final String ID = "ID";
        static final String TITLE = "Tasks";
        static final String DESCRIPTION = "About";
        static final String PRIORITY = "Priority";

    }

}

