package com.example.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen2Activity extends AppCompatActivity {

    // Renamed UI components
    private TextView summaryDisplay;
    private Button goBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);

        // Delegating setup to keep onCreate clean
        bindInterface();
        processIncomingData();
    }

    // Separated the findViewById and click listener logic
    private void bindInterface() {
        summaryDisplay = findViewById(R.id.recap);
        goBackBtn = findViewById(R.id.btnRetour);

        // Using a method reference instead of a lambda (v -> finish())
        goBackBtn.setOnClickListener(this::closeCurrentScreen);
    }

    // Separated the data extraction and display logic
    private void processIncomingData() {
        Intent incomingData = getIntent();

        // Assuming the standard putExtra was used. 
        // (Note: If using the Bundle from the previous example, you would use incomingData.getExtras() here instead)
        String fetchedName = incomingData.getStringExtra("nom");
        String fetchedEmail = incomingData.getStringExtra("email");
        String fetchedPhone = incomingData.getStringExtra("phone");
        String fetchedAddress = incomingData.getStringExtra("adresse");
        String fetchedCity = incomingData.getStringExtra("ville");

        // Using StringBuilder instead of standard string concatenation (+) for better memory efficiency
        StringBuilder textBuilder = new StringBuilder();
        
        textBuilder.append("Nom : ").append(validateData(fetchedName)).append("\n");
        textBuilder.append("Email : ").append(validateData(fetchedEmail)).append("\n");
        textBuilder.append("Phone : ").append(validateData(fetchedPhone)).append("\n");
        textBuilder.append("Adresse : ").append(validateData(fetchedAddress)).append("\n");
        textBuilder.append("Ville : ").append(validateData(fetchedCity));

        // Pushing the final built string to the TextView
        summaryDisplay.setText(textBuilder.toString());
    }

    // The finish() call isolated in its own method
    private void closeCurrentScreen(View view) {
        finish();
    }

    // Rewrote the "safe" helper method to avoid the ternary operator (? :) and use explicit conditions
    private String validateData(String rawText) {
        if (rawText == null) {
            return "—";
        }
        
        String cleanText = rawText.trim();
        
        if (cleanText.isEmpty()) {
            return "—";
        }
        
        return cleanText;
    }
}
