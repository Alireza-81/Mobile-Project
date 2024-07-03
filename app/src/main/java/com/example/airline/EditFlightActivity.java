package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class EditFlightActivity extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination;
    private EditText editTextDateTime, editTextAirplaneName;

    private Button buttonSaveChanges;
    private TextView shownPrice;
    private SeekBar chosenPrice;
    private TextView textViewCapacityValue;
    private Button buttonDecreaseCapacity, buttonIncreaseCapacity;

    public int flightID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_flight);
        flightID =  getIntent().getIntExtra("flightID", -1);


        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        editTextDateTime = findViewById(R.id.editTextDateTime);
        editTextAirplaneName = findViewById(R.id.editTextAirplaneName);

        shownPrice = findViewById(R.id.textViewPriceValue);
        chosenPrice = findViewById(R.id.seekBarPrice);


//        Toast.makeText(this, String.valueOf(flightID), Toast.LENGTH_SHORT).show();

        chosenPrice.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                shownPrice.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        textViewCapacityValue = findViewById(R.id.textViewCapacityValue);
        buttonDecreaseCapacity = findViewById(R.id.buttonDecreaseCapacity);
        buttonIncreaseCapacity = findViewById(R.id.buttonIncreaseCapacity);

        buttonDecreaseCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(textViewCapacityValue.getText().toString());
                if (currentValue > 0) {
                    textViewCapacityValue.setText(String.valueOf(currentValue - 1));
                }
            }
        });

        buttonIncreaseCapacity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentValue = Integer.parseInt(textViewCapacityValue.getText().toString());
                textViewCapacityValue.setText(String.valueOf(currentValue + 1));
            }
        });

        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFlightChanges();
            }
        });

        // Initialize spinners with data (this would normally be done with real data)
        ArrayAdapter<CityEnum> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CityEnum.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        // Load existing flight data
        loadFlightData();
    }

    private void loadFlightData() {
        FlightDAO flightDAO = FlightDAO.getInstance(this);
        flightDAO.open();
//        Toast.makeText(this, String.valueOf(flightID), Toast.LENGTH_SHORT).show();
        Flight flight = flightDAO.getFlightById(flightID);
        flightDAO.close();

        if (flight != null) {
            // Populate UI with flight data
            spinnerOrigin.setSelection(flight.getOrigin().ordinal());  // Set the correct item in the spinner
            spinnerDestination.setSelection(flight.getDestination().ordinal());  // Set the correct item in the spinner
            editTextDateTime.setText(flight.getDateTime().toString());
            editTextAirplaneName.setText(flight.getAirplaneNameId());
            shownPrice.setText(String.valueOf(flight.getPrice()));
            chosenPrice.setProgress(flight.getPrice());
            textViewCapacityValue.setText(String.valueOf(flight.getRemainingCapacity()));
        } else {
            Toast.makeText(this, "Flight not found", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveFlightChanges() {
        // Validate and retrieve updated flight data
        String origin = spinnerOrigin.getSelectedItem().toString();
        String destination = spinnerDestination.getSelectedItem().toString();
        String dateTime = editTextDateTime.getText().toString();
        String airplaneName = editTextAirplaneName.getText().toString();
        int price = chosenPrice.getProgress();
        int capacity = Integer.parseInt(textViewCapacityValue.getText().toString());

        // Convert data to Flight object
        Flight updatedFlight = new Flight(
                CityEnum.valueOf(origin),
                CityEnum.valueOf(destination),
                LocalDateTime.parse(dateTime),
                airplaneName,
                new ArrayList<>(),  // Assume staff and customer lists are updated separately
                new ArrayList<>(),
                capacity,
                price
        );
        updatedFlight.setId(String.valueOf(flightID));

        // Save changes to database
        FlightDAO flightDAO = FlightDAO.getInstance(this);
        flightDAO.open();
        flightDAO.updateFlightWithId(updatedFlight);
        flightDAO.close();

        // Notify user of success
        Toast.makeText(this, "Flight details updated successfully", Toast.LENGTH_SHORT).show();

        // Set result and finish the activity
        Intent resultIntent = new Intent();
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}

