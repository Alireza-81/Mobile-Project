package com.example.airline;

import android.view.View;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PurchasedTicketsFragment extends Fragment {

    User logged_in = Users.getInstance().getLoggedInUser();
    private ArrayList<Flight> flightList = logged_in.getPurchased();

    private LinearLayout resultsContainer;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchased_tickets, container, false);
        resultsContainer = view.findViewById(R.id.results_container);

        for (Flight flight : flightList){
            CardView cardView = new CardView(getContext());
            cardView.setCardElevation(4);
            cardView.setRadius(8);
            cardView.setPadding(16, 16, 16, 16);
            cardView.setUseCompatPadding(true);

            LinearLayout cardLayout = new LinearLayout(getContext());
            cardLayout.setOrientation(LinearLayout.VERTICAL);

            TextView flightDetailsText = new TextView(getContext());
            flightDetailsText.setText(
                    "Flight ID: " + flight.getId() +
                            "\nOrigin: " + flight.getOrigin().getValue() +
                            "\nDestination: " + flight.getDestination().getValue() +
                            "\nDate: " + flight.getDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) +
                            "\nRemaining Capacity: " + flight.getRemainingCapacity() +
                            "\nPrice: " + flight.getPrice()
            );

            // Add details and button to card layout
            cardLayout.addView(flightDetailsText);

            // Add card layout to card view
            cardView.addView(cardLayout);

            // Add card view to results container
            resultsContainer.addView(cardView);

        }
        return view;
    }

}