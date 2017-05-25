package com.keepmeposted.javaui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;

import com.keepmeposted.MainActivity;
import com.keepmeposted.R;

/**
 * Created by VISH_V on 10/23/2016.
 */

public class AlertReciever extends BroadcastReceiver {

    public String itemName;
    public String itemDesciption;
    @Override
    public void onReceive(Context context, Intent intent) {


        Bundle extras = intent.getExtras();
        if (extras != null) {
            itemName = extras.getString("todoitemname");
            itemDesciption = extras.getString("todoitemdesciption");
            //The key argument here must match that used in the other activity
        }
        createNotification(context,itemName, itemDesciption,"To Do Alert");
    }

    private void createNotification(Context context, String message, String messageText, String messageAlert) {

        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);
        NotificationCompat.Builder mBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(context)
                .setContentTitle(message)
                .setContentText(messageText)
                .setTicker(messageAlert)
                .setSmallIcon(R.drawable.com_facebook_button_icon);

        mBuilder.setContentIntent(notificIntent);
        mBuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);

        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
