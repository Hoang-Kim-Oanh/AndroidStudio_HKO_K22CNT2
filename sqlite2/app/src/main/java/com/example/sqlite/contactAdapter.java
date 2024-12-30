package com.example.sqlite;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class contactAdapter  extends ArrayAdapter<Contact> {
    Activity context;
    int resource;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView=inflater.inflate(resource, null);
        TextView txtMa, txtTen,txtDT;
        txtMa=customView.findViewById(R.id.txtMa);
        txtTen=customView.findViewById(R.id.txtTen);
        txtDT=customView.findViewById(R.id.txtDT);
        Contact ct=getItem(position);
        txtMa.setText(ct.getMa()+" ");
        txtTen.setText(ct.getTen());
        txtDT.setText(ct.getDienthoai());
        return customView;
    }

    public contactAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource= resource;
    }
}

