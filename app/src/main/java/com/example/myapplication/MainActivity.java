package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    private Button submit;
    private Button register;
    private EditText UserInput;
    private EditText UserPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        UserInput = (EditText)findViewById(R.id.editText2);
        UserPassword = (EditText)findViewById(R.id.editText);
        submit = (Button)findViewById(R.id.button);
        submit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
        register = (Button)findViewById(R.id.button2);
        register.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent();
                Intent.setClass(MainActivity.this, RegisteredActivity.class);
                startActivity(Intent);
            }
        });
    }

    private void attemptLogin() {

        // Reset errors.
        UserInput.setError(null);;
        UserPassword.setError(null);

        // Store values at the time of the login attempt.
        String INPUT = UserInput.getText().toString();
        String Password = UserPassword.getText().toString();

        UserInput.setText("");
        UserPassword.setText("");

        // Check for a valid email address.
        if (TextUtils.isEmpty(INPUT)) {
            UserInput.setError(getString(R.string.error_field_required));
        }
        else if(TextUtils.isEmpty(Password))
        {
            UserPassword.setError(getString(R.string.error_field_required));
        }
        else
        {
            User_DB UserData = new User_DB(this);
            int CheckResult = UserData.UserCheck(INPUT,Password);
            if(CheckResult == 1)
            {
                Intent Intent = new Intent();
                Intent.setClass(MainActivity.this, SystemIndexActivity.class);
                Intent.putExtra("USERNAME", INPUT);
                startActivity(Intent);
                MainActivity.this.finish();
            }
            else
            {
                Toast t = Toast.makeText(MainActivity.this, "帳號或密碼錯誤！", Toast.LENGTH_SHORT);
                t.show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
