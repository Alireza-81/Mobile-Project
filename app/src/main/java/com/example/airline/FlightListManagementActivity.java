package com.example.airline;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class FlightListManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFlights;
    private FlightManagementAdapter flightAdapter;
    private List<Flight> flightList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_management);

        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));

        // Initialize flight data (this would typically come from a database or API)
        flightList = new ArrayList<>();
        flightList.add(new Flight("AA123", 180, "Non-stop, Economy Class"));
        flightList.add(new Flight("BA456", 200, "One-stop, Business Class"));
        flightList.add(new Flight("CA789", 150, "Non-stop, First Class"));

        flightAdapter = new FlightAdapter(flightList);
        recyclerViewFlights.setAdapter(flightAdapter);
    }
}
