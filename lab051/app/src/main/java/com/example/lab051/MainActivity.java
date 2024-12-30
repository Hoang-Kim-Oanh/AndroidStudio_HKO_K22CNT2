package com.example.lab051;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.adapter.SanPhamAdapter;
import com.example.model.SanPham;

public class MainActivity extends AppCompatActivity {
    ListView lvSanPham;
    SanPhamAdapter sanPhamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addView();
        addEvent();
    }

    private void addEvent() {
    }

    private void addView() {
        lvSanPham=findViewById(R.id.lstSanPham);
        sanPhamAdapter = new SanPhamAdapter( MainActivity.this,R.layout.item);
        lvSanPham.setAdapter(sanPhamAdapter);
        fakeData();



    }

    private void fakeData() {
        sanPhamAdapter.add(new SanPham(1,"vay",150,R.drawable.ads2));
        sanPhamAdapter.add(new SanPham( 2,"Ban Phim ",150,R.drawable.banphimquangco));
        sanPhamAdapter.add(new SanPham( 3,"chuot quang", 250,R.drawable.chuotquang));
        sanPhamAdapter.add(new SanPham( 4,"Ram18GB",400,R.drawable.ram));
    }
}