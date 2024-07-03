package com.example.airline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FlightStaffAdapter extends RecyclerView.Adapter<FlightStaffAdapter.FlightViewHolder> {

    private List<Flight> flightList;

    public FlightStaffAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flight_for_staff, parent, false);
        return new FlightViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);
        holder.flightId.setText(flight.getId());
        holder.origin.setText(flight.getOrigin().toString());
        holder.destination.setText(flight.getDestination().toString());
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {

        public TextView flightId;
        public TextView origin;
        public TextView destination;
        public ImageView airplaneIcon;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            flightId = itemView.findViewById(R.id.flightId);
            origin = itemView.findViewById(R.id.origin);
            destination = itemView.findViewById(R.id.destination);
            airplaneIcon = itemView.findViewById(R.id.airplaneIcon);
        }
    }
}
