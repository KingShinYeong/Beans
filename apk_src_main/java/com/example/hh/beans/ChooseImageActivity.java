package com.example.hh.beans;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ChooseImageActivity extends AppCompatActivity {

    ImageView imageView[];
    boolean imageCheck[];

    TextView ed;

    int MAX_IMAGE_NUM = 31; // 이미지 갯수

    GridLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        ed = (TextView)findViewById(R.id.textView);

        init();
    }

    void init(){
        setImage();


    }

    void setImage(){ // 이미지 설정

        imageView = new ImageView[MAX_IMAGE_NUM];
        imageCheck = new boolean[MAX_IMAGE_NUM];

        linearLayout = (GridLayout) findViewById(R.id.grid);
        int width = 960;

        try {
            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            width = dm.widthPixels - 20;
        }catch (Exception e){
        }

        for (int inx = 0; inx < imageView.length; inx++) {

            String str = "img" + (inx + 1);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), getResources().getIdentifier(str, "drawable", this.getPackageName()));
            Bitmap mThumbnail = ThumbnailUtils.extractThumbnail(bmp, width/3, width/3);

            imageView[inx] = new ImageView(getApplicationContext());
            imageView[inx].setPadding(3, 3, 3, 3);
            imageView[inx].setId(inx);
            imageView[inx].setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView[inx].setImageBitmap(mThumbnail);



            imageView[inx].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int inx = view.getId();
                    if (imageCheck[inx]) {
                        imageCheck[inx] = !imageCheck[inx];
                        view.setAlpha(1);
                    } else {
                        imageCheck[inx] = !imageCheck[inx];
                        view.setAlpha(0.3f);
                    }
                }
            });

            linearLayout.addView(imageView[inx]);
        }
    }

    float[] getProperties() throws IOException {
        String buf;
        int count = 0;
        float properties[] = new float[7];
        int propertiesCount[] = new int[7];


        AssetManager am = getResources().getAssets();
        InputStream is = am.open("imageProperty.txt");

        BufferedReader bufrd = new BufferedReader(new InputStreamReader(is));


        for(int inx = 0; inx < MAX_IMAGE_NUM; inx ++){
            buf = bufrd.readLine();

            if(buf == null){
                break;
            }

            String s_num[] = buf.split("/");

            if(imageCheck[inx]) {
                for (int jnx = 0; jnx < properties.length; jnx++) {
                    if(!s_num[jnx].equals("-")) {
                        properties[jnx] += Float.parseFloat(s_num[jnx]); // 선택한 사진의 속성값을 모두 더한다음
                        propertiesCount[jnx]++; // 속성 카운트 ++
                    }
                }
                count++;
            }
        }


        for(int inx = 0; inx < properties.length; inx ++){
            properties[inx] /= propertiesCount[inx]; // 평균
            properties[inx] = (float) Math.round(properties[inx] * 100)/100;
        }

        bufrd.close();
        is.close();

        return  properties;
    }

    public void ChooseImage_btn(View v){
        boolean check = true;
        User u = getUser();

        for(int inx = 0;inx < u.getProperties().length; inx++){
            if(Float.isNaN(u.getProperties()[inx])){
                Toast.makeText(this,"사진을 조금 더 선택해주세요!",Toast.LENGTH_SHORT).show();
                check = false;
            }
        }

        if(check) {
            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("CurrentUser",u);

            startActivity(intent);
        }
    }

    public User getUser(){
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra("NewUser");


        float s[] = new float[7];
        try{
            s = getProperties();
        }catch(Exception e){
            e.printStackTrace();
        }
        user.setProperties(s);
        return  user;
    }
    public void setDatatxt(User u){
        String filename = "myfile.txt";
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            PrintWriter out = new PrintWriter(outputStream);
           // out.println(str);
            out.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
