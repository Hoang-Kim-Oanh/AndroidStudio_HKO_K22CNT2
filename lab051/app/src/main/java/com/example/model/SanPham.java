package com.example.model;

public class SanPham {
    private int masp;
    private String tensp;
    private int gia;
    private int hinh;

    public SanPham(){

    }

    public SanPham(int masp, String tensp, int gia, int hinh) {
        this.masp = masp;
        this.tensp = tensp;
        this.gia = gia;
        this.hinh = hinh;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getHinh() {
        return hinh;
    }

    public void setHinh(int hinh) {
        this.hinh = hinh;
    }
}

