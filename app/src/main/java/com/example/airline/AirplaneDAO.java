package com.example.airline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class AirplaneDAO {

    private static AirplaneDAO instance;
    private SQLiteDatabase database;
    private AirplaneDatabaseHelper dbHelper;

    private AirplaneDAO(Context context) {
        dbHelper = new AirplaneDatabaseHelper(context.getApplicationContext());
        database = dbHelper.getWritableDatabase();
    }

    public static synchronized AirplaneDAO getInstance(Context context) {
        if (instance == null) {
            instance = new AirplaneDAO(context.getApplicationContext());
        }
        return instance;

    }

    // Insert Airplane
    public long insertAirplane(Airplane airplane) {
        ContentValues values = new ContentValues();
        values.put(AirplaneDatabaseHelper.COLUMN_NAME_ID, airplane.getName());
        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());
        return database.insert(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, values);
    }

    public List<Airplane> getAllAirplanes() {
        List<Airplane> airplanes = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    String name_id = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID));
                    String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
                    int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
                    int maxLuggageWeight = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
                    airplanes.add(new Airplane(name_id, model, capacity, maxLuggageWeight));
                    Log.d("Database", "Added airplane: " + name_id + ", " + model);
                } while (cursor.moveToNext());
            } else {
                Log.d("Database", "Cursor is empty or failed to move to first");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Database", "Exception occurred: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return airplanes;
    }

    // Get airplane by name
    public Airplane getAirplaneByName(String name) {
        Airplane airplane = null;
        Cursor cursor = null;
        try {
            String selection = AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?";
            String[] selectionArgs = { name };

            cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String name_id = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID));
                String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
                int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
                int maxLuggageWeight = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
                airplane = new Airplane(name_id, model, capacity, maxLuggageWeight);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return airplane;
    }

    // Delete airplane by name_id
    public boolean deleteAirplaneByName(String nameId) {
        String whereClause = AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?";
        String[] whereArgs = { nameId };

        int rowsDeleted = database.delete(AirplaneDatabaseHelper.TABLE_AIRPLANES, whereClause, whereArgs);
        return rowsDeleted > 0;
    }

    // Close database
    public void close() {
        if (dbHelper != null) {
            dbHelper.close();
        }
    }

    // Open database
    public void open() throws SQLException {
        if (dbHelper != null) {
            database = dbHelper.getWritableDatabase();
        }
    }

    // Drop and recreate table
    public void dropAndRecreateTable() {
        if (dbHelper != null) {
            dbHelper.dropAndRecreateTable(database);
        }
    }

    public int deleteAirplaneByName(String name, Context context) {
        FlightDAO flightDAO = FlightDAO.getInstance(context);
        flightDAO.open();
        int flightsDeleted = flightDAO.deleteFlightsByAirplaneName(name);
        flightDAO.close();

        return database.delete(AirplaneDatabaseHelper.TABLE_AIRPLANES,
                AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?",
                new String[]{name});
    }
}
