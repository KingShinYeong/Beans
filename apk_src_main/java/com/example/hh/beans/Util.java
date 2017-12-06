package com.example.hh.beans;

import android.location.Location;

/**
 * Created by HH on 2017-11-21.
 */
public class Util {
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);

    }

    public static int getDistance(double[] location1, double[] location2) {

        Location locationA = new Location("PointA");
        Location locationB = new Location("PointB");

        locationA.setLatitude(location1[0]);
        locationA.setLongitude(location1[1]);
        locationB.setLatitude(location2[0]);
        locationB.setLongitude(location2[1]);

        return (int)locationA.distanceTo(locationB);
    }

}