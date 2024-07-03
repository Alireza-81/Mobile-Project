package com.example.airline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffAdminAdapter extends RecyclerView.Adapter<StaffAdminAdapter.StaffViewHolder> {

    private List<Staff> staffList;

    public StaffAdminAdapter(List<Staff> staffList) {
        this.staffList = staffList;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff, parent, false);
        return new StaffViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        Staff staff = staffList.get(position);
        holder.username.setText(staff.getUsername());

        // Sample flight data
        List<Flight> flights = new ArrayList<>(
                Arrays.asList(
                        new Flight(CityEnum.SHIRAZ, CityEnum.AHVAZ, LocalDateTime.now(), "", null, null, 2, 2)
                )
        );
        FlightStaffAdapter flightAdapter = new FlightStaffAdapter(flights);
        holder.recyclerViewFlights.setAdapter(flightAdapter);
        holder.recyclerViewFlights.setLayoutManager(new LinearLayoutManager(holder.itemView.getContext()));

        holder.expandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.detailsLayout.getVisibility() == View.GONE) {
                    holder.detailsLayout.setVisibility(View.VISIBLE);
                    holder.expandButton.setText("Show Less");
                } else {
                    holder.detailsLayout.setVisibility(View.GONE);
                    holder.expandButton.setText("Show More");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return staffList.size();
    }

    public static class StaffViewHolder extends RecyclerView.ViewHolder {

        public TextView username;
        public Button expandButton;
        public LinearLayout detailsLayout;
        public RecyclerView recyclerViewFlights;

        public StaffViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            expandButton = itemView.findViewById(R.id.expandButton);
            detailsLayout = itemView.findViewById(R.id.detailsLayout);
            recyclerViewFlights = itemView.findViewById(R.id.recyclerViewFlights);
        }
    }
}
