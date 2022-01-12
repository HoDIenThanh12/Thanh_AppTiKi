package com.example.tiki.roomdatabase.service;



import static com.example.tiki.roomdatabase.MyApplicationVersionSDK.Channel_ID;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.tiki.R;
import com.example.tiki.fragment.MapFragment;
import com.example.tiki.roomdatabase.database.DataBaseLocation;
import com.example.tiki.roomdatabase.entitys.Locations;
import com.example.tiki.view.ViewProduct;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MyService extends Service implements LocationListener {

    private static Context mContext = null;

    // Flag for GPS status
    private boolean isGPSEnabled = false;

    // Flag for network status
    private boolean isNetworkEnabled = false;

    private LocationManager mLocationManager;

    private Location location; // Location
    private double latitude; // Latitude
    private double longitude; // Longitude
    private int quantityOfSatellites;
    private int hdop;

    // The minimum distance to change Updates in meters
    private static final long MIN_FOR_UPDATES = 10; // 10 meters

    // The minimum time between updates in milliseconds
    private static final long TIME_UPDATES = 1000 * 10 * 1; // 10 second

    private DataBaseLocation mDataBaseLocation;

    public MyService(Context mContext) {
        this.mContext = mContext;
        getmLocation();
        //create roomdatabase
        mDataBaseLocation=DataBaseLocation.getInstance(mContext);
    }

    @SuppressLint("MissingPermission")
    public Location getmLocation() {
        Log.d("","from getlocation ");
        try {
            mLocationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled=mLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isGPSEnabled && !isNetworkEnabled) {
                Log.d("","location status GPS: " + isGPSEnabled +
                        " location status Network: " + isNetworkEnabled);
                Toast.makeText(mContext.getApplicationContext(), "location status GPS: " + isGPSEnabled +
                        " location status Network: " + isNetworkEnabled, Toast.LENGTH_LONG).show();
            }
            else{
                Log.d("","location status GPS: " + isGPSEnabled +
                        "\nlocation status Network: " + isNetworkEnabled);
                if(isNetworkEnabled){
                    try {
                        mLocationManager.requestLocationUpdates(
                                LocationManager.NETWORK_PROVIDER, TIME_UPDATES, MIN_FOR_UPDATES, this::onLocationChanged);
                    } catch (SecurityException ex) {
                        Log.i("NETWORK-->", "fail to request location update, ignore", ex);
                    } catch (IllegalArgumentException ex) {
                        Log.d("NETWORK-->", "provider does not exist " + ex.getMessage());
                    }
                    location= mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if(location!=null){
                        Log.d("NETWORK-->", "provider does not exist--> bắt đầu load" );
                        latitude=location.getLatitude();
                        longitude=location.getLongitude();
                    }
                }
                if(isGPSEnabled){
                    if(location==null){
                        try {
                            mLocationManager.requestLocationUpdates(
                                    LocationManager.GPS_PROVIDER, TIME_UPDATES, MIN_FOR_UPDATES, this::onLocationChanged);
                        } catch (SecurityException ex) {
                            Log.i("GPS-->", "fail to request location update, ignore", ex);
                        } catch (IllegalArgumentException ex) {
                            Log.d("GPS-->", "provider does not exist " + ex.getMessage());
                        }
                        if(mLocationManager!=null){
                            location= mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if(location!=null){
                                Log.d("GPS-->", "phần GPS bắt đầu load" );
                                latitude=location.getLatitude();
                                longitude=location.getLongitude();
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
        }
        return location;
    }
    public void stopLocation(){
        if(mLocationManager != null){
            mLocationManager.removeUpdates(MyService.this);
            Log.d("","Stop service");
            Toast.makeText(mContext, "Service is stop", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isGPSEnabled() {
        return isGPSEnabled;
    }

    public Location getLocation() {
        return location;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String temp= intent.getStringExtra("index");
        sendNotification(temp);
        return START_NOT_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private void sendNotification(String s) {
        Log.d("service--->> ","start connect network: " + isOnline());
        Intent intent=new Intent(this, ViewProduct.class);
        PendingIntent pintent1;
        pintent1=PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews rmView= new RemoteViews(getPackageName(), R.layout.custom_notification);
        rmView.setTextViewText(R.id.tv_Content,"start service ");
        rmView.setTextViewText(R.id.tv_ds,"đầu tiên-> "+s);

        Notification notification=new NotificationCompat.Builder(this,Channel_ID)
                                    .setContentTitle("Notification start service")
                                    .setContentText(s)
                                    .setSmallIcon(R.drawable.ic_launcher_background)
                                    .setContentIntent(pintent1)
                                    .setCustomContentView(rmView)
                                    .build();
        startForeground(1, notification);
    }
        public Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
    @Override
    public void onLocationChanged(@NonNull Location location) {
        saveData(location);
    }
    private void saveData(Location l){
        Log.d("đã vô location---> ",""+l);
        double lat= l.getLatitude();
        double longt=l.getLongitude();
        List<Address> addresses = null;
        try {
            Geocoder geocoder = new Geocoder(mContext,
                    Locale.getDefault());
            addresses = geocoder.getFromLocation(
                    l.getLatitude(), l.getLongitude(),1
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("location chi tiết: ",
                "\nlatidute---->: "+addresses.get(0).getLatitude()+
                "\nlongi---->: "+addresses.get(0).getLongitude()+
                "\nCiuntry---->: "+ addresses.get(0).getCountryName()+
                "\nlocality---->: "+addresses.get(0).getLocality()+
                "\naddressline---->: "+addresses.get(0).getAddressLine(0));

        mDataBaseLocation.loactionDAO().insertLocationTk(new Locations(longitude, latitude,addresses.get(0).getCountryName(),addresses.get(0).getAddressLine(0) ));


        //Toast.makeText(mContext, "đã vô: "+l.getLatitude()+","+l.getLongitude(), Toast.LENGTH_SHORT).show();
    }
    //setting dialog gps
    public void showSettingsAlert(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        alertDialog.setTitle("GPS is settings");

        // Setting Dialog Message
        alertDialog.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        // On pressing the Settings button.
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                mContext.startActivity(intent);
            }
        });

        // On pressing the cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing Alert Message
        alertDialog.show();
    }
}
