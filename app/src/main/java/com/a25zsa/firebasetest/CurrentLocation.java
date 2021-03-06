package com.a25zsa.firebasetest;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by 25zsa on 4/15/2018.
 */

public class CurrentLocation extends ActivityCompat {
    LocationManager locationManager;
    LocationListener locationListener;
    FusedLocationProviderClient client;

    public CurrentLocation(Activity atv){
        requestPermission(atv);
        client = LocationServices.getFusedLocationProviderClient(atv);
//        client.getLastLocation().addOnSuccessListener(atv, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//
//            }
//        })
    }

    private void requestPermission(Activity atv){
        ActivityCompat.requestPermissions(atv, new String[] {ACCESS_FINE_LOCATION}, 1);
    }



//    private FusedLocationProviderClient client;
//    public CurrentLocation(){
//        System.out.println(1);
//        client = LocationServices.getFusedLocationProviderClient(this);
//        System.out.println(2);
//        if(ActivityCompat.checkSelfPermission(CurrentLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_DENIED){
//            System.out.println(3);
//            return;
//        }
//        System.out.println(4);
//        client.getLastLocation().addOnSuccessListener(CurrentLocation.this, new OnSuccessListener<Location>() {
//            @Override
//            public void onSuccess(Location location) {
//                System.out.println(5);
//                System.out.println(location);
//                System.out.println(6);
//            }
//        });
//
//    }
}
