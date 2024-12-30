package com.example.a2210900053_hoangkimoanh;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Sanpham53.db";
    private static final String DATABASE_PATH = "/data/data/%s/databases/";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    private String dbFullPath;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dbFullPath = String.format(DATABASE_PATH, context.getPackageName()) + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void copyDatabase() {
        try {
            File dbFile = new File(dbFullPath);
            if (!dbFile.exists()) {
                File dbDir = new File(dbFile.getParent());
                if (!dbDir.exists()) dbDir.mkdirs();

                AssetManager assetManager = context.getAssets();
                InputStream inputStream = assetManager.open(DATABASE_NAME);
                OutputStream outputStream = new FileOutputStream(dbFullPath);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }

                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Mở cơ sở dữ liệu
    public SQLiteDatabase openDatabase() {
        copyDatabase(); // Sao chép file cơ sở dữ liệu nếu chưa tồn tại
        return SQLiteDatabase.openDatabase(dbFullPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    // Thêm sản phẩm
    public void addProduct(String maSP, String tenSP, int soLuong, double donGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("maSP", maSP);
        values.put("tenSP", tenSP);
        values.put("soLuong", soLuong);
        values.put("donGia", donGia);
        db.insert("SanPham53", null, values);
        db.close();
    }

    // Sửa sản phẩm
    public void updateProduct(String maSP, String tenSP, int soLuong, double donGia) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenSP", tenSP);
        values.put("soLuong", soLuong);
        values.put("donGia", donGia);

        // Cập nhật thông tin sản phẩm theo maSP
        db.update("SanPham53", values, "maSP = ?", new String[]{maSP});
        db.close();
    }

    // Xóa sản phẩm
    public void deleteProduct(String maSP) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("SanPham53", "maSP = ?", new String[]{maSP});
        db.close();
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = openDatabase(); // Mở cơ sở dữ liệu
        Cursor cursor = db.rawQuery("SELECT * FROM SanPham53", null);

        if (cursor != null && cursor.getCount() > 0) { // Kiểm tra nếu có dữ liệu
            if (cursor.moveToFirst()) {
                do {
                    Product product = new Product();

                    // Lấy và chuyển đổi kiểu dữ liệu cho các cột
                    int maSPIndex = cursor.getColumnIndex("maSP");
                    if (maSPIndex != -1) {
                        product.setMaSP(cursor.getString(maSPIndex)); // Sử dụng getString nếu maSP là chuỗi
                    }

                    int tenSPIndex = cursor.getColumnIndex("tenSP");
                    if (tenSPIndex != -1) {
                        product.setTenSP(cursor.getString(tenSPIndex));
                    }

                    int thanhTienIndex = cursor.getColumnIndex("thanhTien");
                    if (thanhTienIndex != -1) {
                        product.setThanhTien(cursor.getDouble(thanhTienIndex)); // Chuyển sang kiểu double nếu là số thập phân
                    }

                    products.add(product);

                } while (cursor.moveToNext());
            }
            cursor.close();
        } else {
            // Thông báo nếu không có dữ liệu
            Log.e("DBHelper", "No data found in 'SanPham53' table.");
        }
        return products;
    }


    // Lấy sản phẩm theo maSP
    public Product getProductByMaSP(String maSP) {
        SQLiteDatabase db = this.getReadableDatabase();
        Product product = null;
        try (Cursor cursor = db.query("SanPham53",
                new String[]{"maSP", "tenSP", "soLuong", "donGia"},
                "maSP = ?",
                new String[]{maSP},
                null, null, null)) {
            if (cursor != null && cursor.moveToFirst()) {
                String tenSP = cursor.getString(cursor.getColumnIndexOrThrow("tenSP"));
                int soLuong = cursor.getInt(cursor.getColumnIndexOrThrow("soLuong"));
                double donGia = cursor.getDouble(cursor.getColumnIndexOrThrow("donGia"));
                product = new Product(maSP, tenSP, soLuong, donGia);
            }
        }
        return product;
    }

}
