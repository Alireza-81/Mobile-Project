package com.example.airline;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EditFlightActivity extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination;
    private EditText editTextDateTime, editTextPrice, editTextCapacity, editTextAirplaneName, editTextFlightNumber;
    private SearchView searchViewStaffToAdd, searchViewStaffToRemove, searchViewCustomersToAdd, searchViewCustomersToRemove;
    private Button buttonSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_flight);

        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        editTextDateTime = findViewById(R.id.editTextDateTime);
//        editTextPrice = findViewById(R.id.editTextPrice);
//        editTextCapacity = findViewById(R.id.editTextCapacity);
        editTextAirplaneName = findViewById(R.id.editTextAirplaneName);
        editTextFlightNumber = findViewById(R.id.editTextFlightNumber);
//        searchViewStaffToAdd = findViewById(R.id.searchViewStaffToAdd);
//        searchViewStaffToRemove = findViewById(R.id.searchViewStaffToRemove);
//        searchViewCustomersToAdd = findViewById(R.id.searchViewCustomersToAdd);
//        searchViewCustomersToRemove = findViewById(R.id.searchViewCustomersToRemove);
        buttonSaveChanges = findViewById(R.id.buttonSaveChanges);

        // Set up the spinners with CityEnum values
        ArrayAdapter<CityEnum> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, CityEnum.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrigin.setAdapter(adapter);
        spinnerDestination.setAdapter(adapter);

        // Handle the Save Changes button click
        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle saving changes here
                saveFlightDetails();
            }
        });
    }

    private void saveFlightDetails() {
        // Retrieve values from the input fields
        CityEnum origin = (CityEnum) spinnerOrigin.getSelectedItem();
        CityEnum destination = (CityEnum) spinnerDestination.getSelectedItem();
        String dateTimeStr = editTextDateTime.getText().toString();
        int price = Integer.parseInt(editTextPrice.getText().toString());
        int capacity = Integer.parseInt(editTextCapacity.getText().toString());
        String airplaneName = editTextAirplaneName.getText().toString();
        int flightNumber = Integer.parseInt(editTextFlightNumber.getText().toString());

        // Parse date and time
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

        // Add logic to update the flight details
    }
}
