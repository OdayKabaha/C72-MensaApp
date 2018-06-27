package com.example.odayk.mensaapp.FilterFunktion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.odayk.mensaapp.MainActivity;
import com.example.odayk.mensaapp.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.Meal;


public class FilterFunktion extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<String> mNameList = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    final OpenMensaAPI openMensaAPI = new OpenMensaAPI();
    final OpenMensa.api.model.Menu menu = new OpenMensa.api.model.Menu();
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    final String currentDate = dateFormat.format(date);

        Button backFilter;

        EditText editText;
        ListView listView;
        ListViewAdapter adapter;


        ArrayList<MealName> nameArrayList = new ArrayList<MealName>();

        Spinner spinner1,spinner2, spinner3;
        ArrayAdapter<CharSequence> adapter1;

        Switch switch1,switch2;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_filter);

            backFilter = (Button) findViewById(R.id.backFilter);
            backFilter.setOnClickListener(this);

            new Thread(new Runnable() {
                List<Meal> mealsLokal = new ArrayList<>();
                @Override
                public void run() {
                    try {
                        final List<Meal> meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                        if (meals != null) {
                            for (Meal meal : meals){
                                mNameList.add(meal.getName());
                            }
                        } else {
                            mImageUrls.add("https://ctcdn.azureedge.net/cloudcache/3/7/d/2/e/4/37d2e4e679e2b59cee8d23f6dbd3fd745c376458.jpg");
                            mNameList.add("Curry Wurst");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            //Create string array
           /* Name = new String[]{"Spagetti Bolognese", "vegetarische Lasagna", "Nackensteak vom Schwein", "Wok Gemüse",
                    "Reismilsch mit Vannile", "Curry Wurst", "Gemüse Kebap", "Spagetti Carbonara", "Tonno Pizza",
                    "Schweine Gulasch", "Chilli con Carne", "Gemüse Käse", "Rinder Bouletten"};*/

            //Listview
            listView = (ListView)findViewById(R.id.list);

            //Set Data
            for (String mealName : mNameList){
                MealName MN = new MealName(mealName);
                nameArrayList.add(MN);
            }

            //The custom adapter
            adapter = new ListViewAdapter(this, nameArrayList);

            //Listview
            listView.setAdapter(adapter);

            //Edittext
            editText = (EditText)findViewById(R.id.Edt);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    //Search or Filter
                    String who = editText.getText().toString().toLowerCase(Locale.getDefault());
                    adapter.myFilter(who);
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            //spinner1 dropdown menu
            spinner1 = (Spinner)findViewById(R.id.spinner1);
            adapter1 = ArrayAdapter.createFromResource(this, R.array.allergie,android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);
            spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"selected",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //spinner2 dropdown menu
            spinner2 = (Spinner)findViewById(R.id.spinner2);
            adapter1 = ArrayAdapter.createFromResource(this, R.array.preis,android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter1);
            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"selected",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //spinner3 dropdown menu
            spinner3 = (Spinner)findViewById(R.id.spinner3);
            adapter1 = ArrayAdapter.createFromResource(this, R.array.preis,android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner3.setAdapter(adapter1);
            spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getBaseContext(),parent.getItemIdAtPosition(position)+"selected",Toast.LENGTH_LONG).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            //spinner1 enabled/disabled
            Switch switch1 = (Switch)findViewById(R.id.switch1);
            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        spinner1.setEnabled(true);
                    }else{
                        spinner1.setEnabled(false);
                    }
                }
            });
            //spinner2 u. 3 enabled/disabled
            Switch switch2 = (Switch)findViewById(R.id.switch2);
            switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        spinner2.setEnabled(true);
                        spinner3.setEnabled(true);
                    }else{
                        spinner2.setEnabled(false);
                        spinner3.setEnabled(false);
                    }
                }
            });

        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            this.finish();
        }


    }

