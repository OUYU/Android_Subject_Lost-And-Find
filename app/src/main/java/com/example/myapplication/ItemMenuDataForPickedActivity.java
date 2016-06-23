package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TextView;

public class ItemMenuDataForPickedActivity extends AppCompatActivity {

    private TextView USER;
    private TextView ITEM;
    private TextView ADDRESS;
    private TextView DATE;
    private TextView PHONE;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_menu_data_for_picked);

        USER = (TextView)findViewById(R.id.textView3);
        ITEM = (TextView)findViewById(R.id.textView5);
        ADDRESS = (TextView)findViewById(R.id.textView7);
        DATE = (TextView)findViewById(R.id.textView9);
        PHONE = (TextView)findViewById(R.id.textView11);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            id = params.getString("ID");
            if (id != null) {
                //User_DB UserData = new User_DB(this);
                //ItemData = UserData.SearchUserLostThingDataItem(Integer.valueOf(id));
                int ItemID = Integer.valueOf(id);
                User_DB UserData = new User_DB(this);
                LostThingDataModel Data = UserData.SearchUserPickedThingDataItem(ItemID);

                USER.setText(Data.Owner_of_lost_property);
                ITEM.setText(Data.name);
                ADDRESS.setText(Data.address);
                DATE.setText(Data.date);
                PHONE.setText(Data.phone);
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            ItemMenuDataForPickedActivity.this.finish();
        }
        return false;
    }
}
