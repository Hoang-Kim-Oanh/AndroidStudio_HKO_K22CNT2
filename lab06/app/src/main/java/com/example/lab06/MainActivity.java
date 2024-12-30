package com.example.lab06;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.Model.DanhMuc;
import com.example.Model.SanPham;

public class MainActivity extends AppCompatActivity {
    Spinner SpnDanhMuc;
    ArrayAdapter<DanhMuc> danhMucArrayAdapter;
    ArrayAdapter<SanPham> sanPhamArrayAdapter;
    EditText edtMaSP, edtTenSP, edtGia;
    ListView lvSanPham;
    Button btnThem;
    DanhMuc SelectedDanhMuc = null;

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
        addview();
        addevent();
    }

    private void addevent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLysanpham();
            }
        });

        SpnDanhMuc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedDanhMuc = danhMucArrayAdapter.getItem(position);
                sanPhamArrayAdapter.clear();
                if (SelectedDanhMuc != null) {
                    sanPhamArrayAdapter.addAll(SelectedDanhMuc.getSanPhams());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì khi không chọn danh mục nào
            }
        });

        // Sự kiện nhấn vào một sản phẩm trong ListView
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham selectedSanPham = sanPhamArrayAdapter.getItem(position);
                if (selectedSanPham != null) {
                    // Hiển thị thông tin sản phẩm lên các EditText
                    edtMaSP.setText(selectedSanPham.getMaSP());
                    edtTenSP.setText(selectedSanPham.getTenSP());
                    edtGia.setText(String.valueOf(selectedSanPham.getGia()));
                }
            }
        });
    }

    private void XuLysanpham() {
        if (SelectedDanhMuc != null) {
            SanPham sanPham = new SanPham(
                    edtMaSP.getText().toString(),
                    edtTenSP.getText().toString(),
                    Integer.parseInt(edtGia.getText().toString())
            );
            SelectedDanhMuc.getSanPhams().add(sanPham);
            sanPhamArrayAdapter.add(sanPham);
        }
    }

    private void addview() {
        SpnDanhMuc = findViewById(R.id.spnSanpham);
        danhMucArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_spinner_item);
        danhMucArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpnDanhMuc.setAdapter(danhMucArrayAdapter);

        lvSanPham = findViewById(R.id.lvSanPham);
        sanPhamArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1);
        lvSanPham.setAdapter(sanPhamArrayAdapter);

        edtMaSP = findViewById(R.id.editmaSp);
        edtTenSP = findViewById(R.id.editTenSp);
        edtGia = findViewById(R.id.editgiaSp);
        btnThem = findViewById(R.id.btnThem);

        // Thêm các danh mục mẫu
        danhMucArrayAdapter.add(new DanhMuc("1", "Bia"));
        danhMucArrayAdapter.add(new DanhMuc("2", "Rượu"));
        danhMucArrayAdapter.add(new DanhMuc("3", "Thuốc"));
    }
}
