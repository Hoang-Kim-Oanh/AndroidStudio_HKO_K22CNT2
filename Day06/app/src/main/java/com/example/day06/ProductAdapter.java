package com.example.day06;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Product> productList;

    public ProductAdapter(Context context, ArrayList<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }

        Product product = productList.get(position);

        TextView textViewName = convertView.findViewById(R.id.product_name);
        TextView textViewPrice = convertView.findViewById(R.id.product_price);
        ImageView imageView = convertView.findViewById(R.id.product_image);

        textViewName.setText(product.getName());
        textViewPrice.setText(String.valueOf(product.getPrice()));
        imageView.setImageResource(product.getImg());

        return convertView;
    }
}
