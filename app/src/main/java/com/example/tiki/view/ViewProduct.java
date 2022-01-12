package com.example.tiki.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.hotspot2.pps.HomeSp;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.adapper.AdapperProduct;
import com.example.tiki.databinding.ActivityViewProductBinding;
import com.example.tiki.db.ConvertBinding;
import com.example.tiki.fragment.BlankFragmentGgMap;
import com.example.tiki.fragment.ExampleBroadCast;
import com.example.tiki.fragment.MainActivityGgMap;
import com.example.tiki.fragment.MapFragment;
import com.example.tiki.module.entity.ItemsItem;
import com.example.tiki.module.entity.MetaData;
import com.example.tiki.module.service.ItemCnClick;
import com.example.tiki.roomdatabase.braodcaselistener.BroadCastNetwork;
import com.example.tiki.roomdatabase.service.MyService;
import com.example.tiki.viewmodule.ProductViewModule;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewProduct extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private MutableLiveData<MetaData> metaData = new MutableLiveData<>();
    private List<ItemsItem> lItems = new ArrayList<>();
    //key broadcast
    private  final String MY_KEY="dienthanh";

    private AdapperProduct adapperProduct;
    private ProductViewModule productViewModule;
    private boolean temp = false;
    private ConvertBinding convertBinding;
    private ActivityViewProductBinding binding;

    //service and gps
    private MyService mService;
    private boolean isService;
    private boolean isLoadAPI;
    private boolean isNetwork;
    private boolean isPermission;
    private FusedLocationProviderClient functionalInterface;
    private GoogleMap mMap;
    private LocationManager mLocationManager;
    private Location mLocation;
    private ExampleBroadCast mBroadCast;
    private FrameLayout frameLayout;
    private BroadCastNetwork mMBroadCastNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        frameLayout=findViewById(R.id.frg_ggMap);
        //mService=new MyService(ViewProduct.this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_product);
        productViewModule = new ViewModelProvider(this).get(ProductViewModule.class);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcvProduct.setLayoutManager(linearLayoutManager);
        binding.setLifecycleOwner(this);
        this.getLifecycle().addObserver(productViewModule);
        productViewModule = new ViewModelProvider(this).get(ProductViewModule.class);
        functionalInterface=  LocationServices.getFusedLocationProviderClient(this);
        mMBroadCastNetwork = new BroadCastNetwork();


//        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadCast,
//                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


        ///ckech permission
        requesrPermission();
        Intent v1=new Intent();
        int k= v1.getIntExtra("key",0);
        if(k==1){
            Log.d("nhay số","");
        }


        getAll();

        //binding.startServiceF.setOnClickListener(startServiceF(binding.startServiceF));
        binding.startServiceF.setOnClickListener(v -> {

            if(isService){
                Log.d("service status-----> ","running");
                return;
            }
            if(ViewProduct.this.requesrPermission()){
                Log.d("service status-----> ","start running");
                //isService=true;
                //startSF();
                //mService = new MyService(this);
                //Log.d("service-----> ","longi: "+mService.getLongitude());
                startFragmentGgMap();
            }

            //CheckNetworkConnect();
            //Log.d("ckeck network and location ---> : "," "+CheckNetworkConnect());
        });
        binding.stopServiceF.setOnClickListener(v -> {
            stopSF();
        });

    }
    private void getAll(){
        productViewModule.getmListItems().observe(this, new Observer<List<ItemsItem>>() {
            @Override
            public void onChanged(List<ItemsItem> itemsItems) {
                if (itemsItems != null) {
                    lItems.addAll(itemsItems);
                    isLoadAPI = true;
                }
                adapperProduct = new AdapperProduct(itemsItems, new ItemCnClick() {
                    @Override
                    public void ItemClick(ItemsItem i) {
                        convertBinding.ItemClick(i, DetailTrendingProduct.class, ViewProduct.this, productViewModule.getMetaData().getValue().getBackgroundImage());
                    }
                });
                binding.rcvProduct.setAdapter(adapperProduct);
            }
        });
        productViewModule.getMetaData().observe(this, new Observer<MetaData>() {
            @Override
            public void onChanged(MetaData metaData) {
                if (metaData != null) {
                    ConvertBinding.blurImageUrl(binding.imageViewBackground, metaData.getBackgroundImage());
                    String title = metaData.getTitle();
                    binding.tv.setText(title);
                    //Log.d("isload-->>"," "+productViewModule.getIsload());
                }
            }
        });
    }
    private boolean requesrPermission() {
        //Runtime permissions
        if (ContextCompat.checkSelfPermission(ViewProduct.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
            ///if((ActivityCompat.shouldShowRequestPermissionRationale(ViewProduct.this, Manifest.permission.ACCESS_FINE_LOCATION)))
            ActivityCompat.requestPermissions(ViewProduct.this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },1);

            Log.d("compeli","--->>>");
        }
        else
            isPermission=true;
        return isPermission;
    }

    private void startFragmentGgMap() {
        if(isService){
            Toast.makeText(ViewProduct.this.getApplicationContext(),
                    "Service is running", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            if(ActivityCompat.checkSelfPermission(ViewProduct.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                //when permissiom granted
                isService=true;
                getLocation();
            }else{
                //when permission denid
                ActivityCompat.requestPermissions(ViewProduct.this, new String[]{
                        Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            }
        }
    }
    @SuppressLint("MissingPermission")
    private void getLocation() {
        startSF();
        //startFragments();
    }
    private void startFragments(){
        binding.frgGgMap.setVisibility(View.VISIBLE);
        Fragment fragment=new MapFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frg_ggMap, fragment)
                .commit();
    }
    private void startSF() {
//        mService=new MyService(ViewProduct.this);
//        Intent intent = new Intent(this, MyService.class);
//        intent.putExtra("index", "thành lùn finish connect network");
//        Log.d("size->>> ", " " + lItems.size());
//        startService(intent);
        mService=new MyService(ViewProduct.this);
        if(mService.isGPSEnabled()){
            Log.d("long: ","location: "+mService.getLongitude());
            //startFragments();
            //Toast.makeText(getApplicationContext(), "location: "+mService.getLongitude(), Toast.LENGTH_SHORT).show();
        }
        else{
            mService.showSettingsAlert();
        }
    }

    private void stopSF() {
        if(mService!=null){
            mService.stopLocation();
            isService=false;
        }
        else {
            Toast.makeText(getApplicationContext(), "not service", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter filter =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mMBroadCastNetwork, filter);
        Log.d("create ---->>> :","broadcast");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (adapperProduct != null) {
            adapperProduct.release();
        }
    }

    private void CheckNetworkConnect() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        //showSettingsAlert();
        Log.d("ckeck network and location ---> : "," "+cm.getActiveNetwork());
//        return ni != null && ni.isConnected() ? 1
//                : ni != null && _wifi.isConnected() ? 2 : 3 ;
    }

}