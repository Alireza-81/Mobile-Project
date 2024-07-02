package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class AirplaneListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAirplanes;
    private AirplaneAdapter airplaneAdapter;
    private List<Airplane> airplaneList;
    private Button addAirplaneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.airplane_management);

        recyclerViewAirplanes = findViewById(R.id.recyclerViewAirplanes);
        addAirplaneButton = findViewById(R.id.addAirplaneButton);

        // Initialize airplane list
        airplaneList = new ArrayList<>();
        // Sample data
        airplaneList.add(new Airplane("A123", "Boeing 747", 416, 20));
        airplaneList.add(new Airplane("B456", "Airbus A380", 525, 25));
        // Add more sample data as needed

        airplaneAdapter = new AirplaneAdapter(airplaneList);
        recyclerViewAirplanes.setAdapter(airplaneAdapter);
        recyclerViewAirplanes.setLayoutManager(new LinearLayoutManager(this));

        addAirplaneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add airplane action
//                Intent intent = new Intent(AirplaneListActivity.this, AddAirplaneActivity.class);
//                startActivity(intent);
            }
        });
    }
}
