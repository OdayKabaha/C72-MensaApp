package com.example.odayk.mensaapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class Settings extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch switch1;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";
    private static final String SWITCH1 = "switch1";
    boolean switchOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        loadData();

        switch1 = (Switch)findViewById(R.id.switch1);

        switch1.setOnCheckedChangeListener(this);

        loadData();
        updateViews();

    }

    public void saveData(){
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putBoolean(SWITCH1, switch1.isChecked());
        editor.apply(); //save variable
        //Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews(){
        switch1.setChecked(switchOnOff);
    }


    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if(switch1.isChecked()){
            saveData();
            //Toast.makeText(getApplicationContext(),"Mitteilungen sind eingeschaltet",Toast.LENGTH_LONG).show();

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY,8);
            calendar.set(Calendar.MINUTE,45);
            calendar.set(Calendar.SECOND,0);

            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
        }else {
            saveData();
            //Toast.makeText(getApplicationContext(),"Mitteilungen sind ausgeschaltet",Toast.LENGTH_LONG).show();

            Intent intent = new Intent(getApplicationContext(), Notification_reciever.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }
    }
}

