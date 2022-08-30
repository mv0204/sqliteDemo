package com.example.sqlitedemo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.midi.MidiOutputPort;
import android.view.Display;

import androidx.annotation.Nullable;

import com.example.sqlitedemo.DmModel;

import java.util.ArrayList;

public class MyDbHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "contact";
    public static final int DATABASE_VERSION = 1;
    public static final String KEY_Id = "ID";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_PHONE = "PHONE";

    public MyDbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + KEY_Id + " integer primary key," + KEY_NAME + " TEXT," + KEY_PHONE + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String id,String name, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_Id, id);
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<DmModel> viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DmModel> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            DmModel model = new DmModel();
            model.setKEY_Id(cursor.getInt(0));
            model.setKEY_NAME(cursor.getString(1));
            model.setKEY_PHONE(cursor.getString(2));
            list.add(model);
        }
        return list;
    }

    public void updateData(String id,String name,String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_PHONE, phone);
        db.update(TABLE_NAME, values, KEY_Id + "=?", new String[]{String.valueOf(id)});

    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY_Id + "=?", new String[]{String.valueOf(id)});
    }

    public int countData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor.getCount();
    }
}