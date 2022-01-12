package com.example.tiki.fragment;

import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_ACTION;
import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_ACTION1;
import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_TEST;
import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_TEXT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class ExampleBroadCast extends BroadcastReceiver {
    private  final Context mCONTEXT = null;
    //key nhận dữ liệu intent
    private static final String MY_TEXT1="com.thanh.lun";
    public static final String MY_KEY="dienthanh";

    @Override
    public void onReceive(Context context, Intent intent) {

        if(MY_KEY.equals(intent.getAction())){
            Toast.makeText(context, "passaction", Toast.LENGTH_LONG).show();
            Log.d("true ---->>> :","true");
        }
        else{
            Toast.makeText(context, "failaction", Toast.LENGTH_LONG).show();
            Log.d("fail ---->>> :","sđsgdfgdfgdf");
        }


        // if api <27 use ConnectivityManager.CONNECTIVITY_ACTION.
        // if api >29 use ConnectivityManager.EXTRA_NETWORK_REQUEST.
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if(IsNetWork(context)){
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
                Log.d("status network ---->>> :","true");
            }else {
                Toast.makeText(context, "Internet DisConnected", Toast.LENGTH_LONG).show();
                Log.d("status network ---->>> :","false");
            }
        }
        if(MY_ACTION.equals(intent.getAction())){
            String txt= intent.getStringExtra(MY_TEXT);
        }

    }

    private boolean IsNetWork(Context cm) {
        ConnectivityManager manager= (ConnectivityManager) cm.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(manager==null)
            return false;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            Network network= manager.getActiveNetwork();
            if(network==null)
                return false;
            NetworkCapabilities capabilities =manager.getNetworkCapabilities(network);
            return capabilities!=null && capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI);
        }
        else{
            NetworkInfo networkInfo=manager.getActiveNetworkInfo();
            return networkInfo!=null && networkInfo.isConnected();
        }
    }

}
