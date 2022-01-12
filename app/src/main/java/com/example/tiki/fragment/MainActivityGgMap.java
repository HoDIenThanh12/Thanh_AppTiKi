package com.example.tiki.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.audiofx.Equalizer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.roomdatabase.service.MyService;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivityGgMap extends AppCompatActivity  {

    private static final int REQUEST_PERMISSION_CODE = 10;
    private TextView tvLet, tvLong;
    private Button btn_GPS, btn_GoneGPS;
    private FrameLayout frameLayout;
    private double lat,logi;
    private SearchView searchView;
    private LocationManager mLocationManager;
    private Location mLocation;
    private GoogleMap mMap;
    private MyService mMyService;
    private boolean isPermission;
    private FusedLocationProviderClient functionalInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_gg_map);
        searchView=findViewById(R.id.s_view);

        //Runtime permissions
//        if (ContextCompat.checkSelfPermission(MainActivityGgMap.this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(MainActivityGgMap.this,new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION
//            },100);
//        }
        Inits();
        clichRequestPermissions();
        Log.d("status permission---> : ",""+isPermission);
        if(isPermission){
            LocationUpdate();
        }
        //initialize functionalInterface
        //functionalInterface=  LocationServices.getFusedLocationProviderClient(this);




        //from fragment
//        Fragment fragment=new MapFragment();
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_ggMap, fragment)
//                .commit();
//        mMyService = new MyService(this);
//        if (CheckNetworkConnect() == 1) {
//            Log.d("isloadInternet-->>", " have internet 3g");
//        } else {
//            if (CheckNetworkConnect() == 2) {
//                Log.d("isloadInternet-->>", " have internet wifi");
//            } else {
//                Log.d("isloadInternet-->>", " no internet ");
//            }
//        }
//        boolean isLoadGPS;
//        boolean isNetwork = false;

        //mLocation = mMyService.getmLocation();
//        try {
//            mLocationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
//            isNetwork = mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
//            isLoadGPS = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,MainActivityGgMap.this);
//            Log.d("location status GPS ---> ", "" + isLoadGPS + " location status Network: " + isNetwork);
////            Toast.makeText(this, "location status GPS: " + isLoadGPS +
////                    " location status Network: " + isNetwork, Toast.LENGTH_LONG).show();
//        } catch (Exception e) {
//            Log.d("location---> ", "" + e);
//            //Toast.makeText(this, "location status GPS: "+isLoadGPS, Toast.LENGTH_LONG).show();
//        }


