package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class NewLostActivity extends Fragment {

    ListView lv;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_new_lost, container, false);

        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final LostThingMenuActivity mainActivity = (LostThingMenuActivity)getActivity();
        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        LostList = mainActivity.GetLostThingData();

        final AlbumArrayAdapter adapter = new AlbumArrayAdapter(mainActivity, LostList);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Intent = new Intent();
                Intent.setClass(mainActivity, ItemMenuDataActivity.class);
                LostThingDataModel Data = adapter.getItem(position);
                Intent.putExtra("ID", String.valueOf(Data.id));
                startActivity(Intent);
            }
        };
        lv = (ListView) view.findViewById(R.id.lv);
        lv.setOnItemClickListener(itemListener);
        lv.setAdapter(adapter);
    }
}

