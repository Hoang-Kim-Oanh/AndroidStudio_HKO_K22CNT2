package com.example.lab_62;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {

    TextView productDetailText;
    Button btnDeleteProduct, btnBack;
    String productName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Nhận dữ liệu từ Intent
        productName = getIntent().getStringExtra("productName");

        productDetailText = findViewById(R.id.productDetailText);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);
        btnBack = findViewById(R.id.btnBack);

        // Hiển thị tên sản phẩm
        productDetailText.setText("Chi tiết của sản phẩm: " + productName);

        // Xử lý sự kiện nhấn nút Xóa sản phẩm
        btnDeleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý xóa sản phẩm (ở đây bạn có thể thêm mã xóa thực tế)
                // Ví dụ: xóa khỏi danh sách hoặc từ cơ sở dữ liệu
                finish(); // Quay lại màn hình chính
            }
        });

        // Xử lý sự kiện nhấn nút Trở về
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lại màn hình chính
            }
        });
    }
}
