package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ItemDataEditActivity extends AppCompatActivity {

    Calendar c = Calendar.getInstance();

    String ITEM;
    String ADDRESS;
    String DATE;
    String PHONE;

    // UI references.
    private EditText WhatLose;
    private EditText WhereLose;
    private EditText WhenLose;
    private EditText Connection;

    private Button submit;
    private Button cancel;

    String id;
    String USERNAME;

    int ItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data_edit);

        // Set up the login form.
        WhatLose = (EditText) findViewById(R.id.WhatLose);
        WhereLose = (EditText) findViewById(R.id.WhereLose);
        WhenLose = (EditText) findViewById(R.id.WhenLose);
        Connection = (EditText) findViewById(R.id.Connection);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            id = params.getString("ID");
            USERNAME = params.getString("USERNAME");
            ItemID = Integer.valueOf(id);
            User_DB UserData = new User_DB(this);
            LostThingDataModel Data = UserData.SearchUserLostThingDataItem(ItemID);

            WhatLose.setText(Data.name);
            WhereLose.setText(Data.address);
            WhenLose.setText(Data.date);
            Connection.setText(Data.phone);
        }

        submit = (Button)findViewById(R.id.button1);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }
    private void attemptLogin() {

        // Reset errors.
        WhatLose.setError(null);
        WhereLose.setError(null);
        WhenLose.setError(null);
        Connection.setError(null);

        // Store values at the time of the login attempt.
        ITEM = WhatLose.getText().toString();
        ADDRESS = WhereLose.getText().toString();
        DATE = WhenLose.getText().toString();
        PHONE = Connection.getText().toString();

        int count = 0;

        // Check for a valid password, if the user entered one.

        // Check for a valid email address.
        if (TextUtils.isEmpty(ITEM)) {
            WhatLose.setError(getString(R.string.error_field_required));
        }
        else
        {
            count++;
        }

        if (TextUtils.isEmpty(ADDRESS)) {
            WhereLose.setError(getString(R.string.error_field_required));
        }
        else
        {
            count++;
        }

        if (TextUtils.isEmpty(DATE)) {
            WhenLose.setError(getString(R.string.error_field_required));
        }
        else
        {
            String[] DateData = DATE.split("/");
            if(DateData.length == 3)
            {
                int month = Integer.valueOf(DateData[0]);
                int day = Integer.valueOf(DateData[1]);
                int year = Integer.valueOf(DateData[2]);

                int NowYear = c.get(Calendar.YEAR);
                int NowMonth = c.get(Calendar.MONTH) + 1;           //取出月，月份的編號是由0~11 故+1
                int NowDay = c.get(Calendar.DAY_OF_MONTH);        //取出日

                if(year >  NowYear)
                {
                    WhenLose.setError("資料輸入錯誤");
                }
                else
                {
                    if(year == NowYear && month > NowMonth)
                    {
                        WhenLose.setError("資料輸入錯誤");
                    }
                    else
                    {
                        if(year == NowYear && month == NowMonth && day >  NowDay)
                        {
                            WhenLose.setError("資料輸入錯誤");
                        }
                        else
                        {
                            if(month > 0 && month <= 12)
                            {
                                if(month == 2)
                                {
                                    if(year%4 == 0)
                                    {
                                        if(year%100 == 0)
                                        {
                                            if(year%400 == 0)
                                            {
                                                if(day < 0 || day > 29)
                                                {
                                                    WhenLose.setError("資料輸入錯誤");
                                                }
                                                else
                                                {
                                                    count++;
                                                }
                                            }
                                            else
                                            {
                                                if(day < 0 || day > 28)
                                                {
                                                    WhenLose.setError("資料輸入錯誤");
                                                }
                                                else
                                                {
                                                    count++;
                                                }
                                            }
                                        }
                                        else
                                        {
                                            if(day < 0 || day > 29)
                                            {
                                                WhenLose.setError("資料輸入錯誤");
                                            }
                                            else
                                            {
                                                count++;
                                            }
                                        }
                                    }
                                    else
                                    {
                                        if(day < 0 || day > 28)
                                        {
                                            WhenLose.setError("資料輸入錯誤");
                                        }
                                        else
                                        {
                                            count++;
                                        }
                                    }
                                }
                                else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                                {
                                    if(day < 0 || day > 31)
                                    {
                                        WhenLose.setError("資料輸入錯誤");
                                    }
                                    else
                                    {
                                        count++;
                                    }
                                }
                                else
                                {
                                    if(day < 0 || day > 30)
                                    {
                                        WhenLose.setError("資料輸入錯誤");
                                    }
                                    else
                                    {
                                        count++;
                                    }
                                }
                            }
                            else
                            {
                                WhenLose.setError("資料輸入錯誤");
                            }
                        }
                    }
                }
            }
            else
            {
                WhenLose.setError("資料輸入錯誤");
            }
        }

        if (TextUtils.isEmpty(PHONE)) {
            Connection.setError(getString(R.string.error_field_required));
        }
        else
        {
            count++;
        }

        if(count == 4)
        {
            Intent Intent = new Intent();
            Intent.setClass(ItemDataEditActivity.this, LostThingActivity.class);
            User_DB UserData = new User_DB(ItemDataEditActivity.this);
            UserData.EditLostThing(ItemID,USERNAME,ITEM,ADDRESS,DATE,PHONE);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            ItemDataEditActivity.this.finish();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent Intent = new Intent();
            Intent.setClass(ItemDataEditActivity.this, ItemDataActivity.class);
            Intent.putExtra("ID", id);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            ItemDataEditActivity.this.finish();
        }
        return false;
    }
}
