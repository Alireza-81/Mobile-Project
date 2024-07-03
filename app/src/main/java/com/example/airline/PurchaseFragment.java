package com.example.airline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class PurchaseFragment extends Fragment {
    private Users users = Users.getInstance();
    private User logged_in = users.getLoggedInUser();
    private Spinner originSpinner;
    private Spinner dateSpinner;
    private Spinner destinationSpinner;
    private Spinner seatsSpinner;
    private LinearLayout resultsContainer;
    private FlightDAO flightDAO;
    private List<String> dates;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);

        // Initialize UI components
        originSpinner = view.findViewById(R.id.origin_spinner);
        dateSpinner = view.findViewById(R.id.date_spinner);
        destinationSpinner = view.findViewById(R.id.destination_spinner);
        seatsSpinner = view.findViewById(R.id.seats_spinner);
        Button searchButton = view.findViewById(R.id.search_button);
        resultsContainer = view.findViewById(R.id.results_container);

        // Initialize FlightDAO
        flightDAO = FlightDAO.getInstance(getContext());
        flightDAO.open();

        // Populate spinners with dynamic data
        populateSpinners();

        // Insert sample flights
        insertSampleFlights();

        // Set onClickListener for the search button
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSearch();
            }
        });

        return view;
    }


    private void populateSpinners() {
        // Dummy data for dates, replace with dynamic data fetching if necessary
        dates = Arrays.asList(
                "2024-07-14", "2024-07-15", "2024-07-16", "2024-07-17"
        );
        List<String> destinations = CityEnum.getValues();
        List<Integer> seats = Arrays.asList(1, 2, 3, 4);

        // Populate origin spinner
        ArrayAdapter<String> originAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, destinations);
        originAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        originSpinner.setAdapter(originAdapter);

        // Populate date spinner
        ArrayAdapter<String> dateAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, dates);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);

        // Populate destination spinner
        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, destinations);
        destinationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destinationSpinner.setAdapter(destinationAdapter);

        // Populate seats spinner
        ArrayAdapter<Integer> seatsAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, seats);
        seatsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        seatsSpinner.setAdapter(seatsAdapter);
    }

    private void insertSampleFlights() {
        // Clear existing flights for demonstration purposes
        flightDAO.dropAndRecreateTable();

        // Insert sample flights into the database
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-14T10:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-14T15:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.ISFAHAN, LocalDateTime.parse("2024-07-15T12:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.ISFAHAN, LocalDateTime.parse("2024-07-15T17:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-16T14:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-16T19:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.AHVAZ, LocalDateTime.parse("2024-07-17T16:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.AHVAZ, LocalDateTime.parse("2024-07-17T21:00"), "Boeing 747", Arrays.asList("Staff1", "Staff2"), Arrays.asList("Customer1"), 10, 200));

        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-14T10:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-14T13:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-15T12:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-15T15:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-16T14:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-16T18:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-17T16:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));
        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-17T19:00"), "Airbus A320", Arrays.asList("Staff3", "Staff4"), Arrays.asList("Customer2"), 8, 150));

        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-14T09:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-14T14:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.KERMAN, CityEnum.BANDARABBAS, LocalDateTime.parse("2024-07-15T11:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.KERMAN, CityEnum.BANDARABBAS, LocalDateTime.parse("2024-07-15T14:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-16T13:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-16T16:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.BANDARABBAS, CityEnum.KISH, LocalDateTime.parse("2024-07-17T15:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));
        flightDAO.insertFlight(new Flight(CityEnum.BANDARABBAS, CityEnum.KISH, LocalDateTime.parse("2024-07-17T18:00"), "Boeing 737", Arrays.asList("Staff5", "Staff6"), Arrays.asList("Customer3"), 12, 250));

        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.KISH, LocalDateTime.parse("2024-07-14T08:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.AHVAZ, CityEnum.KISH, LocalDateTime.parse("2024-07-14T13:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.KERMAN, LocalDateTime.parse("2024-07-15T10:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.KERMAN, LocalDateTime.parse("2024-07-15T15:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.TABRIZ, LocalDateTime.parse("2024-07-16T12:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.TABRIZ, LocalDateTime.parse("2024-07-16T17:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-17T14:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-17T19:00"), "Boeing 737", Arrays.asList("Staff7", "Staff8"), Arrays.asList("Customer4"), 6, 300));

        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-18T10:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-18T15:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.ISFAHAN, LocalDateTime.parse("2024-07-19T12:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.ISFAHAN, LocalDateTime.parse("2024-07-19T17:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TABRIZ, LocalDateTime.parse("2024-07-20T14:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TABRIZ, LocalDateTime.parse("2024-07-20T19:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-21T16:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));
        flightDAO.insertFlight(new Flight(CityEnum.TABRIZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-21T21:00"), "Airbus A380", Arrays.asList("Staff9", "Staff10"), Arrays.asList("Customer5"), 20, 400));

        flightDAO.insertFlight(new Flight(CityEnum.BANDARABBAS, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-14T07:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.BANDARABBAS, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-14T12:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.KERMAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-15T09:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.KERMAN, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-15T14:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-16T11:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.MASHHAD, LocalDateTime.parse("2024-07-16T16:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.KISH, LocalDateTime.parse("2024-07-17T13:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));
        flightDAO.insertFlight(new Flight(CityEnum.MASHHAD, CityEnum.KISH, LocalDateTime.parse("2024-07-17T18:00"), "Boeing 777", Arrays.asList("Staff11", "Staff12"), Arrays.asList("Customer6"), 15, 350));

        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.KERMAN, LocalDateTime.parse("2024-07-18T15:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.SHIRAZ, CityEnum.KERMAN, LocalDateTime.parse("2024-07-18T19:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-19T17:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.ISFAHAN, CityEnum.TEHRAN, LocalDateTime.parse("2024-07-19T21:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.BANDARABBAS, LocalDateTime.parse("2024-07-20T19:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.TEHRAN, CityEnum.BANDARABBAS, LocalDateTime.parse("2024-07-20T22:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-21T21:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
        flightDAO.insertFlight(new Flight(CityEnum.KISH, CityEnum.SHIRAZ, LocalDateTime.parse("2024-07-21T23:00"), "Airbus A321", Arrays.asList("Staff13", "Staff14"), Arrays.asList("Customer7"), 10, 300));
    }

    private void performSearch() {
        String selectedOrigin = originSpinner.getSelectedItem().toString();
        String selectedDate = dateSpinner.getSelectedItem().toString();
        String selectedDestination = destinationSpinner.getSelectedItem().toString();
        int selectedSeats = Integer.parseInt(seatsSpinner.getSelectedItem().toString());

        // Parse selected date to LocalDate
        LocalDate date = LocalDate.parse(selectedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // Perform the search using FlightDAO
        List<Flight> flights = flightDAO.getFlightsByDate(date);

        // Filter flights by origin, destination, and remaining capacity
        flights = filterFlights(flights, selectedOrigin, selectedDestination, selectedSeats);

        // Display the search results
        displaySearchResults(flights);
    }

    private List<Flight> filterFlights(List<Flight> flights, String origin, String destination, int seats) {
        CityEnum originEnum = CityEnum.valueOf(origin.toUpperCase());
        CityEnum destinationEnum = CityEnum.valueOf(destination.toUpperCase());

        // Filter flights by origin, destination, and remaining capacity
        flights.removeIf(flight -> !flight.getOrigin().equals(originEnum) || !flight.getDestination().equals(destinationEnum) || flight.getRemainingCapacity() < seats);

        return flights;
    }

    private void displaySearchResults(List<Flight> flights) {
        resultsContainer.removeAllViews();

        if (flights.isEmpty()) {
            TextView noResultsText = new TextView(getContext());
            noResultsText.setText("No flights found.");
            resultsContainer.addView(noResultsText);
        } else {
            for (Flight flight : flights) {
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

                Button purchaseButton = new Button(getContext());
                purchaseButton.setText("Purchase");
                purchaseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        logged_in.addFlight(flight);
                        Toast.makeText(getContext(), "Purchase for flight ID: " + flight.getId(), Toast.LENGTH_SHORT).show();
                    }
                });

                // Add details and button to card layout
                cardLayout.addView(flightDetailsText);
                cardLayout.addView(purchaseButton);

                // Add card layout to card view
                cardView.addView(cardLayout);

                // Add card view to results container
                resultsContainer.addView(cardView);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (flightDAO != null) {
            flightDAO.close();
        }
    }
}