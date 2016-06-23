package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;


/**
 * A login screen that offers login via email/password.
 */
public class PickedThingDatainputActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    Calendar c = Calendar.getInstance();

    String ITEM;
    String ADDRESS;
    String DATE;
    String PHONE;

    // UI references.
    private EditText WhoPicked;
    private EditText WhatPicked;
    private EditText WherePicked;
    private EditText WhenPicked;
    private EditText Connection;

    String USERNAME;

    // button
    private Button submit;
    private Button dateSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picked_thing_datainput);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            USERNAME = params.getString("USERNAME");
        }

        // Set up the login form.
        WhatPicked = (EditText) findViewById(R.id.WhatPicked);
        WherePicked = (EditText) findViewById(R.id.WherePicked);
        WhenPicked = (EditText) findViewById(R.id.WhenPicked);
        Connection = (EditText) findViewById(R.id.Connection);

        submit = (Button)findViewById(R.id.PickedThingDataSubmit);
        submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        dateSelect = (Button)findViewById(R.id.DateSelect);
        dateSelect.setOnClickListener(new OnClickListener() {

            DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {

                    int cday = dayOfMonth;
                    int cmonth = monthOfYear + 1;
                    int cyear = year;

                    WhenPicked.setText(cmonth + "/" + cday + "/" + cyear);
                }
            };

            @Override
            public void onClick(View view) {
                new DatePickerDialog(PickedThingDatainputActivity.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        WhatPicked.setError(null);
        WherePicked.setError(null);
        WhenPicked.setError(null);
        Connection.setError(null);

        // Store values at the time of the login attempt.
        ITEM = WhatPicked.getText().toString();
        ADDRESS = WherePicked.getText().toString();
        DATE = WhenPicked.getText().toString();
        PHONE = Connection.getText().toString();

        int count = 0;

        // Check for a valid password, if the user entered one.

        // Check for a valid email address.
        if (TextUtils.isEmpty(ITEM)) {
            WhatPicked.setError(getString(R.string.error_field_required));
        }
        else
        {
            count++;
        }

        if (TextUtils.isEmpty(ADDRESS)) {
            WherePicked.setError(getString(R.string.error_field_required));
        }
        else
        {
            count++;
        }

        if (TextUtils.isEmpty(DATE)) {
            WhenPicked.setError(getString(R.string.error_field_required));
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
                    WhenPicked.setError("資料輸入錯誤");
                }
                else
                {
                    if(year == NowYear && month > NowMonth)
                    {
                        WhenPicked.setError("資料輸入錯誤");
                    }
                    else
                    {
                        if(year == NowYear && month == NowMonth && day >  NowDay)
                        {
                            WhenPicked.setError("資料輸入錯誤");
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
                                                    WhenPicked.setError("資料輸入錯誤");
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
                                                    WhenPicked.setError("資料輸入錯誤");
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
                                                WhenPicked.setError("資料輸入錯誤");
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
                                            WhenPicked.setError("資料輸入錯誤");
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
                                        WhenPicked.setError("資料輸入錯誤");
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
                                        WhenPicked.setError("資料輸入錯誤");
                                    }
                                    else
                                    {
                                        count++;
                                    }
                                }
                            }
                            else
                            {
                                WhenPicked.setError("資料輸入錯誤");
                            }
                        }
                    }
                }
            }
            else
            {
                WhenPicked.setError("資料輸入錯誤");
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
            Intent.setClass(PickedThingDatainputActivity.this, PickedThingActivity.class);
            User_DB UserData = new User_DB(PickedThingDatainputActivity.this);
            UserData.AddPickedThing(USERNAME, ITEM, ADDRESS, DATE, PHONE);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            PickedThingDatainputActivity.this.finish();
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent Intent = new Intent();
            Intent.setClass(PickedThingDatainputActivity.this, PickedThingActivity.class);
            Intent.putExtra("USERNAME", USERNAME);
            startActivity(Intent);
            PickedThingDatainputActivity.this.finish();
        }
        return false;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

