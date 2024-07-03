package com.example.airline;


// FlightDAO.java
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.airline.Flight;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {


    private SQLiteDatabase database;
    private FlightDatabaseHelper dbHelper;
    private Gson gson;
    private static FlightDAO instance;

    private FlightDAO(Context context) {
        dbHelper = new FlightDatabaseHelper(context.getApplicationContext());
        gson = new Gson();
    }

    public static synchronized FlightDAO getInstance(Context context) {
        if (instance == null) {
            instance = new FlightDAO(context);
        }
        return instance;
    }


    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long insertFlight(Flight flight) {
        ContentValues values = new ContentValues();
        values.put(FlightDatabaseHelper.COLUMN_ORIGIN, flight.getOrigin().toString());
        values.put(FlightDatabaseHelper.COLUMN_DESTINATION, flight.getDestination().toString());
        values.put(FlightDatabaseHelper.COLUMN_DATETIME, flight.getDateTime().toString());
        values.put(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID, flight.getAirplaneNameId());
        values.put(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY, flight.getRemainingCapacity());
        values.put(FlightDatabaseHelper.COLUMN_PRICE, flight.getPrice());
        values.put(FlightDatabaseHelper.COLUMN_STAFF_LIST, gson.toJson(flight.getStaffList()));
        values.put(FlightDatabaseHelper.COLUMN_CUSTOMER_LIST, gson.toJson(flight.getCustomerList()));

        return database.insert(FlightDatabaseHelper.TABLE_FLIGHTS, null, values);
    }

    public List<Flight> getFlightsByOrigin(String origin) {
        List<Flight> flights = new ArrayList<>();
        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS,
                null, FlightDatabaseHelper.COLUMN_ORIGIN + " = ?", new String[]{origin}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Flight flight = cursorToFlight(cursor);
            flights.add(flight);
            cursor.moveToNext();
        }
        cursor.close();
        return flights;
    }
    public int deleteFlightsByAirplaneName(String airplaneName) {
        return database.delete(FlightDatabaseHelper.TABLE_FLIGHTS,
                FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID + " = ?",
                new String[]{airplaneName});
    }
    private Flight cursorToFlight(Cursor cursor) {
        Type listType = new TypeToken<List<String>>() {}.getType();

        return new Flight(
                CityEnum.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_ORIGIN))),
                CityEnum.valueOf(cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DESTINATION))),
                LocalDateTime.parse(cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DATETIME))),
                cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID)),
                gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_STAFF_LIST)), listType),
                gson.fromJson(cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_CUSTOMER_LIST)), listType),
                cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY)),
                cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_PRICE))
        );
    }

    // 1. Decrease flight remaining capacity by value if possible
    public boolean decreaseRemainingCapacity(int flightId, int value) {
        Flight flight = getFlightById(flightId);
        if (flight != null && flight.getRemainingCapacity() >= value) {
            ContentValues values = new ContentValues();
            values.put(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY, flight.getRemainingCapacity() - value);
            int rowsAffected = database.update(FlightDatabaseHelper.TABLE_FLIGHTS, values, FlightDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(flightId)});
            return rowsAffected > 0;
        }
        return false;
    }

    // 2. Increase flight remaining capacity by value if not violating corresponding airplane capacity
    public boolean increaseRemainingCapacity(int flightId, int value, Context context) {
        Flight flight = getFlightById(flightId);
        if (flight != null) {
            AirplaneDAO airplaneDAO = AirplaneDAO.getInstance(context);
            Airplane airplane = airplaneDAO.getAirplaneByName(flight.getAirplaneNameId());
            if (airplane != null && flight.getRemainingCapacity() + value <= airplane.getCapacity()) {
                ContentValues values = new ContentValues();
                values.put(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY, flight.getRemainingCapacity() + value);
                int rowsAffected = database.update(FlightDatabaseHelper.TABLE_FLIGHTS, values, FlightDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(flightId)});
                return rowsAffected > 0;
            }
        }
        return false;
    }


    public List<Flight> getFlightsByOriginAndDestination(String origin, String destination) {
        List<Flight> flights = new ArrayList<>();
        String selection = FlightDatabaseHelper.COLUMN_ORIGIN + " = ? AND " + FlightDatabaseHelper.COLUMN_DESTINATION + " = ?";
        String[] selectionArgs = { origin, destination};

        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS, null, selection, selectionArgs, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_ID));
                        String originCity = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_ORIGIN));
                        String destinationCity = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DESTINATION));
                        String dateTimeString = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DATETIME));
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString);
                        String airplaneName = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID));
                        int remainingCapacity = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY));
                        int price = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_PRICE));

                        Flight flight = new Flight(CityEnum.valueOf(originCity.toUpperCase()), CityEnum.valueOf(destinationCity.toUpperCase()), dateTime, airplaneName, new ArrayList<>(), new ArrayList<>(), remainingCapacity, price);
                        flight.setId(id);
                        flights.add(flight);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }

        return flights;
    }


    // 4. Get flights by a dateTime
    public List<Flight> getFlightsByDateTime(LocalDateTime dateTime) {
        List<Flight> flights = new ArrayList<>();
        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS,
                null, FlightDatabaseHelper.COLUMN_DATETIME + " = ?", new String[]{dateTime.toString()}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Flight flight = cursorToFlight(cursor);
            flights.add(flight);
            cursor.moveToNext();
        }
        cursor.close();
        return flights;
    }

    // 5. Get flights having more than p remaining capacity
    public List<Flight> getFlightsByRemainingCapacity(int minRemainingCapacity) {
        List<Flight> flights = new ArrayList<>();
        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS,
                null, FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY + " > ?", new String[]{String.valueOf(minRemainingCapacity)}, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Flight flight = cursorToFlight(cursor);
            flights.add(flight);
            cursor.moveToNext();
        }
        cursor.close();
        return flights;
    }

    // Helper method to get a flight by ID
    public Flight getFlightById(int flightId) {
        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS,
                null, FlightDatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(flightId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            Flight flight = cursorToFlight(cursor);
            cursor.close();
            return flight;
        } else {
            return null;
        }
    }
    public void dropAndRecreateTable() {
        database.execSQL("DROP TABLE IF EXISTS " + FlightDatabaseHelper.TABLE_FLIGHTS);
        dbHelper.onCreate(database);
    }
    // Get all flights
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        Cursor cursor = database.query(FlightDatabaseHelper.TABLE_FLIGHTS, null, null, null, null, null, null);

        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    do {
                        String id = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_ID));
                        String origin = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_ORIGIN));
                        String destination = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DESTINATION));
                        String dateTime = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_DATETIME));
                        String airplaneName = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID));
                        int remainingCapacity = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY));
                        int price = cursor.getInt(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_PRICE));

                        LocalDateTime flightDateTime = LocalDateTime.parse(dateTime);
                        String staffListJson = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_STAFF_LIST));
                        String customerListJson = cursor.getString(cursor.getColumnIndexOrThrow(FlightDatabaseHelper.COLUMN_CUSTOMER_LIST));

                        List<String > staffList = convertJsonToStaffList(staffListJson);
                        List<String> customerList = convertJsonToCustomerList(customerListJson);

                        Flight flight = new Flight(CityEnum.valueOf(origin), CityEnum.valueOf(destination),
                                flightDateTime, airplaneName, staffList, customerList, remainingCapacity, price);
                        flight.setId(id);
                        flights.add(flight);
                    } while (cursor.moveToNext());
                }
            } finally {
                cursor.close();
            }
        }

        return flights;
    }

    private List<String> convertJsonToStaffList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    private List<String> convertJsonToCustomerList(String json) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<String>>(){}.getType();
        return gson.fromJson(json, listType);
    }
    public int deleteFlightById(int flightId) {
        return database.delete(FlightDatabaseHelper.TABLE_FLIGHTS,
                FlightDatabaseHelper.COLUMN_ID + " = ?",
                new String[]{String.valueOf(flightId)});
    }
    public int updateFlight(Flight flight) {
        ContentValues values = new ContentValues();
        values.put(FlightDatabaseHelper.COLUMN_ORIGIN, flight.getOrigin().toString());
        values.put(FlightDatabaseHelper.COLUMN_DESTINATION, flight.getDestination().toString());
        values.put(FlightDatabaseHelper.COLUMN_DATETIME, flight.getDateTime().toString());
        values.put(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID, flight.getAirplaneNameId());
        values.put(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY, flight.getRemainingCapacity());
        values.put(FlightDatabaseHelper.COLUMN_PRICE, flight.getPrice());
        values.put(FlightDatabaseHelper.COLUMN_STAFF_LIST, gson.toJson(flight.getStaffList()));
        values.put(FlightDatabaseHelper.COLUMN_CUSTOMER_LIST, gson.toJson(flight.getCustomerList()));

        return database.update(FlightDatabaseHelper.TABLE_FLIGHTS, values,
                FlightDatabaseHelper.COLUMN_ID + " = ?", new String[]{flight.getId()});
    }

    public int updateFlightWithId(Flight flight) {
        ContentValues values = new ContentValues();
        values.put(FlightDatabaseHelper.COLUMN_ID, flight.getId());
        values.put(FlightDatabaseHelper.COLUMN_ORIGIN, flight.getOrigin().toString());
        values.put(FlightDatabaseHelper.COLUMN_DESTINATION, flight.getDestination().toString());
        values.put(FlightDatabaseHelper.COLUMN_DATETIME, flight.getDateTime().toString());
        values.put(FlightDatabaseHelper.COLUMN_AIRPLANE_NAME_ID, flight.getAirplaneNameId());
        values.put(FlightDatabaseHelper.COLUMN_REMAINING_CAPACITY, flight.getRemainingCapacity());
        values.put(FlightDatabaseHelper.COLUMN_PRICE, flight.getPrice());
        values.put(FlightDatabaseHelper.COLUMN_STAFF_LIST, gson.toJson(flight.getStaffList()));
        values.put(FlightDatabaseHelper.COLUMN_CUSTOMER_LIST, gson.toJson(flight.getCustomerList()));

        return database.update(FlightDatabaseHelper.TABLE_FLIGHTS, values,
                FlightDatabaseHelper.COLUMN_ID + " = ?", new String[]{flight.getId()});
    }

}
