package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddFlightActivity extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination, spinnerAirplane;
    private EditText editTextDate, editTextTime, editTextPrice;
    private Button buttonAddFlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);

        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        spinnerAirplane = findViewById(R.id.spinnerAirplane);
        editTextDate = findViewById(R.id.editTextDate);
        editTextTime = findViewById(R.id.editTextTime);
        editTextPrice = findViewById(R.id.editTextPrice);
        buttonAddFlight = findViewById(R.id.buttonAddFlight);

        // Populate spinners with city values
        List<String> cityValues = CityEnum.getValues();
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityValues);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(cityAdapter);
        spinnerDestination.setAdapter(cityAdapter);

        // Populate airplane spinner with airplane names
        AirplaneDAO airplaneDAO = AirplaneDAO.getInstance(this);
        airplaneDAO.open();

        List<Airplane> airplanes = airplaneDAO.getAllAirplanes();
        List<String> airplaneNames = new ArrayList<>();
        for (Airplane airplane : airplanes) {
            airplaneNames.add(airplane.getName());
        }
        ArrayAdapter<String> airplaneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, airplaneNames);
        airplaneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAirplane.setAdapter(airplaneAdapter);

        buttonAddFlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFlight();
            }
        });


        airplaneDAO.close();
    }

    private void addFlight() {
        AirplaneDAO.getInstance(this).open();
        FlightDAO.getInstance(this).open();
        String origin = spinnerOrigin.getSelectedItem().toString();
        String destination = spinnerDestination.getSelectedItem().toString();
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();
        String airplaneName = spinnerAirplane.getSelectedItem().toString();
        String priceStr = editTextPrice.getText().toString();

        if (origin.isEmpty() || destination.isEmpty() || date.isEmpty() || time.isEmpty() || airplaneName.isEmpty() || priceStr.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceStr);

        AirplaneDAO airplaneDAO = AirplaneDAO.getInstance(this);
        Airplane airplane = airplaneDAO.getAirplaneByName(airplaneName);

        if (airplane == null) {
            Toast.makeText(this, "Airplane not found", Toast.LENGTH_SHORT).show();
            return;
        }

        LocalDate flightDate = LocalDate.parse(date);
        LocalTime flightTime = LocalTime.parse(time);
        LocalDateTime flightDateTime = LocalDateTime.of(flightDate, flightTime);

        Flight flight = new Flight(CityEnum.valueOf(origin.toUpperCase()), CityEnum.valueOf(destination.toUpperCase()),
                flightDateTime, airplane.getName(), new ArrayList<>(), new ArrayList<>(), airplane.getCapacity(), (int)price);

        FlightDAO flightDAO = FlightDAO.getInstance(this);
        long res= flightDAO.insertFlight(flight);
        Toast.makeText(getApplicationContext(), String.valueOf(res), Toast.LENGTH_SHORT).show();

        Toast.makeText(this, "Flight added successfully", Toast.LENGTH_SHORT).show();
        AirplaneDAO.getInstance(this).close();
        FlightDAO.getInstance(this).close();
        startActivity(new Intent(AddFlightActivity.this, FlightListManagementActivity.class));
        finish(); // Close the activity
    }
}
