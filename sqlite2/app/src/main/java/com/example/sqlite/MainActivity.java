    package com.example.sqlite;

    import android.content.ContentValues;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.os.Bundle;
    import android.util.Log;
    import android.view.ContextMenu;
    import android.view.MenuItem;
    import android.view.View;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.Toast;

    import androidx.activity.EdgeToEdge;
    import androidx.annotation.NonNull;
    import androidx.annotation.Nullable;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.graphics.Insets;
    import androidx.core.view.ViewCompat;
    import androidx.core.view.WindowInsetsCompat;

    import java.io.File;
    import java.io.FileOutputStream;
    import java.io.InputStream;
    import java.io.OutputStream;

    public class MainActivity extends AppCompatActivity {
        String dbName = "sqlite.db";
        String dbPath = "/databases/";
        SQLiteDatabase db = null;
        contactAdapter adapter;
        ListView lvContact;
        Button btnThem;
        Contact ct;
        int posUpdate;





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
          xulicoppy();
          addView();
          hienthiContact();
          AddEvent();

        }

        private void AddEvent() {
            btnThem=findViewById(R.id.btnThem);
            btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, ThemSuaActivity.class);
                intent.putExtra("TRANGTHAI","THEM");
                startActivityForResult(intent,113);
            }
        });
            lvContact.setOnItemLongClickListener(
                    new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                            ct=adapter.getItem(position);
                            posUpdate=position;

                            return false;
                        }
                    }
            );

        }

        @Override
        public boolean onContextItemSelected(@NonNull MenuItem item) {
            if (item.getItemId()== R.id.mnuSua){
                Intent insets=new Intent(MainActivity.this, ThemSuaActivity.class);
                insets.putExtra("TRANGTHAI","SUA");
                insets.putExtra("CONTACT",ct);
                startActivityForResult(insets,113);

            }
            if (item.getItemId()==R.id.mnuChitiet){
                Intent i = new Intent(MainActivity.this,ChiTietActivity.class);
                i.putExtra("Contact",ct);
                startActivity(i);
            }
            if (item.getItemId()==R.id.mnuXoa){
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn thật sự muốn xóa");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        db.delete("Contact","Ma=?",new String[]{ct.getMa()+""});
                        adapter.remove(ct);
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.setCanceledOnTouchOutside(false);
                //hiển thị cửa sổ này lên:
                dialog.show();

            }
            return super.onContextItemSelected(item);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            super.onCreateContextMenu(menu, v, menuInfo);
            getMenuInflater().inflate(R.menu.context, menu);
        }

        private void hienthiContact() {
            db = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
            Cursor cursor = null;

            try {
                cursor = db.rawQuery("SELECT * FROM context", null);
                while (cursor.moveToNext()) {
                    int ma = cursor.getInt(0);
                    String ten = cursor.getString(1);
                    String dienthoai = cursor.getString(2);
                    adapter.add(new Contact(ma, ten, dienthoai));
                }
            } catch (Exception e) {
                Log.e("LOI", e.toString());
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                if (db != null) {
                    db.close();
                }
            }
        }


        private void addView() {
            lvContact=findViewById(R.id.lvContact);
            adapter=new contactAdapter(MainActivity.this, R.layout.contact_item);
            lvContact.setAdapter(adapter);
            btnThem=findViewById(R.id.btnThem);
            registerForContextMenu(lvContact);
        }

        private void xulicoppy() {
            try{
                File dbFile=getDatabasePath(dbName);
                //TextView txtText=findViewById(R.id.txtText);
                if(!dbFile.exists())
                {
                    copyDataFromAsset();
                    Toast.makeText(MainActivity.this,"Copy thanh cong", Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this
                            ,"file đã tồn tại trên app",
                            Toast.LENGTH_LONG).show();
            }
            catch(Exception e){
                Log.e("Loi",e.toString());
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            super.onActivityResult(requestCode, resultCode, data);
            Contact ctNew = (Contact) data.getSerializableExtra("CONTACT");
            //addnew
            if (resultCode == 114 && requestCode == 113) {
                adapter.add(ctNew);
                try {
                    ContentValues values = new ContentValues();
                    values.put("Ma", ctNew.getMa());
                    values.put("Ten", ctNew.getTen());
                    values.put("Dienthoai", ctNew.getDienthoai());
                    if (db.insert("Contact", null, values) > 0) {
                        Toast.makeText(MainActivity.this, "Thêm mới thành công", Toast.LENGTH_SHORT);

                    } else {
                        Toast.makeText(MainActivity.this, "Thêm mới thất bại", Toast.LENGTH_SHORT);
                    }
                }
                catch(Exception e)
                {
                    Log.e("Loi:",e.toString());
                }
            }
            //end addnew
            //update
            if(requestCode==113 && resultCode==115)
            {
                try{
                    ContentValues values=new ContentValues();
                    values.put("Ten",ctNew.getTen());
                    values.put("Dienthoai",ctNew.getDienthoai());
                    db.update("Contact",values,"Ma=?",new String[]{ctNew.getMa()+""});
                    adapter.getItem(posUpdate).setTen("aaa");
                    adapter.getItem(posUpdate).setDienthoai("kkk");
                    adapter.notifyDataSetChanged();
                }
                catch(Exception e)
                {
                    Log.e("Loi:",e.toString());
                }
            }

        }

        private void copyDataFromAsset() {
            try {
                InputStream myInput = getAssets().open(dbName);
                String outFileName = getApplicationInfo().dataDir + "/databases/" + dbName;

                // Tạo thư mục "databases" nếu nó chưa tồn tại
                File dbDir = new File(getApplicationInfo().dataDir + "/databases/");
                if (!dbDir.exists()) {
                    dbDir.mkdir();
                }

                // Copy file từ assets
                OutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            } catch (Exception ex) {
                Log.e("LOI", ex.toString());
            }
        }


    }