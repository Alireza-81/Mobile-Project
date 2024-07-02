package com.example.airline;// AirplaneDatabaseHelper.java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AirplaneDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "airplanes.db";
    private static final int DATABASE_VERSION = 2;

    // Table name
    public static final String TABLE_AIRPLANES = "Airplanes";

    // Table columns
    public static final String COLUMN_NAME_ID = "name_id";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_CAPACITY = "capacity";
    public static final String COLUMN_MAX_LUGGAGE_WEIGHT = "maxLuggageWeightPerPerson";

    // Create table SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_AIRPLANES + " (" +
                    COLUMN_NAME_ID + " TEXT PRIMARY KEY, " +
                    COLUMN_MODEL + " TEXT, " +
                    COLUMN_CAPACITY + " INTEGER, " +
                    COLUMN_MAX_LUGGAGE_WEIGHT + " INTEGER" +
                    ");";

    public AirplaneDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AIRPLANES);
        onCreate(db);
    }
}
