package com.example.airline;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AirplaneAdapter extends RecyclerView.Adapter<AirplaneAdapter.AirplaneViewHolder> {

    private List<Airplane> airplaneList;

    public AirplaneAdapter(List<Airplane> airplaneList) {
        this.airplaneList = airplaneList;
    }

    @NonNull
    @Override
    public AirplaneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_airplane, parent, false);
        return new AirplaneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AirplaneViewHolder holder, int position) {
        Airplane airplane = airplaneList.get(position);
        holder.textViewNameId.setText("Name/ID: " + airplane.getName());
        holder.textViewModel.setText("Model: " + airplane.getModel());
        holder.textViewCapacity.setText("Capacity: " + airplane.getCapacity());
        holder.textViewMaxLuggageWeight.setText("Max Luggage Weight Per Person: " + airplane.getMaxLuggageWeightPerPerson());

        holder.buttonDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.detailsLayout.getVisibility() == View.GONE) {
                    holder.detailsLayout.setVisibility(View.VISIBLE);
                    holder.buttonDetails.setText("Hide Details");
                } else {
                    holder.detailsLayout.setVisibility(View.GONE);
                    holder.buttonDetails.setText("Show Details");
                }
            }
        });

        holder.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit action
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete action
            }
        });
    }

    @Override
    public int getItemCount() {
        return airplaneList.size();
    }

    public static class AirplaneViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNameId, textViewModel, textViewCapacity, textViewMaxLuggageWeight;
        LinearLayout detailsLayout;
        Button buttonDetails, buttonEdit, buttonDelete;

        public AirplaneViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNameId = itemView.findViewById(R.id.textViewNameId);
            textViewModel = itemView.findViewById(R.id.textViewModel);
            textViewCapacity = itemView.findViewById(R.id.textViewCapacity);
            textViewMaxLuggageWeight = itemView.findViewById(R.id.textViewMaxLuggageWeight);
            detailsLayout = itemView.findViewById(R.id.detailsLayout);
            buttonDetails = itemView.findViewById(R.id.buttonDetails);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);

            detailsLayout.setVisibility(View.GONE);
        }
    }
}
