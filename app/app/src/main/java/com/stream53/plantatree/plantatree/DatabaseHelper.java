package com.stream53.plantatree.plantatree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Login.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_LOGIN_TABLE = "CREATE TABLE " +
                "user(email text PRIMARY KEY, password text)";

        db.execSQL(SQL_CREATE_LOGIN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public boolean addAccount(String email, String password){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password", password);
        long ins = db.insert("user", null, contentValues);

        if(ins==-1) return false;
        else return true;
    }

    public Boolean validateEmail(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE email=?",
                new String[]{email});

        if(c.getCount() > 0)return false;
        else return true;
    }

    public Boolean emailPassword(String email, String password){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?",
                new String[]{email, password});

        if(c.getCount() > 0) return true;
        else return false;
    }
}