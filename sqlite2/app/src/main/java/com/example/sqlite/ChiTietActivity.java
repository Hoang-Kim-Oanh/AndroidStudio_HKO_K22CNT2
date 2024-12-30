package com.example.sqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChiTietActivity extends AppCompatActivity {
    Intent intent;
    EditText edtMa,edTen,edDienThoai;
    Button BtnThemSua, BtnThoat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addview();
        addvent();
    }

    private void addvent() {
        BtnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ChiTietActivity.this,MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void addview() {
        intent=getIntent();
        edtMa=findViewById(R.id.edtMa);
        edTen=findViewById(R.id.edtTen);
        edDienThoai=findViewById(R.id.edtDT);
        BtnThoat=findViewById(R.id.btnThoat);
        Contact ct=(Contact) intent.getSerializableExtra("CONTACT");
        edtMa.setText(ct.getMa()+"");
        edtMa.setEnabled(false);
        edTen.setText(ct.getTen());
        edDienThoai.setText(ct.getDienthoai());
    }
}