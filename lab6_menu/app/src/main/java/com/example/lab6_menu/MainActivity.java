package com.example.lab6_menu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ListView lvNhanVien;
    AdapterNhanVien  adapter;
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
    }
    private void addView() {
        lvNhanVien=findViewById(R.id.lvNhanVien);
        adapter=new AdapterNhanVien(MainActivity.this,R.layout.item);
        lvNhanVien.setAdapter(adapter);
        registerForContextMenu(lvNhanVien);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuThem)
        {
            hienThiManHinhNhap();
        }
        if(item.getItemId()==R.id.mnuHuongdan)
        {
            Toast.makeText(MainActivity.this,"Hướng dẫn", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiManHinhNhap() {
        Dialog dialog=new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.chitiet);
        EditText edtMa=dialog.findViewById(R.id.edtMa);
        EditText edtTen=dialog.findViewById(R.id.edtTen);
        RadioButton radNam=dialog.findViewById((R.id.radNam));
        RadioButton radNu=dialog.findViewById((R.id.radNu));
        Button btnLuu=dialog.findViewById(R.id.btnLuu);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NhanVien nv=new NhanVien();
                nv.setMa(edtMa.getText().toString());
                nv.setTen(edtTen.getText().toString());
                if(radNam.isChecked())
                    nv.setNam(true);
                adapter.add(nv);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.mnuSua)
            Toast.makeText(MainActivity.this,"Sửa",Toast.LENGTH_SHORT).show();
        if(item.getItemId()==R.id.mnuXoa)
            Toast.makeText(MainActivity.this,"Xóa",Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}