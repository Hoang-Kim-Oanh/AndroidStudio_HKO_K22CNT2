package com.example.adapter;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.model.SanPham;
import com.example.lab051.R;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Activity context;
    private int resource;

    public SanPhamAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View customView=inflater.inflate(this.resource,null);
        ImageView imgHinh=customView.findViewById(R.id.imgHinh);
        TextView txtmaSP=customView.findViewById(R.id.txtmaSP);
        TextView txtTenSP=customView.findViewById(R.id.txtTenSP);
        TextView txtGia=customView.findViewById(R.id.txtGia);
        SanPham sp=getItem(position);
        imgHinh.setImageResource(sp.getHinh());
        txtmaSP.setText(sp.getMasp()+"");
        txtTenSP.setText(sp.getTensp());
        txtGia.setText(sp.getGia()+"VND");
        return customView;





    }
}