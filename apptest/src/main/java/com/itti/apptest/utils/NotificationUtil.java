package com.itti.apptest.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationUtil extends ContextWrapper {

    final String TAG = "Tonsen_Tag";
    private NotificationManager manager;
    public static final String id = "channel_1";
    public static final String name = "channel_name_1";
    public PendingIntent pi;

    public NotificationUtil(Context context){
        super(context);
    }
   public NotificationUtil(Context context, java.lang.Class<?> cls){
        super(context);
        this.pi = PendingIntent.getActivity(getApplicationContext(), 0,
                new Intent(getApplicationContext(), cls),
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void createNotificationChannel(){
        NotificationChannel channel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH);
        getManager().createNotificationChannel(channel);
    }
    private NotificationManager getManager(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    public Notification.Builder getChannelNotification(String title, String content){
        if (pi != null){
            Log.d(TAG, "getChannelNotification: pi != null");
            return new Notification.Builder(getApplicationContext(), id)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setContentIntent(pi)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
        } else {
            return new Notification.Builder(getApplicationContext(), id)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
        }
    }
    public NotificationCompat.Builder getNotification_25(String title, String content){
        if (pi != null){
            Log.d(TAG, "getChannelNotification: pi != null");
            return new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle(title)
                    .setContentText(content)
                    .setContentIntent(pi)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
        } else {
            return new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(android.R.drawable.stat_notify_more)
                    .setAutoCancel(true);
        }
    }
    public void sendNotification(String title, String content) {
        if (Build.VERSION.SDK_INT>=26){
            createNotificationChannel();
            Notification notification = getChannelNotification
                    (title, content).build();
            getManager().notify(1,notification);
        }else{
            Notification notification = getNotification_25(title, content).build();
            getManager().notify(1,notification);
        }
    }
}
