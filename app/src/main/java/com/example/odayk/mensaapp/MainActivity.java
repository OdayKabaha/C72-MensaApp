package com.example.odayk.mensaapp;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.darwindeveloper.horizontalscrollmenulibrary.custom_views.HorizontalScrollMenuView;
import com.example.odayk.mensaapp.FilterFunktion.FilterFunktion;
import com.example.odayk.mensaapp.mainpage.RecyclerViewAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.*;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    final String currentDate = dateFormat.format(date);

    private static final String TAG = MainActivity.class.getSimpleName();
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    //Horizontal Scroll View
    HorizontalScrollMenuView menu;
    TextView textView;

    OpenMensaAPI openMensaAPI = new OpenMensaAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Verbindet das Layout der Activity mit der java-Datei
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() entered");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initImageBitmaps();

        //Horizontal menu
        menu = (HorizontalScrollMenuView) findViewById(R.id.menu);
        textView = (TextView) findViewById(R.id.textView);

        initmenu();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
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

        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.wilhelminenhof) {
        }else if (id == R.id.treskowalle) { }

        return super.onOptionsItemSelected(item);
    }

    private void initmenu(){
        menu.addItem("Montag", R.drawable.montag);
        menu.addItem("Dienstag", R.drawable.dienstag);
        menu.addItem("Mittwoch", R.drawable.mittwoch);
        menu.addItem("Donnerstag", R.drawable.donnerstag);
        menu.addItem("Freitag", R.drawable.freitag);
        menu.addItem("Samstag", R.drawable.samstag);
        menu.addItem("Sonntag", R.drawable.sonntag);

        menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(com.darwindeveloper.horizontalscrollmenulibrary.extras.MenuItem menuItem, int position) {
                Toast.makeText(MainActivity.this,""+menuItem.getText(),Toast.LENGTH_SHORT).show();
                textView.setText(menuItem.getText());
            }
        });
    }

    private void initImageBitmaps(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");
        mImageUrls=new ArrayList<>();
        mealNameHolder();
        initRecyclerView();
    }

    public void mealNameHolder(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Meal> meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                    if (meals != null) {
                        for (Meal meal : meals) {
                            mImageUrls.add("https://ctcdn.azureedge.net/cloudcache/3/7/d/2/e/4/37d2e4e679e2b59cee8d23f6dbd3fd745c376458.jpg");
                            mNames.add(meal.getName());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview.");
        RecyclerView recyclerView = findViewById(R.id.recycleview_Speiseplan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(itemDecoration);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_first_layout) {
            Log.d(TAG,"first selected");
            Intent intent = new Intent(this, Alarmlist.class);
            startActivity(intent);
        } else if (id == R.id.nav_second_layout) {
            Log.d(TAG,"second selected");
            Intent intent = new Intent(this, Graph.class);
            startActivity(intent);
        } else if (id == R.id.nav_third_layout) {
            Log.d(TAG,"third selected");
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        } else if (id == R.id.nav_fourth_layout) {
            Log.d(TAG,"fourth selected");
            Intent intent = new Intent(this, FilterFunktion.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
