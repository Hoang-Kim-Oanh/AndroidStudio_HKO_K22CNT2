import androidx.appcompat.app.AppCompatActivity;

public class bai4 extends AppCompatActivity {
}
 package com.example.bai4

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bai4 extends AppCompatActivity {
    //khai báo giao diện
    EditText edtName;
    RadioGroup rgEducationLevel;
    RadioButton rbCollege,rbUniversity;
    CheckBox cbC,cbJava,cbJS;
    Button btnSave;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bai4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                // Kiểm tra hệ học được chọn
                String educationLevel ="";
                int selectedID = rgEducationLevel.getCheckedRadioButtonId();
                if (selectedID==rbCollege.getId()){
                    educationLevel="College";
                }else if (selectedID==rbUniversity.getId()){
                    educationLevel="University";
                }
                // Kiểm tra các checkbox ngôn ngữ lập trình
                String favoriteLanguages ="";
                if (cbC.isChecked()){
                    favoriteLanguages+="Lập trình C";
                } else if (cbJava.isChecked()) {
                    favoriteLanguages+="Lập trình Java";
                } else if (cbJS.isChecked()) {
                    favoriteLanguages+="Lập trình JavaScript";
                }
                // Kiểm tra dữ liệu
                if (name.isEmpty()){
                    Toast.makeText(Bai4.this, "Vui lòng nhập tên của bạn", Toast.LENGTH_SHORT).show();

                } else if (educationLevel.isEmpty()) {
                    Toast.makeText(Bai4.this, "Vui lòng chọn hình thức giáo dục", Toast.LENGTH_SHORT).show();
                } else if (favoriteLanguages.isEmpty()) {
                    Toast.makeText(Bai4.this, "Vui lòng chọn ít nhất 1 ngôn ngữ lập trình", Toast.LENGTH_SHORT).show();
                } else {
                    String Result ="Họ tên: "+name +"\n"
                            +"Hình thức giáo dục: "+educationLevel +"\n"
                            +"Ngôn ngữ lập trình: "+favoriteLanguages +"\n";
                    Toast.makeText(Bai4.this, Result, Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}

