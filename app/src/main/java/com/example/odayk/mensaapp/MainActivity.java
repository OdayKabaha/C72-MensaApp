package com.example.odayk.mensaapp;


import android.app.FragmentManager;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    HashMap<String, List<String>> myHeader;
    List<String> myChild;
    ExpandableListView expandableListView;
    MyAdapter adapter;


    private OpenMensaAPI openMensaAPI;

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
        //myHeader = DataProvider.getInfo();
        // spaeter, wenn gebraucht
        DataProvider.getTodaysMenu();
        DataProvider.init();

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
        if (id == R.id.wilhelminenhof) {
            try {
                Canteen canteen24 = openMensaAPI.getCanteenById(24);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // Toast.makeText(this, "Wilhelminenhof Mensa ausgewählt", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.treskowalle) {
            try {
                Canteen canteen30 = openMensaAPI.getCanteenById(30);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //   Toast.makeText(this, "Treskowalle Mensa ausgewählt", Toast.LENGTH_SHORT).show();
        }

        final LocalDate localDate = LocalDate.now();
        Date date = new Date();
//        int h = date.getHours();

        DateFormat f = new SimpleDateFormat("dd . MM . yyyy");

        String currentTime = f.format(date);

        // LocalDate localDate = LocalDate.of(2018, 05, 27);


        new Thread(new Runnable() {
            @Override
            public void run() {
                //noinspection SimplifiableIfStatement
                if (id == R.id.wilhelminenhof) {
                    try {
                        Canteen canteen24 = openMensaAPI.getCanteenById(24);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (id == R.id.treskowalle) {
                    try {
                        Canteen canteen30 = openMensaAPI.getCanteenById(30);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
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


    /**********************************Implementierung von ExpndableListView***************************************/
    class  MyAdapter extends BaseExpandableListAdapter {
        private Context ctx;
        private HashMap<String, List<String>> ChildTitles;
        private List<String> HeaderTitles;

        MyAdapter(Context ctx, HashMap<String, List<String>> ChildTitles, List<String> HeaderTitles) {
            this.ctx = ctx;
            this.ChildTitles = ChildTitles;
            this.HeaderTitles = HeaderTitles;
        }

        @Override
        public int getGroupCount() {
            return HeaderTitles.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return ChildTitles.get(HeaderTitles.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return HeaderTitles.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return ChildTitles.get(HeaderTitles.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String title = (String) this.getGroup(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.custom_header, null);
            }
            TextView txt = (TextView) convertView.findViewById(R.id.idTitle);
            txt.setTypeface(null, Typeface.BOLD);
            txt.setText(title);
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            String title = (String) this.getChild(groupPosition, childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.custom_childitems, null);
            }
            TextView txt = (TextView) convertView.findViewById(R.id.idChild);
            txt.setText(title);
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

   static class DataProvider {

        public static OpenMensa.api.model.Menu getTodaysMenu(){
          if (todayMenuList.isEmpty())  return null; else return todayMenuList.get(0);
        }

        private final static List<OpenMensa.api.model.Menu> todayMenuList = new ArrayList<OpenMensa.api.model.Menu>();

        public static void init(){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    OpenMensaAPI api = new OpenMensaAPI();
                    try {
                        OpenMensa.api.model.Menu todaysMenu  = api.temp_getMenuFromCanteenByDate(api.getCanteenById(24),"29-05-2018");
                        todayMenuList.add(todaysMenu);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }


      /*  public static HashMap<String, List<String>> getInfo() {

            final OpenMensa.api.model.Menu todaysMenu;

          new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OpenMensaAPI api = new OpenMensaAPI();
                        try {
                            todaysMenu  = api.temp_getMenuFromCanteenByDate(api.getCanteenById(24),"29-05-2018");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
          });*/

 //       MealWrapper today = new MealWrapper(todaysMenu);
 //       return today.getMealMap();
/*
        HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();
        List<String> ChildDetails1 = new ArrayList<>();
        ChildDetails1.add("This is Children 11");
        ChildDetails1.add("This is Children 12");
        ChildDetails1.add("This is Children 13");
        ChildDetails1.add("This is Children 14");

        List<String> ChildDetails2 = new ArrayList<>();
        ChildDetails2.add("This is Children 21");
        ChildDetails2.add("This is Children 22");
        ChildDetails2.add("This is Children 23");
        ChildDetails2.add("This is Children 24");

        List<String> ChildDetails3 = new ArrayList<>();
        ChildDetails3.add("This is Children 31");
        ChildDetails3.add("This is Children 32");
        ChildDetails3.add("This is Children 33");
        ChildDetails3.add("This is Children 34");

        List<String> ChildDetails4 = new ArrayList<>();
        ChildDetails4.add("This is Children 41");
        ChildDetails4.add("This is Children 42");
        ChildDetails4.add("This is Children 43");
        ChildDetails4.add("This is Children 44");

        List<String> ChildDetails5 = new ArrayList<>();
        ChildDetails5.add("This is Children 51");
        ChildDetails5.add("This is Children 52");
        ChildDetails5.add("This is Children 53");
        ChildDetails5.add("This is Children 54");

        List<String> ChildDetails6 = new ArrayList<>();
        ChildDetails6.add("This is Children 61");
        ChildDetails6.add("This is Children 62");
        ChildDetails6.add("This is Children 63");
        ChildDetails6.add("This is Children 64");

        HeaderDetails.put("Header 1", ChildDetails1);
        HeaderDetails.put("Header 2", ChildDetails2);
        HeaderDetails.put("Header 3", ChildDetails3);
        HeaderDetails.put("Header 4", ChildDetails4);
        HeaderDetails.put("Header 5", ChildDetails5);
        HeaderDetails.put("Header 6", ChildDetails6);

        return HeaderDetails;*/
            //   }
        }

}
