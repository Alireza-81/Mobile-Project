package com.example.airline;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;

    private EditText emailEditText;
    private EditText phoneEditText;
    private RadioGroup radioGroupButtons;

    private Users users = Users.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.phone);
        radioGroupButtons = findViewById(R.id.radio_group);


        findViewById(R.id.login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }
    private void attemptLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String phone = phoneEditText.getText().toString();

        if (username.equals("admin") && password.equals("admin")) {
            // Login success
            User user = new User(username, password, email, phone, true);
            users.addUser(username, password, email, phone, true, UserEnum.ADMIN);
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            launchActivityBasedOnSelection();

        } else {
            // Login failed
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }


    private void launchActivityBasedOnSelection() {
        int selectedId = radioGroupButtons.getCheckedRadioButtonId();

        if (selectedId == R.id.radioButton1) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
            startActivity(intent);
        } else if (selectedId == R.id.radioButton2) {
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, AdminMainMenuActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please select an option", Toast.LENGTH_SHORT).show();
            return;
        }


    }



}
