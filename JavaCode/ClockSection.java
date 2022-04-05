package com.example.autofillversion1official;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ClockSection extends AppCompatActivity {
    float x1, x2, y1, y2;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_section);

        timePicker = (TimePicker) findViewById(R.id.TimePick);

        findViewById(R.id.CancelTimer).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                cancelAlarm();
            }


        });

        findViewById(R.id.SetTimer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creates Calendar Object
                Calendar calendar = Calendar.getInstance();
                //Builds the calendar using time picker
                if(Build.VERSION.SDK_INT >= 23) {
                    calendar.set(
                            calendar.get(calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),
                            0
                    );
                }
                else{
                    calendar.set(
                            calendar.get(calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),
                            0
                    );
                }

                setAlarm(calendar.getTimeInMillis());

            }

        });





    }

    private void setAlarm(long timeInMillis) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 , intent, 0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(this, "Alarm is Set", Toast.LENGTH_SHORT).show();
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0 , intent, 0);
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }

    }

    public boolean onTouchEvent(MotionEvent touchevent){
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:  //on down press, record x and y positions
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x1 > x2) {
                    Intent i = new Intent(ClockSection.this, MainActivity.class);
                    startActivity(i);
                }
                break;
        }
        return false;
    }
}
