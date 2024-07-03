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
        addFlight.setOnClickListener(v -> {
            startActivity(new Intent(FlightListManagementActivity.this, AddFlightActivity.class));
            finish();
        });
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
//        flightDAO.dropAndRecreateTable();
        flightList.addAll(flightDAO.getAllFlights());
        flightAdapter.notifyDataSetChanged();
        flightDAO.close();
    }
}
