package com.example.a1a1a1.logger;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


public class  MainActivity extends Activity {

    public GetGPS GPS ;
    ArrayList<Double> arr_latitude = new ArrayList();
    ArrayList<Double> arr_longtitude = new ArrayList();
    ArrayList<String> arr_text = new ArrayList();

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            Toast.makeText(MainActivity.this, "권한 허가", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            Toast.makeText(MainActivity.this, "권한 거부\n" + deniedPermissions.toString(), Toast.LENGTH_SHORT).show();
        }


    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setRationaleMessage("구글 로그인을 하기 위해서는 주소록 접근 권한이 필요해요")
                .setDeniedMessage("취소하셨습니다. [설정] > [권한] 에서 권한을 다시 허용할 수 있어요.")
                .setPermissions(Manifest.permission.READ_CONTACTS)
                .check();

        setContentView(R.layout.activity_main);

        final DataList information = new DataList();
        GPS = new GetGPS(MainActivity.this);

        final TextView lontext = (TextView) findViewById(R.id.lontext);
        final TextView lattext = (TextView) findViewById(R.id.lattext);
        Button gpscall = (Button) findViewById(R.id.gpscall);
        Button mapcall = (Button) findViewById(R.id.mapview);
        Button store = (Button) findViewById(R.id.button3);
        Button reset = (Button) findViewById(R.id.reset);
        final EditText inputtext = (EditText) findViewById(R.id.editText);

        gpscall.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        GPS.getLocation();
                        double lat = GPS.getLatitude();
                        double lon = GPS.getLongitude();

                        lontext.setText("경도 : "+String.valueOf(lon));
                        lattext.setText("위도 : "+String.valueOf(lat));
                        }
                }
        );

        mapcall.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent it = new Intent (getApplicationContext(), MapView.class);
                        startActivity(it);
                    }
                }
        );

        store.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        GPS.getLocation();
                        double lat = GPS.getLatitude();
                        double lon = GPS.getLongitude();
                        String str = inputtext.getText().toString();
                        information.arr_latitude.add(lat);
                        information.arr_longtitude.add(lon);
                        information.arr_text.add(str);
                        inputtext.setText("");
                    }
                }
        );

        reset.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        information.arr_latitude.clear();
                        information.arr_longtitude.clear();
                        information.arr_text.clear();
                        Toast.makeText(getApplicationContext(), "리셋 되었습니다..",Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

}
