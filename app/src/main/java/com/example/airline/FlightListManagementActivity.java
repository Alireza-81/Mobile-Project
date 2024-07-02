package com.example.airline;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightListManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFlights;
    private FlightManagementAdapter flightAdapter;
    private List<Flight> flightList;
    private FlightDAO flightDAO;
    private AirplaneDAO airplaneDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_management);

        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));

        flightList = new ArrayList<>();

        // Initialize DAOs
        flightDAO = FlightDAO.getInstance(this);
        airplaneDAO = AirplaneDAO.getInstance(this);
        flightDAO.open();
        airplaneDAO.open();

        // Drop and recreate the table, then insert data
        resetDatabaseAndInsertData();

        // Retrieve flights from the database and add them to the flightList
        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("MASHHAD", "TEHRAN"));
        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("TABRIZ", "TEHRAN"));
        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("AHVAZ", "TEHRAN"));

        // Set up the RecyclerView with the flight list
        flightAdapter = new FlightManagementAdapter(flightList);
        recyclerViewFlights.setAdapter(flightAdapter);

        // Close the database connections
        flightDAO.close();
        airplaneDAO.close();
    }

    private void resetDatabaseAndInsertData() {
        // Drop and recreate the flight table
        flightDAO.dropAndRecreateTable();

        // Insert airplane if not exists
        Airplane airplane = new Airplane("meow", "Boeing 747", 10, 10);
        if (airplaneDAO.getAirplaneByNameId("meow") == null) {
            airplaneDAO.insertAirplane(airplane);
        }

        // Insert flights into the database
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), new ArrayList<>(), new ArrayList<>(), 180, 500));
        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), new ArrayList<>(), new ArrayList<>(), 180, 500));
        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), new ArrayList<>(), new ArrayList<>(), 180, 500));

    }
}
