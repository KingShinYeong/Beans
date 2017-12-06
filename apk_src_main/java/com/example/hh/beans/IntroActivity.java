package com.example.hh.beans;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by HH on 2017-11-21.
 */

public class IntroActivity extends AppCompatActivity{

    EditText edit_phoneNum;
    EditText edit_age;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        edit_phoneNum = (EditText) findViewById(R.id.intro_editText);
        edit_age = (EditText) findViewById(R.id.intro_age);


    }

    public void Intro_btn(View v){
        String phoneNum = edit_phoneNum.getText().toString();
        String age = edit_age.getText().toString();

        if(phoneNum.equals("") || age.equals("")){
            setPopup();
        }
        else {
            AssetManager am = getResources().getAssets();

            User user;

            Info info = new Info(am, "userDB.json");
            user = info.readUserInfoWith(edit_phoneNum.getText().toString());
            if (user != null) {
                intent = new Intent(this, SubActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("CurrentUser", user);
                intent.putExtras(bundle);

                startActivity(intent);

            } else {
                user = new User(phoneNum, Integer.parseInt(age));
                intent = new Intent(this, ChooseImageActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("NewUser", user);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        }
    }
    public void setPopup() {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("번호와 나이를 다시 입력해주세요")
                .setMessage("")
                .setCancelable(true)
                .setPositiveButton("계속",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                .create();
        alert.show();
    }
}

