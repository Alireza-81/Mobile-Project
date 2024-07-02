package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminMainMenuActivity extends AppCompatActivity {

    private Button buttonFlightMenu, buttonAirplaneMenu, buttonStaffMenu, buttonCustomerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_admin_menu);

        buttonFlightMenu = findViewById(R.id.buttonFlightMenu);
        buttonAirplaneMenu = findViewById(R.id.buttonAirplaneMenu);
        buttonStaffMenu = findViewById(R.id.buttonStaffMenu);
        buttonCustomerMenu = findViewById(R.id.buttonCustomerMenu);

        buttonFlightMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainMenuActivity.this, FlightListManagementActivity.class);
                startActivity(intent);
            }
        });

        buttonAirplaneMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your intent here for Airplane Menu
                // Example: startActivity(new Intent(MainMenuActivity.this, AirplaneMenuActivity.class));
            }
        });

        buttonStaffMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your intent here for Staff Menu
                // Example: startActivity(new Intent(MainMenuActivity.this, StaffMenuActivity.class));
            }
        });

        buttonCustomerMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your intent here for Customer Menu
                // Example: startActivity(new Intent(MainMenuActivity.this, CustomerMenuActivity.class));
            }
        });
    }
}
