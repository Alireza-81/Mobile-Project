package com.example.airline;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class StaffManagementActivity extends AppCompatActivity {

    private RecyclerView recyclerViewStaff;
    private StaffAdapter staffAdapter;
    private List<Staff> staffList;
    private Button buttonAddStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.staff_management);

        buttonAddStaff = findViewById(R.id.buttonAddStaff);
        recyclerViewStaff = findViewById(R.id.recyclerViewStaff);
        recyclerViewStaff.setLayoutManager(new LinearLayoutManager(this));

        staffList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            staffList.add(new Staff("username", "1234", true));
        }

        staffAdapter = new StaffAdapter(staffList);
        recyclerViewStaff.setAdapter(staffAdapter);

        buttonAddStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle add staff logic here
            }
        });
    }
}
