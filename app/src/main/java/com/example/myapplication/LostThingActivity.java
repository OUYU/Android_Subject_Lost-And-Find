package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LostThingActivity extends AppCompatActivity {

    private Button NewLostThing;

    ListView lv;

    String USERNAME;
    ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_thing);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            USERNAME = params.getString("USERNAME");
        }

        NewLostThing = (Button)findViewById(R.id.NEW);
        NewLostThing.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(LostThingActivity.this, LostThingDatainputActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                LostThingActivity.this.finish();
            }
        });

        User_DB UserData = new User_DB(this);
        LostList = UserData.SearchUserLostThingData(USERNAME);
        final AlbumArrayAdapter adapter = new AlbumArrayAdapter(this, LostList);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Intent = new Intent();
                Intent.setClass(LostThingActivity.this, ItemDataActivity.class);
                LostThingDataModel Data = adapter.getItem(position);
                Intent.putExtra("USERNAME", USERNAME);
                Intent.putExtra("ID", String.valueOf(Data.id));
                startActivity(Intent);
                LostThingActivity.this.finish();
            }
        };
        lv = (ListView)findViewById(R.id.lv);
        lv.setOnItemClickListener(itemListener);
        lv.setAdapter(adapter);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent Intent = new Intent();
            Intent.setClass(LostThingActivity.this, SystemIndexActivity.class);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            LostThingActivity.this.finish();
        }
        return false;
    }
}
