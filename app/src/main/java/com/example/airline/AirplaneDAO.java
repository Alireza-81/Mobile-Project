package com.example.airline;// AirplaneDAO.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

//public class AirplaneDAO {
//
//    private SQLiteDatabase database;
//
//    private AirplaneDatabaseHelper dbHelper;
//
//    private static AirplaneDAO instance;
//
//    public AirplaneDAO(Context context) {
//        dbHelper = new AirplaneDatabaseHelper(context.getApplicationContext());
//    }
//
//    public static AirplaneDAO getInstance(Context context){
//        if (instance == null){
//            instance = new AirplaneDAO(context);
//        }
//        return instance;
//    }
//
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public void close() {
//        dbHelper.close();
//    }
//
//    public OperationCode insertAirplane(Airplane airplane) {
//        ContentValues values = new ContentValues();
//        values.put(AirplaneDatabaseHelper.COLUMN_NAME_ID, airplane.getName());
//        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
//        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
//        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());
//        try {
//            long result = database.insertOrThrow(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, values);
//            if (result == -1) {
//                // Handle error if the insert failed
//                return OperationCode.FAILED_INSERTION;
//            }else{
//                return OperationCode.SUCCESS;
//            }
//        } catch (SQLiteConstraintException e) {
//            // Handle unique constraint violation
//            return OperationCode.AIRPLANE_NAME_REPEATED;
//        }
//    }
//
//    public Airplane getAirplaneByNameId(String nameId) {
//        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES,
//                null, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{nameId}, null, null, null);
//
//        if (cursor != null && cursor.moveToFirst()) {
//            Airplane airplane = cursorToAirplane(cursor);
//            cursor.close();
//            return airplane;
//        } else {
//            return null;
//        }
//    }
//
//    public List<Airplane> getAllAirplanes() {
//        List<Airplane> airplanes = new ArrayList<>();
//        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, null, null, null, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Airplane airplane = cursorToAirplane(cursor);
//            airplanes.add(airplane);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return airplanes;
//    }
//
//    private Airplane cursorToAirplane(Cursor cursor) {
//        return new Airplane(
//                cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID)),
//                cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL)),
//                cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY)),
//                cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT))
//        );
//    }
//
//    public void updateAirplane(Airplane airplane) {
//        ContentValues values = new ContentValues();
//        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
//        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
//        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());
//
//        database.update(AirplaneDatabaseHelper.TABLE_AIRPLANES, values, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{airplane.getName()});
//    }
//
//    public void deleteAirplane(String nameId) {
//        database.delete(AirplaneDatabaseHelper.TABLE_AIRPLANES, AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?", new String[]{nameId});
//    }
//
//
//    public Airplane getAirplaneByName(String name) {
//        Airplane airplane = null;
//        String query = "SELECT * FROM " + AirplaneDatabaseHelper.TABLE_AIRPLANES + " WHERE " + AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?";
//        Cursor cursor = database.rawQuery(query, new String[]{name});
//
//        if (cursor.moveToFirst()) {
//            String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
//            int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
//            int maxLuggageWeightPerPerson = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
//            airplane = new Airplane(name, model, capacity, maxLuggageWeightPerPerson);
//        }
//
//        cursor.close();
//        return airplane;
//    }
//    public void dropAndRecreateTable() {
//        dbHelper.dropAndRecreateTable(database);
//    }
//}


//public class AirplaneDAO {
//
//    private static AirplaneDAO instance;
//    private SQLiteDatabase database;
//    private AirplaneDatabaseHelper dbHelper;
//
//    private AirplaneDAO(Context context) {
//        dbHelper = new AirplaneDatabaseHelper(context);
//        database = dbHelper.getWritableDatabase();
//    }
//
//    public static synchronized AirplaneDAO getInstance(Context context) {
//        if (instance == null) {
//            instance = new AirplaneDAO(context.getApplicationContext());
//        }
//        return instance;
//    }
//
//    // Insert Airplane
//    public void insertAirplane(Airplane airplane) {
//        ContentValues values = new ContentValues();
//        values.put(AirplaneDatabaseHelper.COLUMN_NAME_ID, airplane.getName());
//        values.put(AirplaneDatabaseHelper.COLUMN_MODEL, airplane.getModel());
//        values.put(AirplaneDatabaseHelper.COLUMN_CAPACITY, airplane.getCapacity());
//        values.put(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT, airplane.getMaxLuggageWeightPerPerson());
//
//        database.insert(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, values);
//    }
//
//    // Get all airplanes
//    public List<Airplane> getAllAirplanes() {
//        List<Airplane> airplanes = new ArrayList<>();
//        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, null, null, null, null, null);
//
//        if (cursor != null) {
//            try {
//                if (cursor.moveToFirst()) {
//                    do {
//                        String name_id = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID));
//                        String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
//                        int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
//                        int maxLuggageWeight = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
//                        airplanes.add(new Airplane(name_id, model, capacity, maxLuggageWeight));
//                    } while (cursor.moveToNext());
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//
//        return airplanes;
//    }
//
//    // Get airplane by name
//    public Airplane getAirplaneByName(String name) {
//        Airplane airplane = null;
//        String selection = AirplaneDatabaseHelper.COLUMN_NAME_ID + " = ?";
//        String[] selectionArgs = { name };
//
//        Cursor cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, selection, selectionArgs, null, null, null);
//
//        if (cursor != null) {
//            try {
//                if (cursor.moveToFirst()) {
//                    String name_id = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID));
//                    String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
//                    int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
//                    int maxLuggageWeight = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
//                    airplane = new Airplane(name_id, model, capacity, maxLuggageWeight);
//                }
//            } finally {
//                cursor.close();
//            }
//        }
//
//        return airplane;
//    }
//    public void close() {
//        dbHelper.close();
//    }
//
//    public void open() throws SQLException {
//        database = dbHelper.getWritableDatabase();
//    }
//    // Drop and recreate table
//    public void dropAndRecreateTable() {
//        dbHelper.dropAndRecreateTable(database);
//    }
//}


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

    // Get all airplanes
//    public List<Airplane> getAllAirplanes() {
//        List<Airplane> airplanes = new ArrayList<>();
//        Cursor cursor = null;
//        try {
//            cursor = database.query(AirplaneDatabaseHelper.TABLE_AIRPLANES, null, null, null, null, null, null);
//            if (cursor != null && cursor.moveToFirst()) {
//                do {
//                    String name_id = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_NAME_ID));
//                    String model = cursor.getString(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MODEL));
//                    int capacity = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_CAPACITY));
//                    int maxLuggageWeight = cursor.getInt(cursor.getColumnIndexOrThrow(AirplaneDatabaseHelper.COLUMN_MAX_LUGGAGE_WEIGHT));
//                    airplanes.add(new Airplane(name_id, model, capacity, maxLuggageWeight));
//                } while (cursor.moveToNext());
//            }
//        } catch (Exception e) {
//            // Handle the exception as needed
//            e.printStackTrace();
//        } finally {
//            if (cursor != null) {
//                cursor.close();
//            }
//        }
//        return airplanes;
//    }
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
            // Handle the exception as needed
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
            // Handle the exception as needed
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return airplane;
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
}
