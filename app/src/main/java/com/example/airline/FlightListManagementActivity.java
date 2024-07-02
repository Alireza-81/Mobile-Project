package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        flightList.addAll(flightDAO.getAllFlights());

        if (flightAdapter == null) {
            flightAdapter = new FlightManagementAdapter(flightList, this);
            recyclerViewFlights.setAdapter(flightAdapter);
        } else {
            flightAdapter.notifyDataSetChanged();
        }

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
        addFlight.setOnClickListener(v -> startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class)));

        // Initialize DAOs
        flightDAO = FlightDAO.getInstance(this);
        airplaneDAO = AirplaneDAO.getInstance(this);
        flightList = new ArrayList<>();
    }
}
