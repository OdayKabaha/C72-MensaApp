package com.example.odayk.mensaapp.mainpage;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.odayk.mensaapp.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.Meal;


public class GalleryActivity extends AppCompatActivity {
    OpenMensaAPI openMensaAPI = new OpenMensaAPI();
    ListView listView;

    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = dateFormat.format(date);

    private static final String TAG = "GalleryActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,new ArrayList<String>()));


        Log.d(TAG, "onCreate: started.");

        getIncomingIntent();

    }

    private void getIncomingIntent(){
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");

        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("image_name")){
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            String imageUrls = getIntent().getStringExtra("image_url");
            final String imageNames = getIntent().getStringExtra("image_name");

            new MealsHolder(imageNames).execute();
            setImage(imageUrls, imageNames);

        }
    }

    private void setImage(String imageUrls, String imageNames){
        Log.d(TAG, "setImage: setting the image and name to widgets.");

        TextView name = findViewById(R.id.image_description);
        name.setText(imageNames);
        ImageView image = findViewById(R.id.image);
        Glide.with(this)
                .asBitmap()
                .load(imageUrls)
                .into(image);
    }

    public class MealsHolder extends AsyncTask<Void, String, String> {
        OpenMensaAPI openMensaAPI = new OpenMensaAPI();
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String currentDate = dateFormat.format(date);

        ArrayAdapter<String> adapter;
        String mealName;

        MealsHolder(String mealName )
        {
            this.mealName = mealName;
        }

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
                        if (meal.getName().equals(mealName))
                        {
                            publishProgress("Kategorie: " + meal.getCategory().toString());
                            publishProgress("Preise:");
                            publishProgress("   Studenten: " + String.valueOf(meal.getPrices().getStudent()));
                            publishProgress("   Mitarbeiter: " + String.valueOf(meal.getPrices().getEmployee()));
                            publishProgress("   Andere: " + String.valueOf(meal.getPrices().getOther()));
                            publishProgress("Zutaten:");
                            for(i=0 ; i < meal.getNotes().size() ; i++)
                            {
                                publishProgress("   " + meal.getNotes().get(i));
                            }
                        }
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