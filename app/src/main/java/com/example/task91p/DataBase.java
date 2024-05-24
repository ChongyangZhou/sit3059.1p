package com.example.task91p;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    // Database name and version
    private static final String DATABASE_NAME = "MyDatabase.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_NAME = "my_table";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_ADDRESS = "address";

    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create table SQL query
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TYPE + " TEXT,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PHONE + " TEXT,"
                + COLUMN_DESCRIPTION + " TEXT,"
                + COLUMN_DATE + " TEXT,"
                + COLUMN_ADDRESS + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // Create tables again
        onCreate(db);
    }

    // Method to insert a new row
    public long insertData(String type, String name, String phone, String description, String date, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_ADDRESS, address);
        return db.insert(TABLE_NAME, null, values);
    }

    // Method to get all data and return as List<LostBean>
    public List<LostBean> getAllData() {
        List<LostBean> lostList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                LostBean lostBean = new LostBean();
                lostBean.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                lostBean.setType(cursor.getString(cursor.getColumnIndex(COLUMN_TYPE)));
                lostBean.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                lostBean.setPhone(cursor.getString(cursor.getColumnIndex(COLUMN_PHONE)));
                lostBean.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
                lostBean.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DATE)));
                lostBean.setAddress(cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS)));

                lostList.add(lostBean);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lostList;
    }

    // Method to update a row
    public int updateData(int id, String type, String name, String phone, String description, String date, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TYPE, type);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_PHONE, phone);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_ADDRESS, address);
        return db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Method to delete a row
    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }
    public List<LostBean> getAllLocations() {
        List<LostBean> locationList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                String location = cursor.getString(cursor.getColumnIndex(COLUMN_ADDRESS));
                locationList.add(new LostBean(name,location));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return locationList;
    }
}
