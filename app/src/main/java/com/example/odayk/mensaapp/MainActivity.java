package com.example.odayk.mensaapp;


import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.IOException;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    OpenMensaAPI openMensaAPI = new OpenMensaAPI();
    public void setOpenMensaAPI(OpenMensaAPI openMensaAPI) {
        this.openMensaAPI = new OpenMensaAPI();
    }
    public Canteen printCanteen(int i) throws IOException {
       return openMensaAPI.getCanteenById(30);
       // System.out.println("gut");
    }
    String printMealsFromHtw() throws IOException {
       return openMensaAPI.getSomeMealsFromHTW();
    }


    private static final String TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Verbindet das Layout der Activity mit der java-Datei
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() entered");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu campus) { // verbindet das Menü mit der java-Datei
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, campus);
        return super.onCreateOptionsMenu(campus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.wilhelminenhof) {
            try {
                setOpenMensaAPI(openMensaAPI);
                Canteen canteen24 = openMensaAPI.getCanteenById(24);
            } catch (IOException e) {
                e.printStackTrace();
            }
           // Toast.makeText(this, "Wilhelminenhof Mensa ausgewählt", Toast.LENGTH_SHORT).show();
        }else if (id == R.id.treskowalle) {
            try {
                Canteen canteen30 = openMensaAPI.getCanteenById(30);
            } catch (IOException e) {
                e.printStackTrace();
            }
         //   Toast.makeText(this, "Treskowalle Mensa ausgewählt", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first_layout) {
            Log.d(TAG,"first selected");
            Log.d(TAG,"third selected");
            Intent intent = new Intent(this, Alarmlist.class);
            startActivity(intent);
            this.finish();
        } else if (id == R.id.nav_second_layout) {
            Log.d(TAG,"second selected");
            Intent intent = new Intent(this, Graph.class);
            startActivity(intent);
            this.finish();
        } else if (id == R.id.nav_third_layout) {
            Log.d(TAG,"third selected");
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            this.finish();
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
