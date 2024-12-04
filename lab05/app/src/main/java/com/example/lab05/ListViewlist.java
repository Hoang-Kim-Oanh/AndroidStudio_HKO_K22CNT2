package com.example.lab05;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;  // Import ArrayList
import java.util.List;       // Import List

public class ListViewlist extends AppCompatActivity {

    ListView lstMonHoc;  // ListView reference
    List<String> arrMonHoc;  // List for data
    ArrayAdapter<String> adapter;  // Adapter reference

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_arr);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addView();  // Initialize ListView and Adapter
        addEvent();  // Set event listener for ListView
    }

    private void addView() {
        // Initialize ListView and List
        lstMonHoc = findViewById(R.id.lstMonHoc);  // Bind ListView
        arrMonHoc = new ArrayList<>();  // Create a new ArrayList
        arrMonHoc.add("Toán");  // Add data to the list
        arrMonHoc.add("Anh");
        arrMonHoc.add("Văn");

        // Initialize Adapter and set it to ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrMonHoc);
        lstMonHoc.setAdapter(adapter);  // Set Adapter to ListView
    }

    private void addEvent() {
        // Set an item click listener for the ListView
        lstMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Display selected item using Toast
                Toast.makeText(ListViewlist.this, arrMonHoc.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
