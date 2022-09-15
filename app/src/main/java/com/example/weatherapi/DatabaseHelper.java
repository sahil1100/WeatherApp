package com.example.weatherapi;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyWeather";
    public static final String DATA_TABLE_NAME = "WeatherTable";
    public static final String COL_1 = "id";
    public static final String COL_2 = "place";
    public static final String COL_3 = "tempp";
    public static final String COL_4 = "max_temp";
    public static final String COL_5 = "min_temp";
    public static final String COL_6 = "feels_like";
    public static final String COL_7 = "latitude";
    public static final String COL_8 = "longitude";
    public static final String COL_9 = "humidity";
    public static final String COL_10 = "sunrise";
    public static final String COL_11 = "sunset";
    public static final String COL_12 = "pressure";
    public static final String COL_13 = "wind_speed";

    public DatabaseHelper(Context context) {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table "+ DATA_TABLE_NAME +"(id integer primary key AutoIncrement, place text, tempp text, max_temp text, min_temp text, feels_like text, latitude text, longitude text, humidity text, sunrise text, sunset text, pressure text, wind_speed text)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATA_TABLE_NAME);
        onCreate(db);
    }
    public boolean insert(String s, double s1, double s2, double s3, double s4, double s5, double s6, int s7, int s8, int s9, int s10, double s11) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, s);
        contentValues.put(COL_3, s1);
        contentValues.put(COL_4, s2);
        contentValues.put(COL_5, s3);
        contentValues.put(COL_6, s4);
        contentValues.put(COL_7, s5);
        contentValues.put(COL_8, s6);
        contentValues.put(COL_9, s7);
        contentValues.put(COL_10, s8);
        contentValues.put(COL_11, s9);
        contentValues.put(COL_12, s10);
        contentValues.put(COL_13, s11);
        db.insert(DATA_TABLE_NAME, null, contentValues);
        return true;
    }

    public Cursor getData(String placenm){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from "+DATA_TABLE_NAME +" where "+ COL_2+ " = '" + placenm + "'",null);
        return res;
    }
}
