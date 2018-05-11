package com.example.odayk.mensaapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SpeisePlan extends Activity implements View.OnClickListener {
        Button btnGraph;

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
   }