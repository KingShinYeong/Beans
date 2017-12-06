package com.example.hh.beans;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/*
 * Created by HH on 2017-11-21.
 */

public class RecommendActivity extends AppCompatActivity{

    TextView tw_cafe;
    TextView tw_cafe_title;
    TextView tw_age;
    TextView tw_age_title;


    User user;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        tw_cafe = (TextView)findViewById(R.id.textView3);
        tw_age = (TextView) findViewById(R.id.textView5);
        tw_cafe_title = (TextView) findViewById(R.id.textView7);
        tw_age_title = (TextView) findViewById(R.id.textView6);

        init();
    }

    void init(){
        Intent intent = getIntent();

        AssetManager am = getResources().getAssets();

        user = (User) intent.getSerializableExtra("CurrentUser");

        CafeDB cafeDB = new CafeDB(am,user);
        if(cafeDB.getCafeNum() == 0){
            setPopup();
            tw_cafe.append("주위에 카페가 없습니다.\n");
        }
        else {
            ArrayList<Cafe> recommendCafe = user.findCafe(cafeDB);

            tw_cafe_title.append("나랑 제일 잘맞는 카페 : ");
            for (int inx = 0; inx < recommendCafe.size(); inx++) {
                tw_cafe.append(recommendCafe.get(inx).getInfoCafeToUser(user) + "\n" +
                        Util.getDistance(user.getLocation(), recommendCafe.get(inx).getLocation()) + "m\n\n");
            }

            ArrayList<Cafe> ageRecommendCafe = cafeDB.getCafebyAge(user.getAge());
            tw_age_title.append("내 또래가 제일\n좋아하는 카페 : ");
            for (int inx = 0; inx < ageRecommendCafe.size(); inx++) {
                tw_age.append(ageRecommendCafe.get(inx).getName() + "\n" + Util.getDistance(user.getLocation(), ageRecommendCafe.get(inx).getLocation()) + "m\n\n");
            }
        }
    }
    public void setPopup() {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("주위에 카페가 없습니다.")
                .setMessage("현재 사용자의 위치 2km 근처에 카페가 없습니다.")
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

