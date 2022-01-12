package com.example.tiki.roomdatabase;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MyLpcationNetworkGPS  extends Service {
    private  final Context mContext ;

    public MyLpcationNetworkGPS(Context mContext)  {
        this.mContext = mContext;
    }

//    private Boolean isOnline() {
//        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//        if(ni != null && ni.isConnected()) {
//            return true;
//        }
//        return false;
//    }
//    public void connect(){
//        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo _wifi = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        NetworkInfo _3g = conManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
