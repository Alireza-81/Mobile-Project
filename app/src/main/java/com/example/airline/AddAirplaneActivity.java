package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddAirplaneActivity extends AppCompatActivity {

    private EditText editTextNameId;
    private EditText editTextModel;
    private EditText editTextCapacity;
    private EditText editTextMaxLuggageWeight;
    private Button buttonAddAirplane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_airplane);

        editTextNameId = findViewById(R.id.editTextNameId);
        editTextModel = findViewById(R.id.editTextModel);
        editTextCapacity = findViewById(R.id.editTextCapacity);
        editTextMaxLuggageWeight = findViewById(R.id.editTextMaxLuggageWeight);
        buttonAddAirplane = findViewById(R.id.buttonAddAirplane);

        buttonAddAirplane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameId = editTextNameId.getText().toString().trim();
                String model = editTextModel.getText().toString().trim();
                int capacity = Integer.parseInt(editTextCapacity.getText().toString().trim());
                int maxLuggageWeight = Integer.parseInt(editTextMaxLuggageWeight.getText().toString().trim());

                if (!nameId.isEmpty() && !model.isEmpty() && capacity > 0 && maxLuggageWeight > 0) {
                    Airplane airplane = new Airplane(nameId, model, capacity, maxLuggageWeight);
                    AirplaneDAO airplaneDAO = AirplaneDAO.getInstance(AddAirplaneActivity.this);
                    airplaneDAO.insertAirplane(airplane);

                    Toast.makeText(AddAirplaneActivity.this, "Airplane added successfully", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(AddAirplaneActivity.this, AirplaneListActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(AddAirplaneActivity.this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
