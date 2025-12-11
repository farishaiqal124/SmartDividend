package com.example.smartdividend; // UPDATE THIS IF YOUR PACKAGE NAME IS DIFFERENT

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // 1. Setup the Toolbar (Back Arrow)
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("About");
        }

        // 2. Setup the "View on GitHub" Button
        Button btnGithub = findViewById(R.id.btnGithub);

        btnGithub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the browser with your repository URL
                String url = "https://github.com/farishaiqal124/SmartDividend";
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });
    }

    // --- NAVIGATION MENU SETUP ---

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu to show the 3 dots
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
            // We are already on About, just show a message
            Toast.makeText(this, "You are currently on the About screen", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == android.R.id.home) {
            // Handle the standard Back Arrow (Top Left)
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}