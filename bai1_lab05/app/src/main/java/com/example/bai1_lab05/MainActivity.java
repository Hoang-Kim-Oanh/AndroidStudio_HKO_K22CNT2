package com.example.bai1_lab05;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText etId, etName, etPhone;
    private Button btnAdd;
    private ListView lvEmployees;

    private ArrayList<String> employees;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các View
        etId = findViewById(R.id.etId);
        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnAdd = findViewById(R.id.btnAdd);
        lvEmployees = findViewById(R.id.lvEmployees);

        // Tạo danh sách và adapter
        employees = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
        lvEmployees.setAdapter(adapter);

        // Sự kiện khi nhấn nút "Thêm mới"
        btnAdd.setOnClickListener(v -> {
            String id = etId.getText().toString().trim();
            String name = etName.getText().toString().trim();
            String phone = etPhone.getText().toString().trim();

            if (id.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                String employeeInfo = "ID: " + id + ", Tên: " + name + ", SĐT: " + phone;
                employees.add(employeeInfo);
                adapter.notifyDataSetChanged();

                etId.setText("");
                etName.setText("");
                etPhone.setText("");
            }
        });

        // Sự kiện khi chọn một mục trong ListView
        lvEmployees.setOnItemClickListener((parent, view, position, id) -> {
            String selectedEmployee = employees.get(position);
            String[] parts = selectedEmployee.split(", ");

            if (parts.length == 3) {
                etId.setText(parts[0].replace("ID: ", ""));
                etName.setText(parts[1].replace("Tên: ", ""));
                etPhone.setText(parts[2].replace("SĐT: ", ""));
            } else {
                Toast.makeText(this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
        });

        // Sự kiện khi long click vào một mục trong ListView
        lvEmployees.setOnItemLongClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(this)
                    .setTitle("Xóa nhân viên")
                    .setMessage("Bạn có chắc chắn muốn xóa nhân viên này?")
                    .setPositiveButton("Có", (dialog, which) -> {
                        employees.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(this, "Đã xóa nhân viên!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Không", null)
                    .show();
            return true;
        });
    }
}
