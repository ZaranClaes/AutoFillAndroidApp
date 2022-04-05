package com.example.autofillversion1official;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.Settings;

import java.util.logging.Handler;

public class MyAlarm extends BroadcastReceiver {


    @Override
    //this will be executed whenever the alarm is fired
    public void onReceive(Context context, Intent intent) {


        //Selected text, alt+enter add method on recieve
        Intent intent1 = new Intent();
        intent1.setClassName(context.getPackageName(), GoOnline.class.getName());
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }
}
