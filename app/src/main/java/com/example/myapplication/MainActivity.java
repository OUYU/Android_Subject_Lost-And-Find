package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

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
        submit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Input = UserInput.getText().toString();
                String Password = UserPassword.getText().toString();

                UserInput.setText("");
                UserPassword.setText("");

                if(Password.compareTo("985247") == 0)
                {
                    Intent Intent = new Intent();
                    Intent.setClass(MainActivity.this, SystemIndexActivity.class);
                    Intent.putExtra("USERNAME", Input);
                    startActivity(Intent);
                }
                else
                {
                    Toast t = Toast.makeText(MainActivity.this,"帳號或密碼錯誤！",Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
        register = (Button)findViewById(R.id.button2);
        register.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(MainActivity.this, RegisteredActivity.class);
                startActivity(Intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_about)
        {
            AlertDialog.Builder AuthorAbout = new AlertDialog.Builder(this);

            AuthorAbout.setTitle("關於本系統作者");
            AuthorAbout.setMessage("管理員  歐晉佑");

            DialogInterface.OnClickListener AboutListener = new DialogInterface.OnClickListener()
            {
                public void onClick(DialogInterface di,int i)
                {
                    Toast t = Toast.makeText(MainActivity.this,"Hellow",Toast.LENGTH_SHORT);
                    t.show();
                }
            };
            AuthorAbout.setPositiveButton("我了解了", AboutListener);
            AuthorAbout.show();
        }
        else if (id == R.id.action_reset)
        {
            UserInput.setText("");
            UserPassword.setText("");
        }

        return super.onOptionsItemSelected(item);
    }


}
