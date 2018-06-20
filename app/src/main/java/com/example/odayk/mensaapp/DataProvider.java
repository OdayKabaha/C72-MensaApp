package com.example.odayk.mensaapp;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.Meal;

class DataProvider {
    public static HashMap<String, List<String>> getInfo() {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        final String currentDate = dateFormat.format(date);
        final HashMap<String, List<String>> HeaderDetails = new HashMap<String, List<String>>();
        final OpenMensaAPI openMensaAPI = new OpenMensaAPI();
        final OpenMensa.api.model.Menu menu = new OpenMensa.api.model.Menu();


            final List<String> ChildDetails1 = new ArrayList<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Canteen canteen = openMensaAPI.getCanteenById(24);
                        ChildDetails1.add(String.valueOf(canteen.getId()));
                        ChildDetails1.add(canteen.getCity());
                        ChildDetails1.add(canteen.getName());
                        ChildDetails1.add(canteen.getAddress());
                       // HeaderDetails.put(canteen.getName(), ChildDetails1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            final List<String> ChildDetails2 = new ArrayList<>();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Canteen canteen = openMensaAPI.getCanteenById(30);
                        ChildDetails2.add(canteen.getName());
                        ChildDetails2.add(String.valueOf(canteen.getId()));
                        ChildDetails2.add(canteen.getCity());
                        ChildDetails2.add(canteen.getName());
                        ChildDetails2.add(canteen.getAddress());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            final List<String> ChildDetails3 = new ArrayList<>();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        final List<Meal> meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                        if (meals != null) {
                            for (Meal meal : meals){
                                ChildDetails3.add(meal.getName());
                                                            }
                        } else {
                            ChildDetails3.add("List is empty");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();



            final List<String> ChildDetails4 = new ArrayList<>();
            ChildDetails4.add("This is Children 41");
            ChildDetails4.add("This is Children 42");
            ChildDetails4.add("This is Children 43");
            ChildDetails4.add("This is Children 44");

            final List<String> ChildDetails5 = new ArrayList<>();
            ChildDetails5.add("This is Children 51");
            ChildDetails5.add("This is Children 52");
            ChildDetails5.add("This is Children 53");
            ChildDetails5.add("This is Children 54");

            final List<String> ChildDetails6 = new ArrayList<>();
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
            HeaderDetails.put("Header 7", ChildDetails6);

/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> ChildDetails3 = new ArrayList<>();
                try {
                    final List<Meal> meals = openMensaAPI.getMenuFromCanteenByDate(24, currentDate).getMeals();
                    if (meals != null) {
                        for (Meal meal : meals){
                            HeaderDetails.put(meal.getName(), ChildDetails3);
                        }
                    } else {
                        ChildDetails3.add("List is empty");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
*/

        return HeaderDetails;

    }
}
