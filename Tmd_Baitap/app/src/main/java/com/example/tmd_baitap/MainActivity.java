package com.example.tmd_baitap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private CustomAdapter adapter;
    private ArrayList<Item> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        Button btnAdd = findViewById(R.id.btnAdd);

        itemList = new ArrayList<>();
        adapter = new CustomAdapter(this, itemList);
        listView.setAdapter(adapter);

        // Xử lý nút Thêm
        btnAdd.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Thêm Bánh");

            View dialogView = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null);
            builder.setView(dialogView);

            EditText edtName = dialogView.findViewById(R.id.edtName);
            EditText edtPrice = dialogView.findViewById(R.id.edtPrice);

            builder.setPositiveButton("Thêm", (dialog, which) -> {
                String name = edtName.getText().toString();
                double price = Double.parseDouble(edtPrice.getText().toString());
                itemList.add(new Item(name, price));
                adapter.notifyDataSetChanged();
            });

            builder.setNegativeButton("Hủy", null);
            builder.show();
        });
    }
}
