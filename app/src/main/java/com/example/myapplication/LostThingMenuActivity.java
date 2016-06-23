package com.example.myapplication;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LostThingMenuActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    private EditText SelectData;

    private Button Select;

    String SELECTDATA;
    String USERNAME;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lost_thing_menu);

            Bundle params = getIntent().getExtras();
            if (params != null) {
                USERNAME = params.getString("USERNAME");
            }

            SelectData = (EditText) findViewById(R.id.SelectData);

            Select = (Button)findViewById(R.id.Select);
            Select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SelectData.setError(null);

                    // Store values at the time of the login attempt.
                    SELECTDATA = SelectData.getText().toString();

                    Intent Intent = new Intent();
                    Intent.setClass(LostThingMenuActivity.this, SelectResultForLostThingActivity.class);
                    Intent.putExtra("USERNAME", USERNAME);
                    Intent.putExtra("SELECTDATA", SELECTDATA);
                    startActivity(Intent);
                    LostThingMenuActivity.this.finish();
                }
            });


            //透過 id 取得 Layout 上的 FragmentTabHost 物件
            mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);

            //初始化 FragmentTabHost，第二個參數中為需要從系統取得 Fragment 的管理員。
            //第三個參數為要用來顯示 Fragment 的 FrameLayout 。 (Fragment 都會使用 FrameLayout 來掛載。)
            //第一個參數為所在的 Activity ，因為自己(MainActivity)就是主畫面，因此直接放入 this 即可。
            mTabHost.setup(this, getSupportFragmentManager(), R.id.container);

            //加入分頁 apple 到 TabHost 中
            mTabHost.addTab(mTabHost.newTabSpec("最新遺失").setIndicator("最新遺失"), NewLostActivity.class, null);

        }

    public ArrayList<LostThingDataModel> GetLostThingData()
    {
        User_DB UserData = new User_DB(this);
        ArrayList<LostThingDataModel> LostList = new ArrayList<LostThingDataModel>();
        LostList = UserData.GetLostThingData();

        return LostList;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent Intent = new Intent();
            Intent.setClass(LostThingMenuActivity.this, SystemIndexActivity.class);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            LostThingMenuActivity.this.finish();
        }
        return false;
    }
}