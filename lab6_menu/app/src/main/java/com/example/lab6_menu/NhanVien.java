package com.example.lab6_menu;

public class NhanVien {
    private String ma;
    private String ten;
    private boolean isNam;

    public NhanVien() {
    }

    public NhanVien(String ma, String ten, boolean isNam) {
        this.ma = ma;
        this.ten = ten;
        this.isNam = isNam;
    }

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

    public boolean isNam() {
        return isNam;
    }

    public void setNam(boolean nam) {
        isNam = nam;
    }
}
