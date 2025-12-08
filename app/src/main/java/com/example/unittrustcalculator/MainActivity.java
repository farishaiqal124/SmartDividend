package com.example.unittrustcalculator; // Make sure this matches your package name!

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // Declare variables for UI components
    private TextInputEditText etAmount, etRate, etMonths;
    private TextView tvResult;
    private Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Initialize UI components
        etAmount = findViewById(R.id.etAmount);
        etRate = findViewById(R.id.etRate);
        etMonths = findViewById(R.id.etMonths);
        tvResult = findViewById(R.id.tvResult);
        btnCalculate = findViewById(R.id.btnCalculate);

        // 2. Set Click Listener for the Calculate Button
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateDividend();
            }
        });
    }

    private void calculateDividend() {
        // Get text from inputs
        String amountStr = etAmount.getText().toString();
        String rateStr = etRate.getText().toString();
        String monthsStr = etMonths.getText().toString();

        // VALIDATION: Check for empty fields
        if (amountStr.isEmpty() || rateStr.isEmpty() || monthsStr.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Parse numbers
        double amount = Double.parseDouble(amountStr);
        double rate = Double.parseDouble(rateStr);
        int months = Integer.parseInt(monthsStr);

        // VALIDATION: Check Max 12 months requirement
        if (months > 12) {
            etMonths.setError("Max duration is 12 months");
            return;
        }
        if (months < 1) {
            etMonths.setError("Minimum is 1 month");
            return;
        }

        // CALCULATION LOGIC [cite: 9, 10]
        // Note: We divide rate by 100 because user enters "5" for 5%
        // Formula: (Rate / 12) * Invested Fund
        double monthlyRate = (rate / 100) / 12;
        double monthlyDividend = monthlyRate * amount;

        // Total = Monthly * Months
        double totalDividend = monthlyDividend * months;

        // DISPLAY RESULT
        // Formatting to 2 decimal places [cite: 11]
        String resultText = String.format(Locale.getDefault(), "Total Dividend: RM %.2f", totalDividend);
        tvResult.setText(resultText);
    }

    // --- NAVIGATION MENU SETUP ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Handle Menu Clicks
        if (id == R.id.action_about) {
            // Navigate to About Activity
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_home) {
            // We are already home, do nothing or show toast
            Toast.makeText(this, "You are on the Home screen", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}