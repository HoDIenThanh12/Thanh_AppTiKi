package com.example.tiki.roomdatabase.braodcaselistener;

import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_ACTION;
import static com.example.tiki.fragment.MainActivityBroadCastReceiver.MY_TEXT;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.example.tiki.view.ViewProduct;

public class BroadCastNetwork extends BroadcastReceiver {
    private  final Context mCONTEXT = null;
    //key nhận dữ liệu intent
    private final String MY_KEY="dienthanh";
    private boolean isNetwork;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("fail ---->>> :","action: "+intent.getAction());
        if(MY_KEY.equals(intent.getAction())){
            Toast.makeText(context, "passaction", Toast.LENGTH_LONG).show();
            Log.d("true ---->>> :","true");
        }
        else{
            Toast.makeText(context, "failaction", Toast.LENGTH_LONG).show();
            Log.d("fail ---->>> :","sđsgdfgdfgdf");
        }
        String h=intent.getStringExtra(MY_KEY);
        Log.d("fail ---->>> :","sđsgdfgdfgdf-> "+h);
        if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())){
            if(IsNetWork(context)){
                Toast.makeText(context, "Internet Connected", Toast.LENGTH_LONG).show();
                Log.d("status network ---->>> :","true");
            }else {
                isNetwork=false;
                Toast.makeText(context, "Internet DisConnected", Toast.LENGTH_LONG).show();
                Log.d("status network ---->>> :","false");
            }
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

    public boolean getisNetwork() {
        return isNetwork;
    }
}
