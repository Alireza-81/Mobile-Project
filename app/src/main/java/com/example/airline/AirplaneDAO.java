package com.example.airline;// AirplaneDAO.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {

    private SQLiteDatabase database;

    private AirplaneDatabaseHelper dbHelper;

    private static AirplaneDAO instance;

    public AirplaneDAO(Context context) {
        dbHelper = new AirplaneDatabaseHelper(context.getApplicationContext());
    }

    public static AirplaneDAO getInstance(Context context){
        if (instance == null){
            instance = new AirplaneDAO(context);
        }
        return instance;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertAirplane(Airplane airplane) {
        ContentValues values = new ContentValues();
        values.put(AirplaneDatabaseHelper.COLUMN_NAME_ID, airplane.getName());
        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());

        database.insert(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, values);
    }

    public Airplane getAirplaneByNameId(String nameId) {
        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES,
                null, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{nameId}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Airplane airplane = cursorToAirplane(cursor);
            cursor.close();
            return airplane;
        } else {
            return null;
        }
    }

    public List<Airplane> getAllAirplanes() {
        List<Airplane> airplanes = new ArrayList<>();
        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Airplane airplane = cursorToAirplane(cursor);
            airplanes.add(airplane);
            cursor.moveToNext();
        }
        cursor.close();
        return airplanes;
    }

    private Airplane cursorToAirplane(Cursor cursor) {
        return new Airplane(
                cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL)),
                cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY)),
                cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT))
        );
    }

    public void updateAirplane(Airplane airplane) {
        ContentValues values = new ContentValues();
        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());

        database.update(AirplaneDatabaseHelper.TABLE_AIRPLANES, values, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{airplane.getName()});
    }

    public void deleteAirplane(String nameId) {
        database.delete(AirplaneDatabaseHelper.TABLE_AIRPLANES, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{nameId});
    }
}
