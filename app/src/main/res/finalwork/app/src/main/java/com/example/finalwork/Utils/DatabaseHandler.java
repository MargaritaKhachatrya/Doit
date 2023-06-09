package com.example.finalwork.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.finalwork.Model.ToDoModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo_db";
    private static final String TABLE_NAME = "todo_table";

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";
    private static final String TIMESTAMP = "timestamp";
    private static final String STATUS = "status";

    private SQLiteDatabase db;

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + CONTENT + " TEXT,"
                + TIMESTAMP + " TEXT,"
                + STATUS + " INTEGER)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void openDatabase() {
        db = this.getWritableDatabase();
    }

    public void addTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, task.getTitle());
        cv.put(CONTENT, task.getContent());
        cv.put(TIMESTAMP, new Date(String.valueOf(task.getTimestamp())).getTime());
        cv.put(STATUS, task.getStatus());
        db.insert(TABLE_NAME, null, cv);
    }

    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cursor = null;
        db.beginTransaction();
        try {
            cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    int idIndex = cursor.getColumnIndex(ID);
                    int titleIndex = cursor.getColumnIndex(TITLE);
                    int contentIndex = cursor.getColumnIndex(CONTENT);
                    int timestampIndex = cursor.getColumnIndex(TIMESTAMP);
                    int statusIndex = cursor.getColumnIndex(STATUS);

                    do {
                        int id = cursor.getInt(idIndex);
                        String title = cursor.getString(titleIndex);
                        String content = cursor.getString(contentIndex);
                        String timestamp = cursor.getString(timestampIndex);
                        int status = cursor.getInt(statusIndex);

                        ToDoModel task = new ToDoModel(title, content, timestamp);
                        task.setId(id);
                        task.setStatus(status);
                        taskList.add(task);
                    } while (cursor.moveToNext());
                }


            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            db.endTransaction();
        }
        return taskList;
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TITLE, task);
        db.update(TABLE_NAME, cv, ID + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }
}
