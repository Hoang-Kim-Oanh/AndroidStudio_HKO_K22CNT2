package com.example.hoagkimoanh;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button butdangnhap;
    private EditText edttendangnhap, edtmatkhau;

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

        getView();
        addevent();
    }

    private void getView() {
        butdangnhap = findViewById(R.id.butdangnhap);
        edttendangnhap = findViewById(R.id.edttendangnhap);
        edtmatkhau = findViewById(R.id.edtmatkhau);
    }

    private void addevent() {
        butdangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tendangnhap = edttendangnhap.getText().toString();
                String matkhau = edtmatkhau.getText().toString();

                // Hiển thị thông tin tên đăng nhập và mật khẩu
                Toast.makeText(MainActivity.this, tendangnhap + " - " + matkhau, Toast.LENGTH_SHORT).show();

                if (tendangnhap.trim().equals("CNTT") && matkhau.equals("123456")) {
                    Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
