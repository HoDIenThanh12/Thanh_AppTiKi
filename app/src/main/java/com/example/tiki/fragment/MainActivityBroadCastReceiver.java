package com.example.tiki.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiki.R;
import com.example.tiki.roomdatabase.braodcaselistener.BroadCastNetwork;

import java.util.List;

public class MainActivityBroadCastReceiver extends AppCompatActivity {
    public static final String MY_ACTION = "com.thanh";
    public static final String MY_TEXT = "com.thanh.text";
    public static final String MY_TEST = "pass";
    public static final String MY_TEXT1="com.thanh.lun";
    public static final String MY_ACTION1 = "com.thanh.lun1";
    public static final String MY_ACTION2 = "com.thanh.lun2";
    public static final String MY_ACTION3 = "com.thanh.lun3";
    public static final String MY_KEY="dienthanh";
    private ExampleBroadCast broadCast;
    private BroadCastNetwork mBroadCastNetwork;
    Button btnSendBC;
    TextView tvSendBC;

    private BroadcastReceiver mBroadcastReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(MY_ACTION.equals(intent.getAction())){
                String txt=intent.getStringExtra(MY_TEXT);
                tvSendBC.setText(txt);
                //sendBroadcast();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_broad_cast_receiver);
        btnSendBC=findViewById(R.id.btn_SendBC);
        tvSendBC=findViewById(R.id.tv_SendBC);
        broadCast=new ExampleBroadCast();
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(MY_KEY));
        clickSendBC1();
        //clickSendBC1();
        btnSendBC.setOnClickListener(v->{
            //clickSendBC();
            clickSendBC1();
        });
    }

    private void clickSendBC1() {
        Log.d("broadcast ---->>> :","Nhấn ");
        //Intent intent=new Intent(this, ExampleBroadCast.class);
        //cách 1
        //intent.setClass(this, ExampleBroadCast.class)
        //cách 2
        Intent intent=new Intent();
        ComponentName name= new ComponentName(this, BroadCastNetwork.class);
        intent.setComponent(name);
        intent.putExtra(MY_KEY,"dữ liệu gửi theo broadcast");
        sendBroadcast(intent);
        //LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void clickSendBC() {
        Log.d("broadcast ---->>> :","Nhấn ");
        ConnectivityManager manager = (ConnectivityManager)this.getSystemService(this.CONNECTIVITY_SERVICE);
        Intent intent= new Intent(MY_ACTION1);
        intent.putExtra(MY_TEXT, "my thanh lun");
        sendBroadcast(intent);


        //broadCast=new ExampleBroadCast();
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        //sendOrderedBroadcast(intent,"");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        //filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        //IntentFilter filter =new IntentFilter(MY_ACTION);

//        IntentFilter filter =new IntentFilter(MY_ACTION);
//        registerReceiver(broadCast, filter);

//        IntentFilter filter =new IntentFilter(MY_KEY);
//        registerReceiver(broadCast, filter);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}