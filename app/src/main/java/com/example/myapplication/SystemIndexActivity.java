package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SystemIndexActivity extends AppCompatActivity {

    private Button LostItem;
    private Button PickUpItem;
    private Button WhoLose;
    private Button WhoPickUp;
    private Button Pair;
    private Button Recording;

    String USERNAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_index);

        Bundle params = getIntent().getExtras();
        if (params != null) {
            USERNAME = params.getString("USERNAME");
            if (USERNAME != null) {
                Toast t = Toast.makeText(SystemIndexActivity.this, USERNAME + "，Hello！", Toast.LENGTH_SHORT);
                t.show();
            }
        }

        LostItem = (Button)findViewById(R.id.button1);
        LostItem.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(SystemIndexActivity.this, LostThingActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                SystemIndexActivity.this.finish();
            }
        });

        PickUpItem = (Button)findViewById(R.id.button2);
        PickUpItem.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(SystemIndexActivity.this, PickedThingActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                SystemIndexActivity.this.finish();
            }
        });
        WhoLose = (Button)findViewById(R.id.button3);
        WhoLose.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(SystemIndexActivity.this,LostThingMenuActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                SystemIndexActivity.this.finish();
            }
        });

        WhoPickUp = (Button)findViewById(R.id.button4);
        WhoPickUp.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(SystemIndexActivity.this,PickedThingMenuActivity.class);
                Intent.putExtra("USERNAME", USERNAME);
                startActivity(Intent);
                SystemIndexActivity.this.finish();
            }
        });

    }

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
        if(id == R.id.action_Index)
        {
            Intent Intent = new Intent();
            Intent.setClass(this, MainActivity.class);
            startActivity(Intent);
        }
        else if (id == R.id.action_settings) {
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
                    Toast t = Toast.makeText(SystemIndexActivity.this,"Hellow",Toast.LENGTH_SHORT);
                    t.show();
                }
            };
            AuthorAbout.setPositiveButton("我了解了", AboutListener);
            AuthorAbout.show();
        }
        else if (id == R.id.action_reset)
        {

        }
        return super.onOptionsItemSelected(item);
    }
}
