package com.example.autofillversion1official;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.autofillversion1official.R;

public class MainActivity extends AppCompatActivity {
    float x1, x2, y1, y2;
    public String identifierOne,identifierTwo,identifierThree;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotificationChannel();


    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "AutofillReminderChannel";
            String description = "Channel for Alarm Manager";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("update",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);

        }
    }

    public void UpdateProtocol (View view){
        //opening up shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        //Grabbing ID for ID1 and storing in phone memory
        EditText edtTxtID1 = findViewById(R.id.employeeID1Enter);
        myEdit.putString("ID1", edtTxtID1.getText().toString());
        myEdit.apply();

        identifierOne = sharedPreferences.getString("ID1", "");
        TextView txtID1 = findViewById(R.id.employeeID1Text);
        txtID1.setText(identifierOne);

        //Repeat for 2-3
        EditText edtTxtID2 = findViewById(R.id.employeeID2Enter);
        myEdit.putString("ID2", edtTxtID2.getText().toString());
        myEdit.apply();

        identifierTwo = sharedPreferences.getString("ID2", "");
        TextView txtID2 = findViewById(R.id.employeeID2Text);
        txtID2.setText(identifierTwo);

        EditText edtTxtID3 = findViewById(R.id.employeeID3Enter);
        myEdit.putString("ID3", edtTxtID3.getText().toString());
        myEdit.apply();

        identifierThree = sharedPreferences.getString("ID3", "");
        TextView txtID3 = findViewById(R.id.employeeID3Text);
        txtID3.setText(identifierThree);
    }

    public void SendData (View view){
        Intent f = new Intent(MainActivity.this,GoOnline.class);
        startActivity(f);
    }

    public void ClearProtocol (View vew){
        //SharedPreferences settings = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        //settings.edit().clear().apply();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        myEdit.putString("ID1", "000000");
        myEdit.apply();

        identifierOne = sharedPreferences.getString("ID1", "");
        TextView txtID1 = findViewById(R.id.employeeID1Text);
        txtID1.setText(identifierOne);

        myEdit.putString("ID2", "000000");
        myEdit.apply();

        identifierTwo = sharedPreferences.getString("ID2", "");
        TextView txtID2 = findViewById(R.id.employeeID2Text);
        txtID2.setText(identifierTwo);

        myEdit.putString("ID3", "000000");
        myEdit.apply();

        identifierThree = sharedPreferences.getString("ID3", "");
        TextView txtID3 = findViewById(R.id.employeeID3Text);
        txtID3.setText(identifierThree);

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
                if (x1 < x2) {
                    Intent i = new Intent(MainActivity.this, ClockSection.class);
                    startActivity(i);
                }
                break;
            }
            return false;
        }
    }

