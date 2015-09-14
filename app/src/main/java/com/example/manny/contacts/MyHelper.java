package com.example.manny.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by manny on 9/14/2015 AD.
 */
public class MyHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contact.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contacts";
    public static final String COL_ID = "_id";
    public static final String COL_NAME = "name";
    public static final String COL_PHONE_NUMBER = "phone_number";


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreaTable = "CREATE TABLE %s ( " +
                              "%s INTEGER PRIMARY KEY AUTOINCREMENT, "+
                              "%s TEXT , " +
                              "%s TEXT)";

        sqlCreaTable = String.format(sqlCreaTable,TABLE_NAME,COL_ID,COL_NAME,COL_PHONE_NUMBER);
        db.execSQL(sqlCreaTable);
        db.insert(TABLE_NAME, null, addContent("Man","086-0278298"));
        db.insert(TABLE_NAME, null ,addContent("Bo","089-8360212"));

    }

    public ContentValues addContent(String msgName ,String msgPhoneNumber){
        ContentValues cv  = new ContentValues();
        cv.put(COL_NAME , msgName);
        cv.put(COL_PHONE_NUMBER, msgPhoneNumber);
        return cv;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
