package com.example.tiki.fragment;

import android.R.layout;
import android.annotation.SuppressLint;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.R.id;
import com.example.tiki.view.ViewProduct;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MapFragment extends Fragment implements LocationListener {
    SupportMapFragment myMapFragment;
    GoogleMap mMap;
    private View convertView;
    FragmentManager fm;
    private Location mLocation;
    private LocationManager mLocationManager;
    //private MainActivityGgMap mMainActivityGgMap;
    private ViewProduct mViewProduct;
    private Spinner mSpinner;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //mMainActivityGgMap= (ViewProduct) getActivity();
        mViewProduct= (ViewProduct) getActivity();
        //initSpiner();
        // Inflate the layout for this fragment
        //View v= inflater.inflate(R.layout.fragment_map, container, false);
//        SupportMapFragment m;
//        m=(SupportMapFragment) getActivity()
//                .getSupportFragmentManager().findFragmentById(R.id.google_Map);


        //////
        convertView=inflater.inflate(R.layout.fragment_map,null);
        initSpiner();
        fm=getChildFragmentManager();
        myMapFragment=(SupportMapFragment) fm.findFragmentById(id.google_Map);

        // here instead of using getMap(), we are using getMapAsync() which returns a callback and shows map only when it gets ready.
        //it automatically checks for null pointer or older play services version, getMap() is deprecated as mentioned in google docs.

        myMapFragment.getMapAsync(new OnMapReadyCallback() {

            @Override
            public void onMapReady(GoogleMap googlemap) {
                //x`Toast.makeText(mMainActivityGgMap, "đã vô: ", Toast.LENGTH_SHORT).show();

                if(mLocationManager==null){
                    mLocationManager =
                            (android.location.LocationManager) mViewProduct.getSystemService(mViewProduct.LOCATION_SERVICE);
                }
                if(mLocationManager!=null){
                    Toast.makeText(mViewProduct, "mLocationManager: khác null", Toast.LENGTH_SHORT).show();
                    updateLocation();
                }

                // TODO Auto-generated method stub
                mMap=googlemap;
                mMap.clear();
                //initiale marrket map
                MarkerOptions options= new MarkerOptions();
                double l=106.6854911;
                double la=10.9733719;
                LatLng latLng=new LatLng(10.9733719,106.6854911);
                options.position(latLng);
                options.title("đi nhậu không" );
                Log.d("-->  ",""+options.getAnchorU());
                mMap.setOnMapClickListener(new OnMapClickListener() {
                @Override
                public void onMapClick(@NonNull LatLng latLng) {
                    List<Address> addresses = null;
                    try {
                        Geocoder geocoder = new Geocoder(mViewProduct,
                                Locale.getDefault());
                        addresses = geocoder.getFromLocation(
                                latLng.latitude, latLng.longitude,1
                        );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(mViewProduct,
                            "latidute---->: "+addresses.get(0).getLatitude()+
                                  "\nlongi---->: "+addresses.get(0).getLongitude()+
                                  "\nCiuntry---->: "+ addresses.get(0).getCountryName()+
                                  "\nlocality---->: "+addresses.get(0).getLocality()+
                                  "\naddressline---->: "+addresses.get(0).getAddressLine(0),
                            Toast.LENGTH_LONG).show();
                    //when click on map
                    //initiale marrket map
                    MarkerOptions options= new MarkerOptions();
                    //set position of market
                    options.position(latLng);
                    //set title of market
                    options.title("đi nhậu không" );
                    mMap.clear();
                    //Animating to zoom marrket
                    //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                    mMap.addMarker(options);
                }
                });
                //mMap.clear();
                //show all market
                //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                //show market with zoom 10X
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                mMap.addMarker(options);
        }});

        /////
        return convertView;
    }

    @SuppressLint("MissingPermission")
    private void updateLocation() {

        try {
            mLocationManager.requestLocationUpdates(
                    android.location.LocationManager.NETWORK_PROVIDER, 1000, 0, MapFragment.this);
        } catch (SecurityException ex) {
            Log.i("TAG-->", "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
            Log.d("TAG-->", "provider does not exist " + ex.getMessage());
        }
        //mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,0, this::onLocationChanged);

        mLocation= mLocationManager
                .getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(mLocation!=null){
            Log.d("lấy vị trí---> ","lat: "+mLocation.getLatitude());
            Toast.makeText(mViewProduct, "mLocation request: "+"lat:= "+mLocation.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(mViewProduct, "mLocation request: location=null", Toast.LENGTH_SHORT).show();
    }

    private void initSpiner() {
        mSpinner=convertView.findViewById(R.id.dr_StyleggMap);
        getSpiner();
    }

    private void getSpiner() {
        ArrayList<String> mList=new ArrayList<>();
        mList.add("Style 1");
        mList.add("Style 2");
        mList.add("Style 3");
        mList.add("Style 4");
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), layout.simple_spinner_item, mList);
        mSpinner.setAdapter(arrayAdapter);
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Log.d("đã vô location---> ",""+location);
        double lat= location.getLatitude();
        double longt=location.getLongitude();
        //tvLong.setText("tạo độ longi: "+longt);
        Log.d("location---> ",""+location);
        Toast.makeText(mViewProduct, "đã vô: "+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
}