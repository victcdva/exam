package com.example.exam;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private ArrayList<OperatingSystem> data;
    private Activity activity;
    private LayoutInflater inflater;

    @Override
    public int getCount() { return this.data.size(); }
    @Override
    public Object getItem(int position) { return position; }
    @Override
    public long getItemId(int position) { return position; }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //view
        View view = convertView;
        //layout for each item in the ListView
        if (view == null) view = inflater.inflate(R.layout.item_layout, null);
        //get controls from item_layout
        TextView tvDescription = view.findViewById(R.id.tvDescription);
        ImageView imageIcon = view.findViewById(R.id.imageIcon);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvLotSize = view.findViewById(R.id.tvLotSize);
        TextView tvConstruction = view.findViewById(R.id.tvConstruction);
        TextView tvBedrooms = view.findViewById(R.id.tvBedrooms);
        TextView tvBathrooms = view.findViewById(R.id.tvBathrooms);
        TextView tvPrice =  view.findViewById(R.id.tvPrice);
        //get item from ArrayList
        OperatingSystem os = this.data.get(position);
        //bind controls to getters
        tvDescription.setText(os.get_description());
        imageIcon.setImageDrawable(os.get_image());
        tvName.setText(os.get_name());
        tvLotSize.setText("Lot Size: " + os.get_lotSize());
        tvConstruction.setText("Construction: " + os.get_constructionSize());
        tvBedrooms.setText("Bedrooms: " + os.get_bedrooms());
        tvBathrooms.setText("Bathrooms: " + os.get_bathrooms());
        tvPrice.setText("Price: $" + os.get_price());
        //return view
        return view;
    }

    //constructor
    public ListAdapter(ArrayList<OperatingSystem> data, Activity activity) {
        this.data = data;
        this.activity = activity;
        this.inflater = (LayoutInflater)this.activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

}
