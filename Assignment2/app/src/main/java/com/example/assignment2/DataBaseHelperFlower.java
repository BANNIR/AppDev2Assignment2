package com.example.assignment2;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelperFlower extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Flowers.db";
    private static final String TABLE_NAME = "Gift_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "Description";
    private static final String COL_4 = "isFavorite";


    public DataBaseHelperFlower(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID Integer primary key autoincrement, Name Text, Description Text, isFavorite Boolean)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }

    //inserts data
    public boolean insertData (String name, String Description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,Description);
        contentValues.put(COL_4, false);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }

    //gets all data
    public Cursor getALlData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    //deletes data
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, " ID = ?", new String[] {id});

    }

//    public void deleteAllData () {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("Drop table if exists " + TABLE_NAME);
//        db.execSQL("create table " + TABLE_NAME + " (ID Integer primary key autoincrement, Name Text, Description Text, isFavorite Boolean)");
//    }

    //updates the favorite column
    public boolean updateFav (int fav, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_4, fav);
        db.update(TABLE_NAME, contentValues, "ID = ? ", new String[] {id});
        return  true;


    }

    //gets all the items that are favorited
    public Cursor getALlFav() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + COL_4 + " = 1", null);
        return res;
    }


}