package com.example.hh.beans;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by HH on 2017-11-09.
 */

public class SubActivity extends AppCompatActivity{

    TextView tw;
    Button btn;
    User user;

    LocationManager mLocationManager;
    LocationListener mLocationListener;

    double latitude;
    double longitude;
    boolean isGpsDone;

    Timer mTimer;
    TimerTask mTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        tw = (TextView)findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.Sub_btn);
        init();
    }

    @Override
    protected void onResume() {
        setGps();

        super.onResume();
    }

    void init(){
        Intent intent = getIntent();

        user = (User) intent.getSerializableExtra("CurrentUser");

        tw.setText("\nYour Phone Num : " + user.getPhoneNumber() + "\nage : " + user.getAgeText() +"\nProperties : ");

        for(int inx = 0; inx < user.getProperties().length; inx++){
            tw.append("\n"+user.getProperties()[inx]);
        }

        btn.setClickable(false);
        btn.setText("GPS 수신중...");

        isGpsDone = false;
        checkGps();
    }

    public void recommendClick(View v){
        Intent intent = new Intent(this, RecommendActivity.class);

        Bundle bundle = new Bundle();
        bundle.putSerializable("CurrentUser",user);
        intent.putExtras(bundle);

        startActivity(intent);
    }
    public void checkGps(){
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        }
        setGps();
    }
    public void setGps(){
        if( ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && !isGpsDone) {
            isGpsDone = true;
            mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            mLocationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    if (location.getAccuracy() < 50) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();

                        user.setLoacation(new double[]{latitude, longitude});

                        btn.setClickable(true);
                        btn.setText("카페추천받기");
                        mLocationManager.removeUpdates(mLocationListener);
                        mTimer.cancel();
                    }
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {
                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };

            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

            mTask = new TimerTask() {
                @Override
                public void run() {
                    SubActivity.super.runOnUiThread(new Runnable() {
                        public void run() {
                            user.setLocation();
                            btn.setClickable(true);
                            btn.setText("카페추천받기");
                            mLocationManager.removeUpdates(mLocationListener);
                            setPopup();
                        }
                    });
                }
            };
            mTimer = new Timer();
            mTimer.schedule(mTask, 10000);
        }
    }

    public void setPopup() {
        final AlertDialog alert = new AlertDialog.Builder(this)
                .setTitle("GPS를 수신하지 못했습니다.")
                .setMessage("GPS를 수신하지 못했습니다.\n기본 좌표인 중앙대학교로 설정합니다.\n")
                .setCancelable(true)
                .setPositiveButton("계속",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                            }
                        })
                .create();
        alert.show();
    }

    @Override
    public void finish() {
        mTimer.cancel();
        mLocationManager.removeUpdates(mLocationListener);
        super.finish();
    }
}
