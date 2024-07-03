package com.example.airline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.airline.R;

public class ProfileFragment extends Fragment {
    Users users = Users.getInstance();
    User logged_in = users.getLoggedInUser();
    private ImageView profileImageView;
    private EditText usernameTextView;
    private EditText emailTextView;
    private EditText phoneTextView;
    private Button editProfileButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize UI elements
        profileImageView = view.findViewById(R.id.profile_image);
        usernameTextView = view.findViewById(R.id.username);
        emailTextView = view.findViewById(R.id.email);
        phoneTextView = view.findViewById(R.id.phone);
        editProfileButton = view.findViewById(R.id.edit_profile_button);

        // Populate UI elements with data
        // In a real app, this data might come from a ViewModel, a database, or a network request
        populateProfileData();

        // Set up button click listener
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle edit profile button click
                // For example, navigate to an edit profile screen
                editProfile();
            }
        });

        return view;
    }

    private void populateProfileData() {
        String username = logged_in.getUsername();
        String email = logged_in.getEmail();
        String phone = logged_in.getPhone();

        // Set data to UI elements
        usernameTextView.setText(username);
        emailTextView.setText(email);
        phoneTextView.setText(phone);
    }

    public void editProfile() {
        logged_in.setEmail(emailTextView.getText().toString());
        logged_in.setPhone(phoneTextView.getText().toString());
        logged_in.setUsername(usernameTextView.getText().toString());
    }
}