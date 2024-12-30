package com.example.a2210900053_hoangkimoanh;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class EditProductActivity extends AppCompatActivity {
    private EditText maSPEditText, tenSPEditText, soLuongEditText, donGiaEditText;
    private Button btnSave;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        dbHelper = new DBHelper(this);

        maSPEditText = findViewById(R.id.edtMaSP53);
        tenSPEditText = findViewById(R.id.edtTenSP53);
        soLuongEditText = findViewById(R.id.edtSoLuong53);
        donGiaEditText = findViewById(R.id.edtDonGia53);
        btnSave = findViewById(R.id.btnSave53);

        // Kiểm tra hành động: Thêm hoặc Sửa
        String action = getIntent().getStringExtra("action");
        if ("edit".equals(action)) {
            String maSP = getIntent().getStringExtra("maSP");
            Product product = dbHelper.getProductByMaSP(maSP);
            if (product != null) {
                maSPEditText.setText(product.getMaSP());
                tenSPEditText.setText(product.getTenSP());
                soLuongEditText.setText(String.valueOf(product.getSoLuong()));
                donGiaEditText.setText(String.valueOf(product.getDonGia()));
                maSPEditText.setEnabled(false); // Không cho phép sửa mã sản phẩm
            }
        }

        btnSave.setOnClickListener(v -> {
            // Lưu sản phẩm
            String maSP = maSPEditText.getText().toString();
            String tenSP = tenSPEditText.getText().toString();
            int soLuong = Integer.parseInt(soLuongEditText.getText().toString());
            double donGia = Double.parseDouble(donGiaEditText.getText().toString());

            Product product = new Product(maSP, tenSP, soLuong, donGia);
            if ("edit".equals(action)) {
                dbHelper.updateProduct(product.getMaSP(), product.getTenSP(), product.getSoLuong(), product.getDonGia());
            } else {
                dbHelper.addProduct(product.getMaSP(), product.getTenSP(), product.getSoLuong(), product.getDonGia());
            }

            finish();
        });
    }
}
