package com.example.myapplication;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered);

        submit = (Button)findViewById(R.id.button1);
        returnMain = (Button)findViewById(R.id.button2);
        UserInput = (EditText)findViewById(R.id.editText1);
        UserPassword = (EditText)findViewById(R.id.editText2);

        submit.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Input = UserInput.getText().toString();
                String Password = UserPassword.getText().toString();

                UserInput.setText("");
                UserPassword.setText("");

                if (TextUtils.isEmpty(Input)) {
                    UserInput.setError(getString(R.string.error_field_required));
                }
                else if(TextUtils.isEmpty(Password))
                {
                    UserPassword.setError(getString(R.string.error_field_required));
                }
                else
                {
                    User_DB UserData = new User_DB(RegisteredActivity.this);
                    int SearchResult = UserData.SearchUser(Input);
                    if(SearchResult == 1)
                    {
                        Toast t = Toast.makeText(RegisteredActivity.this, "帳號重複！", Toast.LENGTH_SHORT);
                        t.show();
                    }
                    else
                    {
                        UserData.AddUser(Input, Password);
                        Intent Intent = new Intent();
                        Intent.setClass(RegisteredActivity.this, MainActivity.class);
                        Intent.putExtra("USERNAME", Input);
                        Intent.putExtra("USERPASSWORD", Password);
                        startActivity(Intent);
                        RegisteredActivity.this.finish();
                    }
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
                RegisteredActivity.this.finish();
            }
        });
    }
}
