package com.example.odayk.mensaapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDate;


import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.helpers.MealWrapper;
import OpenMensa.api.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;



public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, List<String>> myHeader;
    List<String> myChild;
    ExpandableListView expandableListView;
    MyAdapter adapter;


    public OpenMensaAPI openMensaAPI;

    public void createOpenMensaAPI() {
        openMensaAPI = new OpenMensaAPI();
    }


    public Canteen printCanteen(int i) throws IOException {
        return openMensaAPI.getCanteenById(30);
        // System.out.println("gut");
    }

    String printMealsFromHtw() throws IOException {
        return openMensaAPI.getSomeMealsFromHTW();
    }

    String CanteenClose = "Heute ist die Mensa geschlossen!";


    private static final String TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Verbindet das Layout der Activity mit der java-Datei
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() entered");

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /****************************Implementierung von ExpandableListView*************************/
        expandableListView = (ExpandableListView) findViewById(R.id.idListView);
        myHeader = DataProvider.getInfo();
        myChild = new ArrayList<>(myHeader.keySet());
        adapter = new MyAdapter(this, myHeader, myChild);
        expandableListView.setAdapter(adapter);
        /********************************************************************************************/


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        createOpenMensaAPI();

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();


        //noinspection SimplifiableIfStatement

        Date date = new Date();
//        int h = date.getHours();
        DateFormat dateFormat = new SimpleDateFormat("dd . MM . yyyy");
        final String currentDate = dateFormat.format(date);

        new Thread(new Runnable() {
            @Override
            public void run() {
                //noinspection SimplifiableIfStatement
                if (id == R.id.wilhelminenhof) {
                    try {
                        if (openMensaAPI.getDayStatusFromCanteenByDate(24, currentDate).isOpen() == false) {
                            //Toast.makeText(getApplicationContext(),CanteenClose,Toast.LENGTH_SHORT).show();}
                            Toast.makeText(MainActivity.this, "mensa ist geschlossen", Toast.LENGTH_SHORT).show();
                        } else {
                            Canteen canteen24 = openMensaAPI.getCanteenById(24);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (id == R.id.treskowalle) {
                    try {
                        if (openMensaAPI.getDayStatusFromCanteenByDate(30, currentDate).isOpen() == true) {
                            //   Toast.makeText(this, "Wilhelminenhof Mensa ausgewählt", Toast.LENGTH_SHORT).show();}
                            Toast.makeText(MainActivity.this, "mensa ist geschlossen", Toast.LENGTH_SHORT).show();
                        } else {
                            Canteen canteen30 = openMensaAPI.getCanteenById(30);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onOptionsItemSelected(item);

        //   Toast.makeText(this, "Wilhelminenhof Mensa ausgewählt", Toast.LENGTH_SHORT).show();
        //  Toast.makeText(this, "Treskowalle Mensa ausgewählt", Toast.LENGTH_SHORT).show();
    }


    //*********************************************************************//
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                //noinspection SimplifiableIfStatement
                if (id == R.id.wilhelminenhof) {
                    try {
                        if(openMensaAPI.getDayStatusFromCanteenByDate(24,localDate ).isOpen() == false){
                            Toast.makeText(getApplicationContext(),CanteenClose,Toast.LENGTH_SHORT).show();}
                        else {
                            try {

                                Canteen canteen24 = openMensaAPI.getCanteenById(24);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    //   Toast.makeText(this, "Wilhelminenhof Mensa ausgewählt", Toast.LENGTH_SHORT).show();
                }else if (id == R.id.treskowalle) {
                    try {
                        Canteen canteen30 = openMensaAPI.getCanteenById(30);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                  //  Toast.makeText(this, "Treskowalle Mensa ausgewählt", Toast.LENGTH_SHORT).show();
                }
            }
        });
         */

    //****************************************************************//


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first_layout) {
            Log.d(TAG, "first selected");
            Log.d(TAG, "third selected");
            Intent intent = new Intent(this, Alarmlist.class);
            startActivity(intent);
        } else if (id == R.id.nav_second_layout) {
            Log.d(TAG, "second selected");
            Intent intent = new Intent(this, Graph.class);
            startActivity(intent);
        } else if (id == R.id.nav_third_layout) {
            Log.d(TAG, "third selected");
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

