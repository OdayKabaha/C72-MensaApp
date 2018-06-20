package com.example.odayk.mensaapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarmlist);

        ListView listView = (ListView) findViewById(R.id.listview);
        final List<BestMeals> bMeals = new ArrayList<>();

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final List<Meal> meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                    if (meals != null) {
                        for (Meal meal : meals){
                            bMeals.add(new BestMeals(false,meal.getName()));
                        }
                    } else {

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */
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
        bMeals.add(new BestMeals(false,"Joghurt"));

        final CustomAdapter adapter = new CustomAdapter(this,bMeals);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BestMeals bestMeals = bMeals.get(position);

                if (bestMeals.isSelected())
                    bestMeals.setSelected(false);
                else
                    bestMeals.setSelected(true);
                bMeals.set(position,bestMeals);
                // update adapter
                adapter.updateRecords(bMeals);
            }
        });
    }
}
