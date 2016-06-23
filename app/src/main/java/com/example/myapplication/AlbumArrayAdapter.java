package com.example.myapplication;

/**
 * Created by 歐歐 on 2016/5/10.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AlbumArrayAdapter extends ArrayAdapter<LostThingDataModel> {
    Context context;

    public AlbumArrayAdapter(Context context, ArrayList<LostThingDataModel> items) {
        super(context, 0, items);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);

        LinearLayout itemlayout = null;
        if(convertView == null) {
            itemlayout = (LinearLayout)inflater.inflate(R.layout.listitem, null);
        } else {
            itemlayout = (LinearLayout) convertView;
        }

        LostThingDataModel item = (LostThingDataModel)getItem(position);

        TextView tv_name = (TextView)itemlayout.findViewById(R.id.itemtv);
        tv_name.setText(item.name);

        ImageView iv = (ImageView)itemlayout.findViewById(R.id.itemiv);
        iv.setImageResource(item.imgid);

        TextView tv_point = (TextView)itemlayout.findViewById(R.id.pointView);
        tv_point.setText(item.date);

        return itemlayout;
    }
}
