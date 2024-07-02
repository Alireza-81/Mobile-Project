package com.example.airline;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDateTime;

public class EditFlightActivity extends AppCompatActivity {

    private Spinner spinnerOrigin, spinnerDestination;
    private EditText editTextDateTime, editTextAirplaneName, editTextFlightNumber;
    private EditText StaffToAddEditText, StaffToRemoveEditText, CustomerToAddEditText, CustomerToRemoveEditText;
    private Button buttonSaveChanges;
    private TextView shownPrice;
    private SeekBar chosenPrice;
    private TextView textViewCapacityValue;
    private Button buttonDecreaseCapacity, buttonIncreaseCapacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_flight);

        spinnerOrigin = findViewById(R.id.spinnerOrigin);
        spinnerDestination = findViewById(R.id.spinnerDestination);
        editTextDateTime = findViewById(R.id.editTextDateTime);
        editTextAirplaneName = findViewById(R.id.editTextAirplaneName);
        editTextFlightNumber = findViewById(R.id.editTextFlightNumber);
        StaffToAddEditText = findViewById(R.id.editTextStaffToAdd);
        StaffToRemoveEditText = findViewById(R.id.editTextStaffToRemove);
        CustomerToAddEditText = findViewById(R.id.editTextCustomersToAdd);
        CustomerToRemoveEditText = findViewById(R.id.editTextCustomersToRemove);

        shownPrice = findViewById(R.id.textViewPriceValue);
        chosenPrice = findViewById(R.id.seekBarPrice);
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

        // Load existing flight data (this would normally be done with real data)
        loadFlightData();
    }

    private void loadFlightData() {
        // Example data loading - in a real app, this would be dynamic data loading
        spinnerOrigin.setSelection(0); // Select the first item in the spinner
        spinnerDestination.setSelection(1); // Select the second item in the spinner
        editTextDateTime.setText(LocalDateTime.now().toString());
        editTextAirplaneName.setText("Boeing 737");
        editTextFlightNumber.setText("1234");
        shownPrice.setText("5000");
        chosenPrice.setProgress(5000);
        textViewCapacityValue.setText("150");
    }

    private void saveFlightChanges() {
        // Handle the saving of flight changes
        // This would typically involve validation and saving to a database or API
    }
}
