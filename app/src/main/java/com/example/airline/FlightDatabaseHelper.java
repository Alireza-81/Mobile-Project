package com.example.airline;// FlightDatabaseHelper.java
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FlightDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "flights.db";
    private static final int DATABASE_VERSION = 3;

    // Table name
    public static final String TABLE_FLIGHTS = "Flights";

    // Table columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ORIGIN = "origin";
    public static final String COLUMN_DESTINATION = "destination";
    public static final String COLUMN_DATETIME = "dateTime";
    public static final String COLUMN_AIRPLANE_NAME_ID = "airplaneNameId";
    public static final String COLUMN_REMAINING_CAPACITY = "remainingCapacity";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_STAFF_LIST = "staffList";
    public static final String COLUMN_CUSTOMER_LIST = "customerList";

    // Create table SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_FLIGHTS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ORIGIN + " TEXT, " +
                    COLUMN_DESTINATION + " TEXT, " +
                    COLUMN_DATETIME + " TEXT, " +
                    COLUMN_AIRPLANE_NAME_ID + " TEXT, " +
                    COLUMN_REMAINING_CAPACITY + " INTEGER, " +
                    COLUMN_PRICE + " INTEGER, " +
                    COLUMN_STAFF_LIST + " TEXT, " +
                    COLUMN_CUSTOMER_LIST + " TEXT" +
                    ");";

    public FlightDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_FLIGHTS + " ADD COLUMN " + COLUMN_STAFF_LIST + " TEXT");
            db.execSQL("ALTER TABLE " + TABLE_FLIGHTS + " ADD COLUMN " + COLUMN_CUSTOMER_LIST + " TEXT");
        }
        if (oldVersion < 3) {
            db.execSQL("ALTER TABLE " + TABLE_FLIGHTS + " ADD COLUMN " + COLUMN_AIRPLANE_NAME_ID + " TEXT");
        }
    }
}
