package com.example.odayk.mensaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Alarmlist extends AppCompatActivity implements View.OnClickListener {

    Button backAlarmlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        backAlarmlist = (Button) findViewById(R.id.backAlarmlist);
        backAlarmlist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
