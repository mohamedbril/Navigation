package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 1) Using Constants for Intent keys prevents typos across different Activity files
    public static final String KEY_NAME = "nom";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PHONE = "phone";
    public static final String KEY_ADDRESS = "adresse";
    public static final String KEY_CITY = "ville";

    // 2) Completely renamed variables for the input fields
    private EditText fieldFullName;
    private EditText fieldEmailAddress;
    private EditText fieldPhoneNumber;
    private EditText fieldStreetAddress;
    private EditText fieldCityLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Delegating the view setup to keep onCreate() clean and readable
        initializeUIComponents();
    }

    // Extracted method to handle all the findViewById calls
    private void initializeUIComponents() {
        fieldFullName = findViewById(R.id.nom);
        fieldEmailAddress = findViewById(R.id.email);
        fieldPhoneNumber = findViewById(R.id.phone);
        fieldStreetAddress = findViewById(R.id.adresse);
        fieldCityLocation = findViewById(R.id.ville);

        Button submitDataBtn = findViewById(R.id.btnEnvoyer);
        
        // Using a "Method Reference" (this::...) instead of a lambda or inner class
        submitDataBtn.setOnClickListener(this::processAndNavigate);
    }

    // Dedicated method that gets triggered when the button is clicked
    private void processAndNavigate(View view) {
        // Extracting values into new string variables
        String valName = fieldFullName.getText().toString().trim();
        String valEmail = fieldEmailAddress.getText().toString().trim();
        String valPhone = fieldPhoneNumber.getText().toString().trim();
        String valAddress = fieldStreetAddress.getText().toString().trim();
        String valCity = fieldCityLocation.getText().toString().trim();

        // Calling a custom validation helper method
        if (isInputMissing(valName, valEmail)) {
            displayWarning("Nom et Email sont obligatoires.");
            return; // Stops execution
        }

        // ALTERNATIVE METHOD: Using a Bundle to group all data together first
        Bundle navigationData = new Bundle();
        navigationData.putString(KEY_NAME, valName);
        navigationData.putString(KEY_EMAIL, valEmail);
        navigationData.putString(KEY_PHONE, valPhone);
        navigationData.putString(KEY_ADDRESS, valAddress);
        navigationData.putString(KEY_CITY, valCity);

        // Creating the Intent and attaching the entire Bundle at once
        Intent nextScreenIntent = new Intent(MainActivity.this, Screen2Activity.class);
        nextScreenIntent.putExtras(navigationData); 

        startActivity(nextScreenIntent);
    }

    // Helper method to keep validation logic separate
    private boolean isInputMissing(String nameToCheck, String emailToCheck) {
        return nameToCheck.isEmpty() || emailToCheck.isEmpty();
    }

    // Helper method to simplify Toast creation
    private void displayWarning(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
