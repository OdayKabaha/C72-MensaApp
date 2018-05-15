package com.example.odayk.mensaapp;

import OpenMensa.api.model.*;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class SpeisePlan extends Activity implements View.OnClickListener {
        Button btnGraph;
        List<Meal> Speisekarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speise_plan);

        btnGraph = (Button) findViewById(R.id.btnGraph);
        btnGraph.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this, Graph.class);
            startActivity(intent);
            this.finish();
        }

        public void fillSpeisekarte(){
            Prices preise = new Prices();
            preise.setEmployee(1.99);
            preise.setStudent(1.00);
            preise.setOther(2.50);

            Meal hauptmenu = new Meal();
            hauptmenu.setId(1);
            hauptmenu.setName("Kartoffeln");
            hauptmenu.setPrices(preise);
            this.Speisekarte.add(hauptmenu);

            Meal salat = new Meal();
            hauptmenu.setId(2);
            hauptmenu.setName("Tomate");
            hauptmenu.setPrices(preise);
            this.Speisekarte.add(salat);

            Meal beilage = new Meal();
            hauptmenu.setId(3);
            hauptmenu.setName("Reis");
            hauptmenu.setPrices(preise);
            this.Speisekarte.add(beilage);
        }


   }