package com.example.quanlysanpham_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab6_2.R;
import com.example.model.SanPham;

public class ChiTietActivity extends AppCompatActivity {
    EditText edtMa, edtTen, edtGia;
    Button btnXoa, btnThoat;
    Intent intent;
    SanPham sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);

        // Xử lý Edge-to-Edge
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addView();
        addEvent();
    }

    private void addEvent() {
        btnThoat.setOnClickListener(view -> finish());

        btnXoa.setOnClickListener(view -> {
            intent.putExtra("SANPHAM", sp);
            setResult(115, intent);
            finish();
        });
    }

    private void addView() {
        edtMa = findViewById(R.id.edtMaSP);
        edtTen = findViewById(R.id.edtTenSP);
        edtGia = findViewById(R.id.edtGiaSP);
        btnXoa = findViewById(R.id.btnXoa);
        btnThoat = findViewById(R.id.btnTrove);

        intent = getIntent();
        sp = (SanPham) intent.getSerializableExtra("SANPHAM");

        if (sp != null) {
            edtMa.setText(sp.getMa());
            edtTen.setText(sp.getTen());
            edtGia.setText(String.valueOf(sp.getGia()));
        }
    }
}
