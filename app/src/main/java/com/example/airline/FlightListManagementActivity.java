package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

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
    private Button addFlight;

    @Override
    protected void onResume() {
        super.onResume();
        flightDAO.open();
        airplaneDAO.open();

        flightList.clear();

//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("MASHHAD", "TEHRAN"));
//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("TABRIZ", "TEHRAN"));
//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("AHVAZ", "TEHRAN"));
//


        flightList.addAll(flightDAO.getAllFlights());

//        flightAdapter.notifyDataSetChanged(); // Notify adapter of data change
        flightAdapter = new FlightManagementAdapter(flightList, this);
        recyclerViewFlights.setAdapter(flightAdapter);

        airplaneDAO.close();
        flightDAO.close();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_management);

        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));
        addFlight = findViewById(R.id.addFlightButton);
        addFlight.setOnClickListener(v -> {
            startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class));
        });

        // Initialize DAOs
        flightDAO = FlightDAO.getInstance(this);
        airplaneDAO = AirplaneDAO.getInstance(this);
        flightDAO.open();
        airplaneDAO.open();
//        resetDatabaseAndInsertData();

        // Set up the RecyclerView with an empty list initially
//        flightList = flightDAO.getAllFlights();

//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("MASHHAD", "TEHRAN"));
//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("TABRIZ", "TEHRAN"));
//        flightList.addAll(flightDAO.getFlightsByOriginAndDestination("AHVAZ", "TEHRAN"));
//

        flightList = new ArrayList<>();

        flightDAO.close();
        airplaneDAO.close();
    }


    private void resetDatabaseAndInsertData() {
        // Drop and recreate the flight table
        flightDAO.dropAndRecreateTable();
        airplaneDAO.dropAndRecreateTable();
        // Insert airplane if not exists
        Airplane airplane = new Airplane("meow3", "Boeing 748", 100, 10);
        airplaneDAO.insertAirplane(new Airplane("meow2", "Boeing 747", 100, 10));
        airplaneDAO.insertAirplane(airplane);

        List<String> staff = new ArrayList<>();
        staff.add("miow khan");
        staff.add("sobhan allah");
        // Insert flights into the database
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), staff, new ArrayList<>(), 100, 500));
        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), new ArrayList<>(), new ArrayList<>(), 100, 500));
        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane.getName(), new ArrayList<>(), new ArrayList<>(), 100, 500));

    }
}
