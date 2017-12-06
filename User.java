package com.example.hh.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by HH on 2017-11-09.
 */

public class User implements Serializable {
    private String phoneNumber;
    private double location[] = new double[2];
    private int age;
    private float properties[] = new float[7];


    User(){

        for(int i=0; i < properties.length; i ++) {
            properties[i] = 5;
        }
        phoneNumber = null;
    }

    User(String phoneNumber, int age){
        // Chung-Ang University's location
        location[0] = 37.504694;
        location[1]= 126.956741;

        int inx  = (age/10) - 1;
        if(inx <= 0){ inx = 0; }
        else if(inx > 5){ inx = 4; }
        this.age = inx;
        this.phoneNumber = phoneNumber;
    }
    public void setProperties(float[] p){
        this.properties = p;
    }
    public void setLoacation(double[] location){
        this.location = location;
    }
    public void setLocation(){
        location[0] = 37.504694;
        location[1]= 126.956741;
    }
    public String getPhoneNumber() {

        return phoneNumber;
    }
    public int getAge(){ return age; }
    public String getAgeText(){
        String str = (age + 1) * 10 + "";
        str += "대";
        if(this.age == 4){
            str += " 이상";
        }
        return str;
    }
    public double[] getLocation() {
        return location;
    }

    public float[] getProperties() {
        return properties;
    }

    public ArrayList<Cafe> findCafe(CafeDB cafeDB) {

        ArrayList<Float> sim_Sum = new ArrayList<Float>();

        ArrayList<Cafe> result = new ArrayList<>();

        for(int inx= 0; inx < cafeDB.getCafeNum(); inx++) {
            sim_Sum.add(getSimilarityWith(cafeDB.getCafe(inx)));
        }

        int limit;

        if(sim_Sum.size() > 10) {
            limit = 5;
        }else if(sim_Sum.size()> 5) {
            limit = 3;
        }else{
            limit = 1;
        }
        int index[] = getIndexOfMAX(sim_Sum, limit);

        for(int inx = 0; inx < limit; inx++){
            result.add(cafeDB.getCafe(index[inx]));
        }

        return result;
    }

    public float getSimilarityWith(Cafe cafe) {
        float result = 0;
        float sum1 = 0;
        float sum2 = 0;
        float sum3 = 0;
        float cafeProperties[] = cafe.getProperties();

        for(int i=0; i < properties.length; i ++) {
            //result += Math.pow(this.properties[i]- cafeProperties[i], 2);
            sum1 += (this.properties[i]-5) * (cafeProperties[i] - 5);
            sum2 += Math.pow(this.properties[i]- 5, 2);
            sum3 += Math.pow(cafeProperties[i]- 5, 2);
        }
        sum2 = (float) Math.sqrt(sum2);
        sum3 = (float) Math.sqrt(sum3);
        result = (sum1 / (sum2 * sum3) ) + 1;

        return result * 50;
    }

    public int[] getIndexOfMAX(ArrayList<Float> sim_sum, int limit){
        int index[] = new int[limit];
        ArrayList<Float> buf = (ArrayList<Float>) sim_sum.clone();
        Collections.sort(buf);
        Collections.reverse(buf);

        for(int inx = 0; inx < limit; inx ++) {
            index[inx] = sim_sum.indexOf(buf.get(inx));
            sim_sum.set(index[inx], 0f);
        }
        return index;
    }

}