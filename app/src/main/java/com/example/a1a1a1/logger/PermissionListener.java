package com.example.a1a1a1.logger;

import java.util.ArrayList;

/**
 * Created by 1a1a1 on 2016-11-15.
 */
public interface PermissionListener {

    void onPermissionGranted();

    void onPermissionDenied(ArrayList<String> deniedPermissions);

}