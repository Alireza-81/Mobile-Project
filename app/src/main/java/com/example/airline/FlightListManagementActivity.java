package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

//public class FlightListManagementActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerViewFlights;
//    private FlightManagementAdapter flightAdapter;
//    private List<Flight> flightList;
//    private FlightDAO flightDAO;
//    public static final int REQUEST_CODE_EDIT_FLIGHT = 1;
//
//    private AirplaneDAO airplaneDAO;
//    private Button addFlight;
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        flightDAO.open();
//        airplaneDAO.open();
//
//        flightList.clear();
//        flightList.addAll(flightDAO.getAllFlights());
//
//        if (flightAdapter == null) {
//            flightAdapter = new FlightManagementAdapter(flightList, this);
//            recyclerViewFlights.setAdapter(flightAdapter);
//        } else {
//            flightAdapter.notifyDataSetChanged();
//        }
//
//        airplaneDAO.close();
//        flightDAO.close();
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.flight_management);
//
//        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
//        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));
//
//        addFlight = findViewById(R.id.addFlightButton);
//        addFlight.setOnClickListener(v -> startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class)));
//
//        // Initialize DAOs
//        flightDAO = FlightDAO.getInstance(this);
//        airplaneDAO = AirplaneDAO.getInstance(this);
//        flightList = new ArrayList<>();
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_EDIT_FLIGHT && resultCode == RESULT_OK) {
//            // Refresh the flight list
//            FlightDAO flightDAO = FlightDAO.getInstance(this);
//            flightDAO.open();
//            List<Flight> updatedFlights = flightDAO.getAllFlights();
//            flightDAO.close();
//
//            // Update the adapter's data and notify changes
//            flightAdapter.setFlightList(updatedFlights);
//            flightAdapter.notifyDataSetChanged();
//        }
//    }
//
//}

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
//
//public class FlightListManagementActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerViewFlights;
//    private FlightManagementAdapter flightAdapter;
//    private List<Flight> flightList;
//    private FlightDAO flightDAO;
//    public static final int REQUEST_CODE_EDIT_FLIGHT = 1;
//
//    private AirplaneDAO airplaneDAO;
//    private Button addFlight;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.flight_management);
//
//        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
//        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));
//
//        addFlight = findViewById(R.id.addFlightButton);
//        addFlight.setOnClickListener(v -> startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class)));
//
//        // Initialize DAOs
//        flightDAO = FlightDAO.getInstance(this);
//        airplaneDAO = AirplaneDAO.getInstance(this);
//        flightList = new ArrayList<>();
//
//        loadFlightData();
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadFlightData();
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE_EDIT_FLIGHT && resultCode == RESULT_OK) {
//            loadFlightData();
//        }
//    }
//
//    private void loadFlightData() {
//        flightDAO.open();
//        airplaneDAO.open();
//
//        flightList.clear();
//        flightList.addAll(flightDAO.getAllFlights());
//
//        if (flightAdapter == null) {
//            flightAdapter = new FlightManagementAdapter(flightList, this);
//            recyclerViewFlights.setAdapter(flightAdapter);
//        } else {
//            flightAdapter.setFlightList(flightList);
//            flightAdapter.notifyDataSetChanged();
//        }
//
//        airplaneDAO.close();
//        flightDAO.close();
//    }
//}
public class FlightListManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFlights;
    private FlightManagementAdapter flightAdapter;
    private List<Flight> flightList;
    private FlightDAO flightDAO;
    private AirplaneDAO airplaneDAO;
    public static final int REQUEST_CODE_EDIT_FLIGHT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_management);

        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));

        // Initialize DAOs
        flightDAO = FlightDAO.getInstance(this);
        airplaneDAO = AirplaneDAO.getInstance(this);
        flightList = new ArrayList<>();
        flightAdapter = new FlightManagementAdapter(flightList, this);
        recyclerViewFlights.setAdapter(flightAdapter);

        // Load flight data
        loadFlightData();

        Button addFlight = findViewById(R.id.addFlightButton);
        addFlight.setOnClickListener(v -> startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFlightData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_EDIT_FLIGHT && resultCode == RESULT_OK) {
            // Refresh the flight list
            loadFlightData();
        }
    }

    private void loadFlightData() {
        flightDAO.open();
        flightList.clear();
        flightList.addAll(flightDAO.getAllFlights());
        flightAdapter.notifyDataSetChanged();
        flightDAO.close();
    }
}
