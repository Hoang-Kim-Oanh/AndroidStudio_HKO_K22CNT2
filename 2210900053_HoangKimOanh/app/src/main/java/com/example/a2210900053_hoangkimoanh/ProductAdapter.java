package com.example.a2210900053_hoangkimoanh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private Context context;
    private List<Product> productList;
    private OnProductActionListener listener;

    // Interface để lắng nghe sự kiện sửa và xóa
    public interface OnProductActionListener {
        void onEdit(Product product);
        void onDelete(Product product);
    }

    public ProductAdapter(Context context, List<Product> productList, OnProductActionListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.item_product, parent, false);
        }

        Product product = productList.get(position);
        TextView nameText = convertView.findViewById(R.id.txtTenSP53);
        TextView priceText = convertView.findViewById(R.id.txtDonGia53);

        nameText.setText(product.getTenSP());
        priceText.setText(String.format("%s VND", product.getThanhTien()));

        Button btnEdit = convertView.findViewById(R.id.btnEditProduct53);
        Button btnDelete = convertView.findViewById(R.id.btnDeleteProduct53);

        // Xử lý sự kiện nút sửa
        btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEdit(product);
            }
        });

        // Xử lý sự kiện nút xóa
        btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDelete(product);
            }
        });

        return convertView;
    }
}
