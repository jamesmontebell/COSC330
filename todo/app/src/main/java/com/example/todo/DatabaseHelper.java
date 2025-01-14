package com.example.todo;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "Todo"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database

    DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase(db);
    }

//    public static void insertTodo(SQLiteDatabase db, String text) {
//        db.execSQL("INSERT INTO Todo (todo) VALUES ("+text+")");
//    }
    public void updateMyDatabase(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE Todo(todo varchar(64));");
    }
}
