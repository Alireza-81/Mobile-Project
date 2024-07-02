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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flight_management);

        recyclerViewFlights = findViewById(R.id.recyclerViewFlights);
        recyclerViewFlights.setLayoutManager(new LinearLayoutManager(this));

        flightList = new ArrayList<>();
        Airplane airplane = new Airplane("meow", "Boeing 747", null, 10, 10);
        for (int i = 0; i < 20; i++) {flightList.add(new Flight(i, CityEnum.MASHHAD, CityEnum.TEHRAN, LocalDateTime.now(), airplane, new ArrayList<>(), new ArrayList<>(), 180, 500));
            flightList.add(new Flight(i + 1, CityEnum.TABRIZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane, new ArrayList<>(), new ArrayList<>(), 180, 500));
            flightList.add(new Flight(i + 2, CityEnum.AHVAZ, CityEnum.TEHRAN, LocalDateTime.now(), airplane, new ArrayList<>(), new ArrayList<>(), 180, 500));
        }

        flightAdapter = new FlightManagementAdapter(flightList);
        recyclerViewFlights.setAdapter(flightAdapter);
    }
}
