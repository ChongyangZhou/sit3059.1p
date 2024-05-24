package com.example.task91p;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LostItemsAdapter extends BaseAdapter {
    private Context context;
    private List<LostBean> lostItems;

    public LostItemsAdapter(Context context, List<LostBean> lostItems) {
        this.context = context;
        this.lostItems = lostItems;
    }

    @Override
    public int getCount() {
        return lostItems.size();
    }

    @Override
    public Object getItem(int position) {
        return lostItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lostItems.get(position).getId();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }

        LostBean lostBean = lostItems.get(position);

        TextView typeTextView = convertView.findViewById(R.id.type);
        TextView nameTextView = convertView.findViewById(R.id.name);
        TextView phoneTextView = convertView.findViewById(R.id.phone);
        TextView descriptionTextView = convertView.findViewById(R.id.description);
        TextView dateTextView = convertView.findViewById(R.id.date);
        TextView addressTextView = convertView.findViewById(R.id.address);

        typeTextView.setText(lostBean.getType());
        nameTextView.setText(lostBean.getName());
        phoneTextView.setText(lostBean.getPhone());
        descriptionTextView.setText(lostBean.getDescription());
        dateTextView.setText(lostBean.getDate());
        addressTextView.setText(lostBean.getAddress());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                LostBean item = lostItems.get(position);
                intent.putExtra("id", item.getId());
                intent.putExtra("type", item.getType());
                intent.putExtra("name", item.getName());
                intent.putExtra("phone", item.getPhone());
                intent.putExtra("description", item.getDescription());
                intent.putExtra("date", item.getDate());
                intent.putExtra("address", item.getAddress());
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
