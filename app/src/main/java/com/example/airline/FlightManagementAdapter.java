package com.example.airline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class FlightManagementAdapter extends RecyclerView.Adapter<FlightAdapter.FlightViewHolder> {
    private List<Flight> flightList;

    public FlightManagementAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_flight, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.tvFlightNumber.setText("Flight Number: " + flight.getFlightNumber());
        holder.tvCapacity.setText("Capacity: " + flight.getCapacity());
        holder.tvOtherDetails.setText("Details: " + flight.getOtherDetails());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView tvFlightNumber, tvCapacity, tvOtherDetails;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFlightNumber = itemView.findViewById(R.id.tvFlightNumber);
            tvCapacity = itemView.findViewById(R.id.tvCapacity);
            tvOtherDetails = itemView.findViewById(R.id.tvOtherDetails);
        }
    }
}
