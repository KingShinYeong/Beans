package com.example.hh.beans;

/**
 * Created by HH on 2017-11-21.
 */
import android.app.Application;
import android.content.res.AssetManager;

import java.util.ArrayList;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;

public class Info {
    private File file;

    FileInputStream inputStream;
    FileOutputStream outputStream;
    JsonReader reader;
    JsonWriter writer;
    JsonParser parser;

    JsonElement tree;
    JsonArray array;
    JsonObject object;
    JsonElement element;

    AssetManager am;
    String fileName;

    private Gson gson;

    public Info() {
    }

    public Info(AssetManager am, String fileName) {
        this.am = am;
        this.fileName = fileName;
    }

    public ArrayList<Cafe> readCafeListWith(double[] userLocation) {
        ArrayList<Cafe> cafeList = new ArrayList<>();

        Cafe cafeObj;

        double dist;

        try {
            InputStream is = am.open(fileName);
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));

            gson = new GsonBuilder().create();

            reader.beginArray();

            while(reader.hasNext()) {
                cafeObj = gson.fromJson(reader, Cafe.class);
                if(Util.getDistance(userLocation, cafeObj.getLocation()) < 2000) {
                    cafeList.add(cafeObj);
                }
            }
            reader.endArray();
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return cafeList;
    }

    public void writeCafetWith(Cafe cafeObj) {
        try {
            boolean found=false;
            JsonElement cafeElement = null;

            inputStream = new FileInputStream(file);
            reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            parser = new JsonParser();

            tree = parser.parse(reader);

            inputStream.close();

            outputStream = new FileOutputStream(file, false);
            writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            gson = new Gson();
            cafeElement = gson.toJsonTree(cafeObj);

            writer.setIndent("   ");

            if(tree.isJsonNull()) {
                array = new JsonArray();
            } else {
                array = tree.getAsJsonArray();

                for(int i=0; i<array.size(); i++) {
                    element = array.get(i);

                    if(element.isJsonObject()) {
                        object = element.getAsJsonObject();

                        if(object.get("index").getAsInt() == cafeObj.getIndex()) {
                            found = true;
                            array.set(i, cafeElement);
                            break;
                        }
                    }
                }
            }

            if(!found) {
                array.add(cafeElement);
            }

            gson.toJson(array, writer);

            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public User readUserInfoWith(String phoneNumber) {
        User userObj=null, tempObj=null;

        try {
            InputStream is = am.open(fileName);
            reader = new JsonReader(new InputStreamReader(is, "UTF-8"));

            gson = new GsonBuilder().create();

            reader.beginArray();

            while(reader.hasNext()) {
                tempObj = gson.fromJson(reader, User.class);
                if(tempObj.getPhoneNumber().equals(phoneNumber)) {
                    userObj = tempObj;
                    break;
                }
            }

            reader.endArray();
            reader.close();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return userObj;
    }
    /*
    public void writeUserInfoWith(User userObj) {
        try {
            boolean found=false;
            JsonElement userElement = null;

            reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            parser = new JsonParser();

            tree = parser.parse(reader);

            inputStream.close();

            writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            gson = new Gson();
            userElement = gson.toJsonTree(userObj);

            writer.setIndent("   ");

            if(tree.isJsonNull()) {
                array = new JsonArray();
            } else {
                array = tree.getAsJsonArray();

                for(int i=0; i<array.size(); i++) {
                    element = array.get(i);

                    if(element.isJsonObject()) {
                        object = element.getAsJsonObject();

                        if(object.get("phoneNumber").getAsString().equals(userObj.getInfo().get("phoneNumber"))) {
                            found = true;
                            array.set(i, userElement);
                            break;
                        }
                    }
                }
            }

            if(!found) {
                array.add(userElement);
            }

            gson.toJson(array, writer);

            writer.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }*/

}