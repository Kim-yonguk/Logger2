package com.example.a1a1a1.logger;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by 1a1a1 on 2016-11-15.
 */
public class MapView extends FragmentActivity {
    GoogleMap gmap;
    MarkerOptions marker;


    LatLng location = new LatLng(37.566535, 126.977969);
    CameraPosition cp = new CameraPosition.Builder().target(location).zoom(10).build();

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);

        DataList information = new DataList();

        gmap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));

        for(int i = 0 ; i < information.arr_text.size(); i++){
            LatLng pinmark = new LatLng(information.arr_latitude.get(i),information.arr_longtitude.get(i));
            marker = new MarkerOptions().position(pinmark).title(information.arr_text.get(i));
            gmap.addMarker(marker);
        }
    }
}