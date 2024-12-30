package com.example.Model;

public class SanPham {
    private String ma;  // Mã sản phẩm
    private String ten; // Tên sản phẩm
    private int gia;    // Giá sản phẩm

    // Constructor
    public SanPham(String ma, String ten, int gia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
    }

    // Getter và Setter cho các thuộc tính
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    // Phương thức hiển thị mã sản phẩm cho EditText
    public String getMaSP() {
        return ma;
    }

    // Phương thức hiển thị tên sản phẩm cho EditText
    public String getTenSP() {
        return ten;
    }

    // Định nghĩa lại phương thức toString để hiển thị trong ListView
    @Override
    public String toString() {
        return ma + " - " + ten + " - " + gia + " VND";
    }
}
