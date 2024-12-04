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

public class ListViewArr extends AppCompatActivity {
    ListView lstMonHoc;  // ListView reference
    String[] arrMonHoc;  // Array for data
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
        lstMonHoc = findViewById(R.id.lstMonHoc);  // Bind ListView
        arrMonHoc = new String[]{"Toán", "Văn", "Anh"};  // Data for ListView
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrMonHoc);  // Initialize Adapter
        lstMonHoc.setAdapter(adapter);  // Set Adapter to ListView
    }

    private void addEvent() {
        lstMonHoc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Display selected item using Toast
                Toast.makeText(ListViewArr.this, arrMonHoc[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
