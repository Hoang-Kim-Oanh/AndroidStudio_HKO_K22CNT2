package com.example.day06;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Product> productList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        productList = new ArrayList<>();

        adapter = new ProductAdapter(MainActivity.this, productList);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tmdmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            View addLayout = getLayoutInflater().inflate(R.layout.add_layout, null);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(addLayout);
            builder.setTitle("Thêm Sản Phẩm");

            EditText editTextName = addLayout.findViewById(R.id.editTextName);
            EditText editTextPrice = addLayout.findViewById(R.id.editTextPrice);
            Button buttonAddProduct = addLayout.findViewById(R.id.buttonAdd);

            // Khởi tạo dialog
            AlertDialog dialog = builder.create();

            buttonAddProduct.setOnClickListener(v -> {
                String name = editTextName.getText().toString();
                String priceString = editTextPrice.getText().toString();

                if (!name.isEmpty() && !priceString.isEmpty()) {
                    double price = 0;
                    try {
                        price = Double.parseDouble(priceString);
                    } catch (NumberFormatException e) {
                        Toast.makeText(MainActivity.this, "Giá không hợp lệ!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Product product = new Product();
                    product.setName(name);
                    product.setPrice(price);

                    productList.add(product);
                    adapter.notifyDataSetChanged();  // Cập nhật lại ListView

                    Toast.makeText(MainActivity.this, "Sản phẩm đã được thêm!", Toast.LENGTH_SHORT).show();

                    dialog.dismiss();  // Đóng dialog sau khi thêm sản phẩm
                } else {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}