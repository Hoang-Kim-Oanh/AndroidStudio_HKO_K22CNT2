package com.example.tmd_baitap;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Item> itemList;

    public CustomAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        }

        Item item = itemList.get(position);

        TextView itemName = convertView.findViewById(R.id.itemName);
        TextView itemPrice = convertView.findViewById(R.id.itemPrice);
        Button btnEdit = convertView.findViewById(R.id.btnEdit);
        Button btnDelete = convertView.findViewById(R.id.btnDelete);

        itemName.setText(item.getName());
        itemPrice.setText(String.format("Giá: %.2f", item.getPrice()));

        // Xử lý nút Sửa
        btnEdit.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Sửa Bánh");

            View dialogView = LayoutInflater.from(context).inflate(R.layout.edit_item_dialog, null);
            builder.setView(dialogView);

            EditText edtName = dialogView.findViewById(R.id.edtName);
            EditText edtPrice = dialogView.findViewById(R.id.edtPrice);

            edtName.setText(item.getName());
            edtPrice.setText(String.valueOf(item.getPrice()));

            builder.setPositiveButton("Lưu", (dialog, which) -> {
                item.setName(edtName.getText().toString());
                item.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                notifyDataSetChanged();
            });

            builder.setNegativeButton("Hủy", null);
            builder.show();
        });

        btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xóa Bánh");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");

            builder.setPositiveButton("Xóa", (dialog, which) -> {
                itemList.remove(position);
                notifyDataSetChanged();
            });

            builder.setNegativeButton("Hủy", null);
            builder.show();
        });

        return convertView;
    }
}

