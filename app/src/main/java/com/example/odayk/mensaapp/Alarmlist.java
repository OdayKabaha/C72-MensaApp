package com.example.odayk.mensaapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.Meal;

public class Alarmlist extends Activity {
    OpenMensaAPI openMensaAPI = new OpenMensaAPI();
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    final String currentDate = dateFormat.format(date);

    List<BestMeals> bMeals = new ArrayList<>();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private static final String PREF_NAME = "prefs";

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);
        //dropData();
        //loadData();

        listView = (ListView)findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));
        bMeals.add(new BestMeals(false,"Pommes"));
       // ListView listView = (ListView) findViewById(R.id.listview);
/*
        bMeals.add(new BestMeals(false,"Pommes"));
        bMeals.add(new BestMeals(false,"Reis"));
        bMeals.add(new BestMeals(false,"Hähnchen"));
        bMeals.add(new BestMeals(false,"Joghurt"));
        bMeals.add(new BestMeals(false,"Pommes"));
        bMeals.add(new BestMeals(false,"Reis"));
        bMeals.add(new BestMeals(false,"Hähnchen"));
        bMeals.add(new BestMeals(false,"Joghurt"));
        bMeals.add(new BestMeals(false,"Pommes"));
        bMeals.add(new BestMeals(false,"Reis"));
        bMeals.add(new BestMeals(false,"Hähnchen"));
        bMeals.add(new BestMeals(false,"Joghurt"));
        bMeals.add(new BestMeals(false,"Pommes"));
        bMeals.add(new BestMeals(false,"Reis"));
        bMeals.add(new BestMeals(false,"Hähnchen"));
        bMeals.add(new BestMeals(false,"Joghurt"));
        bMeals.add(new BestMeals(false,"Pommes"));
        bMeals.add(new BestMeals(false,"Reis"));
        bMeals.add(new BestMeals(false,"Hähnchen"));
        bMeals.add(new BestMeals(false,"Joghurt"));*/

        final CustomAdapter adapter = new CustomAdapter(this,bMeals);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BestMeals bestMeals = bMeals.get(position);

                if (bestMeals.isSelected()){
                    bestMeals.setSelected(false);
                }
                else{
                    bestMeals.setSelected(true);
                }
                bMeals.set(position,bestMeals);
                saveData();
                // update adapter
                adapter.updateRecords(bMeals);
            }
        });
    }

    public void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bMeals);
        editor.putString("task list", json);
        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list",null);
        Type type = new TypeToken<ArrayList<BestMeals>>() {}.getType();
        bMeals = gson.fromJson(json, type);
        if (bMeals == null){
            bMeals = new ArrayList<>();
        }
    }

    public void dropData(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(bMeals);
        editor.remove(json);
    }


    public class MealsHolder extends AsyncTask<Void, String, String> {
        OpenMensaAPI openMensaAPI = new OpenMensaAPI();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(date);

        ArrayAdapter<String> adapter;
        //String mealName;

        //MealsHolder(String mealName )
        //{
        //    this.mealName = mealName;
        //}

        @Override
        protected void onPreExecute() {
            adapter = (ArrayAdapter<String>)listView.getAdapter();
        }

        @Override
        protected String doInBackground(Void... voids) {
            List<Meal> meals = null;
            int i = 0;
            try {
                meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                if (meals != null) {
                    for (Meal meal : meals){
                            publishProgress("Kategorie: " + meal.getCategory().toString());
                            publishProgress("Preise:");
                            publishProgress("   Studenten: " + String.valueOf(meal.getPrices().getStudent()));
                            publishProgress("   Mitarbeiter: " + String.valueOf(meal.getPrices().getEmployee()));
                            publishProgress("   Andere: " + String.valueOf(meal.getPrices().getOther()));
                            publishProgress("Zutaten:");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "fertig";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            adapter.add(values[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}