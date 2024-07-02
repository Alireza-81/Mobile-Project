package com.example.airline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FlightManagementAdapter extends RecyclerView.Adapter<FlightManagementAdapter.FlightViewHolder> {

    private List<Flight> flightList;

    public FlightManagementAdapter(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @NonNull
    @Override
    public FlightViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flight, parent, false);
        return new FlightViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightViewHolder holder, int position) {
        Flight flight = flightList.get(position);

        // Bind initial visible details
        holder.originDestination.setText(flight.getOrigin() + " - " + flight.getDestination());
        holder.dateTime.setText(flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

        // Bind additional details (hidden by default)
        holder.flightNumber.setText("Flight Number: " + flight.getDestination());
//        holder.airplane.setText("Airplane: " + flight.getAirplane().getModel());
        holder.staffList.setText("Staff List: " + flight.getStaffList().toString());
        holder.customerList.setText("Customer List: " + flight.getCustomerList().toString());
        holder.remainingCapacity.setText("Remaining Capacity: " + flight.getRemainingCapacity());
        holder.price.setText("Price: " + flight.getPrice());

        // Toggle visibility of details on button click
        holder.expandButton.setOnClickListener(v -> {
            boolean isVisible = holder.detailsLayout.getVisibility() == View.VISIBLE;
            holder.detailsLayout.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            holder.expandButton.setText(isVisible ? "Show More" : "Show Less");
        });

        // Handle edit and delete button clicks (implement your logic)
        holder.editButton.setOnClickListener(v -> {
            // Handle edit action
        });

        holder.deleteButton.setOnClickListener(v -> {
            // Handle delete action
        });
    }

    @Override
    public int getItemCount() {
        return flightList.size();
    }

    public static class FlightViewHolder extends RecyclerView.ViewHolder {
        TextView originDestination, dateTime, flightNumber, airplane, staffList, customerList, remainingCapacity, price;
        Button expandButton, editButton, deleteButton;
        LinearLayout detailsLayout;

        public FlightViewHolder(@NonNull View itemView) {
            super(itemView);
            originDestination = itemView.findViewById(R.id.originDestination);
            dateTime = itemView.findViewById(R.id.dateTime);
            expandButton = itemView.findViewById(R.id.expandButton);
            detailsLayout = itemView.findViewById(R.id.detailsLayout);
            flightNumber = itemView.findViewById(R.id.flightNumber);
            airplane = itemView.findViewById(R.id.airplane);
            staffList = itemView.findViewById(R.id.staffList);
            customerList = itemView.findViewById(R.id.customerList);
            remainingCapacity = itemView.findViewById(R.id.remainingCapacity);
            price = itemView.findViewById(R.id.price);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
