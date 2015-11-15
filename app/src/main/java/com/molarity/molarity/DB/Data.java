package com.molarity.molarity.DB;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.molarity.molarity.Config;

import java.util.ArrayList;

public class Data {

    public String category;
    public String code;
    public String description;
    public String weight;

    public static String TABLE_NAME = "FormulaWeight";

    public Data(String table_name, String category, String code, String description, String weight) {
//        this.TABLE_NAME = table_name;
        this.category = category;
        this.code = code;
        this.description = description;
        this.weight = weight;
    }

    public static void createTable(SQLiteDatabase db) {
        String sqlCreateTable = "CREATE TABLE " + TABLE_NAME + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Config.category + " TEXT NOT NULL, " +
                Config.code + " TEXT NOT NULL, " +
                Config.description + " TEXT NOT NULL, " +
                Config.weight + " TEXT NOT NULL" + ")";
        db.execSQL(sqlCreateTable);
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public static void addDatas(ArrayList<Data> arrDatas) {
        SQLiteDatabase db = App.getDBHelper().getWritableDatabase();

        for (Data data: arrDatas) {
            ContentValues values = new ContentValues();
            values.put(Config.category, data.category);
            values.put(Config.code, data.code);
            values.put(Config.description, data.description);
            values.put(Config.weight, data.weight);

            db.insert(TABLE_NAME, null, values);
        }

        db.close();
    }

    public static boolean hasValue(Data data) {
        boolean isDuplicated = false;

        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = App.getDBHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String category = cursor.getString(1);
                String code = cursor.getString(2);
                String description = cursor.getString(3);
                String weight = cursor.getString(4);

                if (category.equals(data.category) && code.equals(data.code) && description.equals(data.description) && weight.equals(data.weight)) {
                    isDuplicated = true;
                }

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return isDuplicated;
    }

    public static void deleteWeight( String category, String code, String description ) {
        SQLiteDatabase db = App.getDBHelper().getWritableDatabase();
        db.delete(TABLE_NAME, Config.category + " = ? AND " + Config.code + " = ? AND " + Config.description + " = ?", new String[]{category, code, description});
        db.close();
    }

    public static ArrayList<Data> getAllRecord( String category ) {
        ArrayList<Data> arrRecord = new ArrayList<Data>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME + " WHERE " + Config.category + " = '" + category + "'";

        SQLiteDatabase db = App.getDBHelper().getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String _category = cursor.getString(1);
                String code = cursor.getString(2);
                String description = cursor.getString(3);
                String weight = cursor.getString(4);

                Data record = new Data(TABLE_NAME, _category, code, description, weight);

                arrRecord.add(record);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return arrRecord;
    }

    public static int updateWeight( String category, String code, String description, String newDescription ) {
        SQLiteDatabase db = App.getDBHelper().getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(Config.description, newDescription);

        int result = db.update(TABLE_NAME, cv, Config.category + " = ? AND " + Config.code + " = ? AND " + Config.description + " = ?", new String[]{category, code, description});

        db.close();

        return result;
    }
}