package com.example.model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String ma;
    private String ten;
    private double gia;

    public SanPham(String ma, String ten, double gia) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public double getGia() {
        return gia;
    }

    @Override
    public String toString() {
        return ma + " - " + ten + " - " + gia;
    }
}
