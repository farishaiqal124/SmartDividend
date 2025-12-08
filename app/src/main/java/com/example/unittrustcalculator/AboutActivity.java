package com.example.unittrustcalculator; // Check your package name!

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Enable the "Back" arrow in the top toolbar for better UX
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About");
        }
    }

    // --- NAVIGATION MENU SETUP ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            // Navigate back to Home (MainActivity)
            Intent intent = new Intent(this, MainActivity.class);
            // Clear stack so hitting back doesn't loop
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "You are currently on the About screen", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == android.R.id.home) {
            // Handle the back arrow click
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}