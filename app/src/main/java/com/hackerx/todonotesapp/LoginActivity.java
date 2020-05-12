package com.hackerx.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText editTextfullname, editTextusername;
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextfullname = findViewById(R.id.edittextfullname);
        editTextusername = findViewById(R.id.edittextusername);
        loginbtn = findViewById(R.id.login_btn);


        View.OnClickListener loginclicklistener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = editTextfullname.getText().toString();
                String username = editTextusername.getText().toString();
                if (!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(username)) {
                    Intent mainactivitiyintent = new Intent(LoginActivity.this, MainActivity.class);
                    mainactivitiyintent.putExtra("full_name", fullname);
                    startActivity(mainactivitiyintent);
                }else{
                    Toast.makeText(LoginActivity.this,"Text Fields cannot be Empty",Toast.LENGTH_SHORT);
                }
            }
        };


           loginbtn.setOnClickListener(loginclicklistener);

    }
}