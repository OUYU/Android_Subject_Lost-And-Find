package com.example.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LostThingDatainputActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    Calendar c = Calendar.getInstance();

    // UI references.
    private EditText WhoLose;
    private EditText WhatLose;
    private EditText WhereLose;
    private EditText WhenLose;
    private EditText Connection;

    // button
    private Button submit;
    private Button dateSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_thing_datainput);

        // Set up the login form.
        WhoLose = (EditText) findViewById(R.id.WhoLose);
        WhatLose = (EditText) findViewById(R.id.WhatLose);
        WhereLose = (EditText) findViewById(R.id.WhereLose);
        WhenLose = (EditText) findViewById(R.id.WhenLose);
        Connection = (EditText) findViewById(R.id.Connection);

        submit = (Button)findViewById(R.id.LostThingDataSubmit);
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

                    WhenLose.setText(cmonth + "/" + cday + "/" + cyear);
                }
            };

            @Override
            public void onClick(View view) {
                new DatePickerDialog(LostThingDatainputActivity.this, d, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
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
        WhoLose.setError(null);
        WhatLose.setError(null);
        WhereLose.setError(null);
        WhenLose.setError(null);
        Connection.setError(null);

        // Store values at the time of the login attempt.
        String USER = WhoLose.getText().toString();
        String ITEM = WhatLose.getText().toString();
        String ADDRESS = WhereLose.getText().toString();
        String DATE = WhenLose.getText().toString();
        String PHONE = Connection.getText().toString();

        // Check for a valid password, if the user entered one.

        // Check for a valid email address.
        if (TextUtils.isEmpty(USER)) {
            WhoLose.setError(getString(R.string.error_field_required));
        }

        if (TextUtils.isEmpty(ITEM)) {
            WhatLose.setError(getString(R.string.error_field_required));
        }

        if (TextUtils.isEmpty(ADDRESS)) {
            WhereLose.setError(getString(R.string.error_field_required));
        }

        if (TextUtils.isEmpty(DATE)) {
            WhenLose.setError(getString(R.string.error_field_required));
        }

        if (TextUtils.isEmpty(PHONE)) {
            Connection.setError(getString(R.string.error_field_required));
        }
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

