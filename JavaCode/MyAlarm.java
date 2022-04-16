package com.example.autofillversion1official;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.logging.Handler;

public class MyAlarm extends BroadcastReceiver {


    @Override
    //this will be executed whenever the alarm is fired
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context,GoOnline.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,i,0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"update")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Reminder: Fill In Temp Form")
            .setContentText("Tap here to auto fill")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(123,builder.build());

    }

}
