package com.example.hh.beans;

import android.location.Location;

/**
 * Created by HH on 2017-11-09.
 */

public class Cafe {
    private String name;
    private int index;
    private float properties[] = new float[7];
    private double location[] = new double[2];
    private int customersByAge[] = new int[5];

    Cafe(){
        for(int inx = 0; inx < properties.length; inx ++) {
            properties[inx] = 5; // 중간값?
        }
        index = -1;
        name = null;
        location[0] = 0;
        location[1] = 0;
    }
    Cafe(String[] str, int index){
        for(int inx = 0; inx < properties.length; inx++) {
            properties[inx] = Float.parseFloat(str[inx+3]);
        }
        this.index = index;
        name = str[0];
        location[0] = Double.parseDouble(str[1]);
        location[1] = Double.parseDouble(str[2]);
    }

    String getInfoCafe() {
        String str = new String();

        str = "name : " + name;

        for(int inx = 0 ; inx < properties.length; inx++) {
            str += properties[inx] + "/";
        }

        return str;
    }
    String getName(){
        return name;
    }
    String getInfoCafeToUser(User u){
        String str = new String();

        str = name + "  "+(int)u.getSimilarityWith(this) + "% 일치!!" ;

        return str;

    }
    public int[] getCustomersByAge() {
        return customersByAge;
    }
    public int getIndex(){
        return index;
    }
    public float[] getProperties() {
        return properties;
    }
    public  double[] getLocation(){
        return  location;
    }
}