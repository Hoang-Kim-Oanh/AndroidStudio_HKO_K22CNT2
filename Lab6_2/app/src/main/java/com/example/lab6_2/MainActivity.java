package com.example.quanlysanpham_intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.model.SanPham;
import com.example.lab6_2.R;

public class MainActivity extends AppCompatActivity {
    ListView lvSanpham;
    ArrayAdapter<SanPham> adtSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addView();
        addEvent();
    }

    private void addEvent() {
        lvSanpham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SanPham sp = adtSanPham.getItem(i);
                if (sp != null) {
                    Intent intent = new Intent(MainActivity.this, com.example.quanlysanpham_intent.ChiTietActivity.class);
                    intent.putExtra("SANPHAM", sp);
                    startActivityForResult(intent, 113);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 113 && resultCode == 115 && data != null) {
            SanPham sp = (SanPham) data.getSerializableExtra("SANPHAM");
            if (sp != null) {
                for (int i = 0; i < adtSanPham.getCount(); i++) {
                    if (sp.getMa().equals(adtSanPham.getItem(i).getMa())) {
                        adtSanPham.remove(adtSanPham.getItem(i));
                        break;
                    }
                }
            }
        }
    }

    private void addView() {
        lvSanpham = findViewById(R.id.lvSanPham);
        adtSanPham = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvSanpham.setAdapter(adtSanPham);

        // Thêm dữ liệu mẫu
        adtSanPham.add(new SanPham("001", "Kem ly", 50000));
        adtSanPham.add(new SanPham("002", "Kem que", 55000));
        adtSanPham.add(new SanPham("003", "Kem ốc quế", 55000));
    }
}
