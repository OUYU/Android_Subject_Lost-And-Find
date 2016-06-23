package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PickedThingActivity extends AppCompatActivity {

    private Button NewPickedThing;

    PickedThingDataModel[] data = new PickedThingDataModel[5];

    ListView lv;

    String USERNAME;
    ArrayList<LostThingDataModel> PickedList = new ArrayList<LostThingDataModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_thing);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            USERNAME = params.getString("USERNAME");
        }

        NewPickedThing = (Button)findViewById(R.id.NEW);
        NewPickedThing.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent();
                Intent.setClass(PickedThingActivity.this, PickedThingDatainputActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                PickedThingActivity.this.finish();
            }
        });

        User_DB UserData = new User_DB(this);
        PickedList = UserData.SearchUserPickedThingData(USERNAME);
        final AlbumArrayAdapter adapter = new AlbumArrayAdapter(this, PickedList);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent Intent = new Intent();
                Intent.setClass(PickedThingActivity.this, ItemDataForPickedActivity.class);
                LostThingDataModel Data = adapter.getItem(position);
                Intent.putExtra("USERNAME", USERNAME);
                Intent.putExtra("ID", String.valueOf(Data.id));
                startActivity(Intent);
                PickedThingActivity.this.finish();
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
            Intent.setClass(PickedThingActivity.this, SystemIndexActivity.class);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            PickedThingActivity.this.finish();
        }
        return false;
    }
}
