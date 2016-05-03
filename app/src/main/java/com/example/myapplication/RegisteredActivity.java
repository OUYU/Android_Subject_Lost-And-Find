package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisteredActivity extends AppCompatActivity {

    private Button returnMain;
    private Button submit;
    private EditText UserInput;
    private EditText UserPassword;
    private EditText UserPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        submit = (Button)findViewById(R.id.button1);
        returnMain = (Button)findViewById(R.id.button2);
        UserInput = (EditText)findViewById(R.id.editText1);
        UserPassword = (EditText)findViewById(R.id.editText2);
        UserPhone = (EditText)findViewById(R.id.editText3);

        submit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Input = UserInput.getText().toString();
                String Password = UserPassword.getText().toString();
                String phone = UserPhone.getText().toString();
                if(Input != null && Password != null && phone != null) {
                    UserInput.setText("");
                    UserPassword.setText("");
                    UserPhone.setText("");

                    Intent Intent = new Intent();
                    Intent.setClass(RegisteredActivity.this, MainActivity.class);
                    Intent.putExtra("USERNAME", Input);
                    Intent.putExtra("USERPASSWORD", Password);
                    Intent.putExtra("USERPHONE", phone);
                    startActivity(Intent);
                    finish();
                }
                else
                {
                    Toast t = Toast.makeText(RegisteredActivity.this, "註冊失敗！", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        returnMain.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent Intent = new Intent();
                Intent.setClass(RegisteredActivity.this,MainActivity.class);
                startActivity(Intent);
                finish();
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

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
                    Toast t = Toast.makeText(RegisteredActivity.this,"Hellow",Toast.LENGTH_SHORT);
                    t.show();
                }
            };
            AuthorAbout.setPositiveButton("我了解了", AboutListener);
            AuthorAbout.show();
        }

        return super.onOptionsItemSelected(item);
    }
}
