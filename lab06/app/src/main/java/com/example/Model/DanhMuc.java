package com.example.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DanhMuc {
    private String ma; // Mã danh mục
    private String ten; // Tên danh mục
    private ArrayList<SanPham> sanPhams; // Danh sách sản phẩm thuộc danh mục

    // Constructor đầy đủ (khi đã có danh sách sản phẩm)
    public DanhMuc(String ma, String ten, ArrayList<SanPham> sanPhams) {
        this.ma = ma;
        this.ten = ten;
        this.sanPhams = sanPhams != null ? sanPhams : new ArrayList<>(); // Đảm bảo không null
    }

    // Constructor đơn giản (không truyền danh sách sản phẩm, tự khởi tạo)
    public DanhMuc(String ma, String ten) {
        this.ma = ma;
        this.ten = ten;
        this.sanPhams = new ArrayList<>();
    }

    // Getter và Setter
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public ArrayList<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(ArrayList<SanPham> sanPhams) {
        this.sanPhams = sanPhams != null ? sanPhams : new ArrayList<>(); // Đảm bảo không null
    }

    // Phương thức thêm sản phẩm vào danh mục
    public void addSanPham(SanPham sanPham) {
        if (sanPham != null) {
            this.sanPhams.add(sanPham);
        }
    }

    // Phương thức xóa sản phẩm khỏi danh mục
    public void removeSanPham(SanPham sanPham) {
        this.sanPhams.remove(sanPham);
    }

    // Hiển thị thông tin danh mục
    @NonNull
    @Override
    public String toString() {
        return ma + " - " + ten; // Không cần dấu '-' cuối cùng
    }
}
