package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ItemDataActivity extends AppCompatActivity {

    private TextView USER;
    private TextView ITEM;
    private TextView ADDRESS;
    private TextView DATE;
    private TextView PHONE;

    private Button edit;
    private Button delete;

    String id;
    String USERNAME;

    int ItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);

        USER = (TextView)findViewById(R.id.textView3);
        ITEM = (TextView)findViewById(R.id.textView5);
        ADDRESS = (TextView)findViewById(R.id.textView7);
        DATE = (TextView)findViewById(R.id.textView9);
        PHONE = (TextView)findViewById(R.id.textView11);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            id = params.getString("ID");
            USERNAME = params.getString("USERNAME");
            if (id != null) {
                //User_DB UserData = new User_DB(this);
                //ItemData = UserData.SearchUserLostThingDataItem(Integer.valueOf(id));
                ItemID = Integer.valueOf(id);
                User_DB UserData = new User_DB(this);
                LostThingDataModel Data = UserData.SearchUserLostThingDataItem(ItemID);
                USER.setText(Data.Owner_of_lost_property);
                ITEM.setText(Data.name);
                ADDRESS.setText(Data.address);
                DATE.setText(Data.date);
                PHONE.setText(Data.phone);
            }
        }

        edit = (Button)findViewById(R.id.button1);
        edit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(ItemDataActivity.this, ItemDataEditActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                Intent.putExtra("ID", id);
                startActivity(Intent);
                ItemDataActivity.this.finish();
            }
        });

        delete = (Button)findViewById(R.id.button2);
        delete.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(ItemDataActivity.this, LostThingActivity.class);
                User_DB UserData = new User_DB(ItemDataActivity.this);
                UserData.DeleteLostThing(ItemID);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                ItemDataActivity.this.finish();
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent Intent = new Intent();
            Intent.setClass(ItemDataActivity.this, LostThingActivity.class);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            ItemDataActivity.this.finish();
        }
        return false;
    }
}
