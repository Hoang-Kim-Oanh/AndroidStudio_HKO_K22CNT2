package com.example.lab_62;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    ListView listViewProducts;
    String[] products = {"Sản phẩm 1", "Sản phẩm 2", "Sản phẩm 3", "Sản phẩm 4"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewProducts = findViewById(R.id.listViewProducts);

        // Cài đặt Adapter cho ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, products);
        listViewProducts.setAdapter(adapter);

        // Xử lý sự kiện click vào item trong ListView
        listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Mở màn hình chi tiết sản phẩm
                Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                intent.putExtra("productName", products[position]); // Gửi tên sản phẩm đến màn hình chi tiết
                startActivity(intent);
            }
        });
    }
}
