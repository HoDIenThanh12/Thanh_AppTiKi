package com.example.tiki.roomdatabase;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class MyApplicationVersionSDK extends Application {
    public static final String Channel_ID="Channel_service_example";
    @Override
    public void onCreate() {
        super.onCreate();
        createChanneNotification();
    }

    private void createChanneNotification() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel= new NotificationChannel(Channel_ID,
                    "Channel Service Example",
                    NotificationManager.IMPORTANCE_DEFAULT);
            //distable music defaul
//            channel.setSound(null, null);
            NotificationManager manager = getSystemService(NotificationManager.class);
            if(manager!=null)
                manager.createNotificationChannel(channel);
        }
    }
}
