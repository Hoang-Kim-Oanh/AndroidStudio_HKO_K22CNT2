package com.example.lab05;

import android.content.Intent;
import android.os.Bundle;
import android.view.View; // Add this import

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Method to handle navigation to ListViewArr
    public void molistViewArr(View view) {
        Intent intent = new Intent(MainActivity.this, ListViewArr.class); // Fixed: added semicolon
        startActivity(intent);  // Start the ListViewArr activity
    }
    public void molistViewlist(View view) {
        Intent intent = new Intent(MainActivity.this, ListViewArr.class); // Fixed: added semicolon
        startActivity(intent);  // Start the ListViewArr activity
    }
}