//        m = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            Log.d("location---> ","fail ");
//            return;
//
//        }
//        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);
//        location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        onLocationChanged(location);
    }

    @SuppressLint("MissingPermission")
    private void LocationUpdate() {
        fragments();
        //mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500,  1, MainActivityGgMap.this );
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

//        try {
//            mLocationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
//            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,MainActivityGgMap.this);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        //
        functionalInterface.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //initialze location
                mLocation= task.getResult();
                if(mLocation!=null){
                    try {
                        //initialze geoCoder
                        Geocoder geocoder = new Geocoder(MainActivityGgMap.this,
                                Locale.getDefault());
                        //initialze adress
                        List<Address> addresses = geocoder.getFromLocation(
                                mLocation.getLatitude(), mLocation.getLongitude(),1
                        );
                        lat=addresses.get(0).getLatitude();
                        logi=addresses.get(0).getLongitude();
                        Log.d("latidute---->: ",""+addresses.get(0).getLatitude());
                        Log.d(" longi---->: ",""+addresses.get(0).getLongitude());
                        Log.d("Ciuntry---->: ",""+ addresses.get(0).getCountryName());
                        Log.d(" locality---->: ",""+addresses.get(0).getLocality());
                        Log.d(" addressline---->: ",""+addresses.get(0).getAddressLine(0));
                        fragments();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
    private void fragments(){
            frameLayout.setVisibility(View.VISIBLE);
            Fragment fragment=new MapFragment();
            getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_ggMap, fragment)
                .commit();
    }
    private void Inits() {
        tvLet=findViewById(R.id.tv_content);
        btn_GPS=findViewById(R.id.btnEnableGPS);
        btn_GoneGPS=findViewById(R.id.btnGoneGPS);
        frameLayout=findViewById(R.id.frame_ggMap);
        btn_GPS.setOnClickListener(v->{
//            if(ActivityCompat.checkSelfPermission(MainActivityGgMap.this,
//                    Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                //when permissiom granted
                //getLocation();
                clichRequestPermissions();
//            }else{
//                //when permission denid
//                ActivityCompat.requestPermissions(MainActivityGgMap.this, new String[]{
//                        Manifest.permission.ACCESS_FINE_LOCATION}, 44);
//            }

            Log.d("location---> ","đã nhấn");
//            frameLayout.setVisibility(View.VISIBLE);
//            Fragment fragment=new MapFragment();
//            getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_ggMap, fragment)
//                .commit();

        });
        btn_GoneGPS.setOnClickListener(v->{
            //frameLayout.setVisibility(View.GONE);
            onOpenPermission();
        });
//        tvLong=findViewById(R.id.longt);
    }

    private void onOpenPermission() {
        Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri= Uri.fromParts("package",getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);
    }

    private void clichRequestPermissions() {
        if(Build.VERSION.SDK_INT <Build.VERSION_CODES.M){
            return;
        }
        else{
            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                //Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
                isPermission=true;
            }
            else{
                String[] perStrings={Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(perStrings, REQUEST_PERMISSION_CODE);
            }
        }
    }
    //manager status message dinalog permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] > PackageManager.PERMISSION_GRANTED) {
                LocationUpdate();
                //Toast.makeText(this, "Permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    private int CheckNetworkConnect() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo _wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo _3g = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return ni != null && _3g.isConnected() ? 1
                : ni != null && _wifi.isConnected() ? 2 : 3 ;
    }

    public double getLat() {
        return lat;
    }

    public double getLogi() {
        return logi;
    }

//
//    @Override
//    public void onLocationChanged(@NonNull Location location) {
////        Log.d("đã vô location---> ",""+location);
////        double lat= location.getLatitude();
////        double longt=location.getLongitude();
////        tvLet.setText("tọa độ lat: "+ lat);
////        //tvLong.setText("tạo độ longi: "+longt);
////        Log.d("location---> ",""+location);
////        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onLocationChanged(@NonNull List<Location> locations) {
//        Log.d("latidute---->: ","load locatiob");
//        //initialze location
//        //mLocation= task.getResult();
////        if(mLocation!=null){
////            try {
////                //initialze geoCoder
////                Geocoder geocoder = new Geocoder(MainActivityGgMap.this,
////                        Locale.getDefault());
////                //initialze adress
////                List<Address> addresses = geocoder.getFromLocation(
////                        mLocation.getLatitude(), mLocation.getLongitude(),1
////                );
////                lat=addresses.get(0).getLatitude();
////                logi=addresses.get(0).getLongitude();
////                Log.d("latidute---->: ",""+addresses.get(0).getLatitude());
////                Log.d(" longi---->: ",""+addresses.get(0).getLongitude());
////                Log.d("Ciuntry---->: ",""+ addresses.get(0).getCountryName());
////                Log.d(" locality---->: ",""+addresses.get(0).getLocality());
////                Log.d(" addressline---->: ",""+addresses.get(0).getAddressLine(0));
////                fragments();
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
////        }
//    }
//
//    @Override
//    public void onFlushComplete(int requestCode) {
//
//    }
//
//    @Override
//    public void onStatusChanged(String provider, int status, Bundle extras) {
//
//    }
//
//    @Override
//    public void onProviderEnabled(@NonNull String provider) {
//
//    }
//
//    @Override
//    public void onProviderDisabled(@NonNull String provider) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}