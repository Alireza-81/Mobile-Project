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
                Intent intent = new Intent(AdminMainMenuActivity.this, AirplaneListActivity.class);
                startActivity(intent);
            }
        });

        buttonStaffMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMainMenuActivity.this, StaffManagementActivity.class);
                startActivity(intent);
            }
        });


    }
}
