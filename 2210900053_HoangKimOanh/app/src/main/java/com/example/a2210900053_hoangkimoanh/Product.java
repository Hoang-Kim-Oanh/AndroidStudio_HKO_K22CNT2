package com.example.a2210900053_hoangkimoanh;

public class Product {
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private double thanhTien; // Thêm trường thanhTien

    public Product(String maSP, String tenSP, int soLuong, double donGia) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = calculateThanhTien(); // Tính toán thành tiền khi tạo đối tượng
    }

    public Product() {
        this.thanhTien = 0.0; // Khởi tạo thanhTien mặc định
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        this.thanhTien = calculateThanhTien(); // Cập nhật lại thành tiền khi số lượng thay đổi
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
        this.thanhTien = calculateThanhTien(); // Cập nhật lại thành tiền khi đơn giá thay đổi
    }

    // Tính thành tiền với giảm giá nếu số lượng lớn hơn 10
    public double getThanhTien() {
        return thanhTien;
    }

    // Phương thức setThanhTien (không thực sự cần thiết, nhưng bạn yêu cầu)
    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Hàm tính thành tiền
    private double calculateThanhTien() {
        double thanhTien = this.soLuong * this.donGia;
        if (this.soLuong > 10) {
            thanhTien *= 0.9; // Giảm 10% nếu số lượng > 10
        }
        return thanhTien;
    }

    @Override
    public String toString() {
        return tenSP + " - " + soLuong + " - " + donGia + " - Thành tiền: " + getThanhTien();
    }
}
