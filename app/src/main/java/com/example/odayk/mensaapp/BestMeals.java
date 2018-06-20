package com.example.odayk.mensaapp;

public class BestMeals {
    boolean isSelected;
    String mealName;

    //create Constructor

    public BestMeals(boolean isSelected, String mealName) {
        this.isSelected = isSelected;
        this.mealName = mealName;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }
}
