package com.example.hh.beans;

import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.Collections;

public class CafeDB {
    ArrayList<Cafe> cafeDB;

    CafeDB(){
        cafeDB = new ArrayList<Cafe>();
    }
    CafeDB(AssetManager am, User u){
        cafeDB = new ArrayList<Cafe>();

        Info info = new Info(am,"cafeDB.json");

        cafeDB = info.readCafeListWith(u.getLocation());
    }

    public void addCafe(Cafe c) {
        cafeDB.add(c);
    }

    public void printAll() {
        for(int inx = 0 ; inx < cafeDB.size(); inx ++) {
            printCafe(inx);
        }
    }

    public ArrayList<Cafe> getCafebyAge(int age) {
        ArrayList<Cafe> cafebyAge = new ArrayList<Cafe>();
        ArrayList<Integer> agePreference = new ArrayList<>();
        int limit;

        if(getCafeNum() > 10) {
            limit = 3;
        }else {
            limit = 1;
        }

        for(int inx = 0; inx < getCafeNum(); inx ++){
            agePreference.add(cafeDB.get(inx).getCustomersByAge()[age]);
        }

        ArrayList<Integer> buf = (ArrayList<Integer>) agePreference.clone();
        Collections.sort(buf);
        Collections.reverse(buf);

        int index[] = new int[limit];

        for(int inx = 0; inx < limit; inx ++) {
            index[inx] = agePreference.indexOf(buf.get(inx));
            agePreference.set(index[inx], 0);
        }

        for(int inx = 0; inx < limit; inx++){
            cafebyAge.add(cafeDB.get(index[inx]));
        }

        return cafebyAge;
    }


    public void printCafe(int index) {
        System.out.println(cafeDB.get(index).getInfoCafe());
    }

    public int getCafeNum() {
        return cafeDB.size();
    }

    public Cafe getCafe(int index) {
        return cafeDB.get(index);
    }
}
