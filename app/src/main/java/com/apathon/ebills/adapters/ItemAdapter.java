package com.apathon.ebills.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apathon.ebills.R;
import com.apathon.ebills.models.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends BaseAdapter {

    private List<Item> objects = new ArrayList<Item>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ItemAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }
    public ItemAdapter(Context context,List<Item> objects) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.objects=objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Item getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.layout_item_row, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.item = (TextView) convertView.findViewById(R.id.item);

            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(Item object, ViewHolder holder) {
        //TODO implement
    }

    protected class ViewHolder {
        private TextView item;
    }
}
