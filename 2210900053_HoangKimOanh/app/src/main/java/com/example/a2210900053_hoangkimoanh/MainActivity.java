package com.example.a2210900053_hoangkimoanh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ProductAdapter adapter;
    private List<Product> productList;
    private DBHelper dbHelper;

    private Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView53);
        btnAddProduct = findViewById(R.id.btnAdd53);

        dbHelper = new DBHelper(this);

        productList = new ArrayList<>();

        // Setup adapter với lắng nghe sự kiện sửa và xóa
        adapter = new ProductAdapter(this, productList, new ProductAdapter.OnProductActionListener() {
            @Override
            public void onEdit(Product product) {
                openEditProductScreen(product, "edit");
            }

            @Override
            public void onDelete(Product product) {
                deleteProduct(product);
            }
        });

        listView.setAdapter(adapter);

        // Load data từ cơ sở dữ liệu
        loadProducts();

        // Setup button Thêm sản phẩm
        btnAddProduct.setOnClickListener(v -> openEditProductScreen(null, "add"));
    }

    private void loadProducts() {
        productList.clear(); // Clear danh sách trước khi load lại
        dbHelper.copyDatabase(); // Copy cơ sở dữ liệu từ assets nếu cần
        List<Product> products = dbHelper.getAllProducts();
        if (products != null && !products.isEmpty()) {
            productList.addAll(products);
        }
        adapter.notifyDataSetChanged(); // Thông báo adapter cập nhật
    }

    private void openEditProductScreen(Product product, String action) {
        Intent intent = new Intent(MainActivity.this, EditProductActivity.class);
        if ("edit".equals(action) && product != null) {
            intent.putExtra("action", "edit");
            intent.putExtra("maSP", product.getMaSP());
        } else {
            intent.putExtra("action", "add");
        }
        startActivity(intent);
    }

    private void deleteProduct(Product product) {
        dbHelper.deleteProduct(product.getMaSP());
        productList.remove(product);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Sản phẩm đã được xóa", Toast.LENGTH_SHORT).show();
    }
}
