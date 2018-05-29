package OpenMensa.api.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import OpenMensa.api.model.Meal;
import OpenMensa.api.model.Menu;

public class MealWrapper {

    private Meal m;
    private HashMap<String, List<String>> MealMap;

    public MealWrapper(){
        this.MealMap = new HashMap<String, List<String>> ();
    }

    public MealWrapper(Menu menu){
        this.MealMap = new HashMap<String, List<String>> ();

        for (Meal meal: menu.getMeals())
        {
            this.addMeal(meal);
        }
    }

    public void addMeal(Meal meal){
        List<String> childs = new ArrayList<>();
        String headerName = meal.getName();
        for (String s: meal.getNotes())
        {
            childs.add(s);
        }

        this.MealMap.put(headerName,childs);
    }

    public HashMap<String, List<String>> getMealMap(){
        return this.MealMap;
    }
}
