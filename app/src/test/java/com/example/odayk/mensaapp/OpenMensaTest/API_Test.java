package com.example.odayk.mensaapp.OpenMensaTest;



import com.example.odayk.mensaapp.MainActivity;
import com.example.odayk.mensaapp.SpeisePlan;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.dataprovider.OpenMensaOrg;
import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.DayStatus;
import OpenMensa.api.model.Meal;
import OpenMensa.api.model.Menu;
import OpenMensa.api.modelbuilders.CanteenJSON;
import OpenMensa.api.modelbuilders.DayStatusJSON;
import OpenMensa.api.modelbuilders.MealJSON;




import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class API_Test {

    private OpenMensaAPI testAPI;
    private boolean report = true;

    @Before
    public void initialize() {
        this.testAPI = new OpenMensaAPI();
    }




    @Test
    public void local_date(){
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat ft = new SimpleDateFormat("MM-dd-YYYY");
        System.out.println(ft.format(c));
    }

    @Test
    public void print_categories() {
        int htw_id = 24;
        LocalDate heute = LocalDate.now();

        Menu m = new Menu();

        boolean result = true;
        try {
            Canteen htw = testAPI.getCanteenById(htw_id);
            m = testAPI.temp_getMenuFromCanteenByDate(htw, "29-05-2018");
        }
        catch(IOException e){

        }

        for (Meal meal :
                m.getMeals())
        {
            System.out.println(meal.getNotes().toString());
        }

        assertThat(true, is(true));
    }

    /**
     * hier Oday
     */
/*
    @Test
    public void test_MenuVonHeute() throws IOException {
        int htw_id = 24;
        LocalDate heute = LocalDate.now();

        Menu m = new Menu();

        boolean result = true;
        try {
            Canteen htw = testAPI.getCanteenById(htw_id);
            m = testAPI.getMenuFromCanteenByDate(htw, heute);

            if (m.getCanteen() != htw) result = false;
//             if (m.getStatus().isOpen() && m.getMeals()==null) result = false;

            System.out.print(m.toString());
        } catch (IOException e) {
            result = false;
        }
        assertThat(result, is(true));
    }*/


    // tests if the canteen list can be downloaded
    @Test
    public void canteen_avail() {
        String response = null;
        try {
            response = testAPI.getCanteens();
            System.out.print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(response, notNullValue());
    }


    // tests if a single Canteen can be build from a JSON object
    @Test
    public void test_CanteenJSON() {
        boolean result = true;
        String testJSON = "{\"id\":1,\"name\":\"Mensa UniCampus Magdeburg\",\"city\":\"Magdeburg\",\"address\":\"Pfälzer Str. 1, 39106 Magdeburg\",\"coordinates\":[52.1396188273019,11.6475999355316]}";
        try {
            Canteen c = CanteenJSON.translate(testJSON);
        } catch (Exception e) {
            System.out.println("Error occured in test_CanteenJSON: " + e.toString());
            result = false;
        }
        assertThat(result, is(true));
    }

    // tests if a Json collection of Canteens can be translated to Canteen objects
    @Test
    public void test_CanteenParser() throws IOException {
        String json = testAPI.getCanteens();
        boolean result = true;
        try {
            List<Canteen> canteens = CanteenJSON.parse(json);
            if (report) {
                System.out.println("Results of parsed Canteens: ");
                for (Canteen c : canteens) {
                    System.out.println(c);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occured in test_CanteenParser: " + e.toString());
            result = false;
        }
        assertThat(result, is(true));
    }


    // tests if a meal list can be downloaded
    @Test
    public void Meals_avail() {
        String response = null;
        try {
            response = testAPI.getSomeMealsFromHTW();
            System.out.print(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(response, notNullValue());
    }

    // tests if a single Meal can be build from a JSON object
    @Test
    public void test_MealJSON() {
        boolean result = true;
        String testJSON = "{\"id\":3242761,\"name\":\"Antipasti\",\"category\":\"Vorspeisen\",\"prices\":{\"students\":3.45,\"employees\":3.45,\"pupils\":null,\"others\":3.45},\"notes\":[\"geschwärzt\",\"vegan\",\"grün (Ampel)\"]}";

        try {
            Meal m = MealJSON.translate(testJSON);
        } catch (Exception e) {
            System.out.println("Error occured in test_CanteenJSON: " + e.toString());
            result = false;
        }
        assertThat(result, is(true));
    }

    // tests if a Json collection of Canteens can be translated to Canteen objects
    @Test
    public void test_MealParser() throws IOException {
        String json = testAPI.getSomeMealsFromHTW();
        boolean result = true;
        try {
            List<Meal> meals = MealJSON.parse(json);
            if (report) {
                System.out.println("Results of parsed Canteens: ");
                for (Meal m : meals) {
                    System.out.println(m);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occured in test_CanteenParser: " + e.toString());
            result = false;
        }
        assertThat(result, is(true));
    }
/*
    @Test
    public void test_DayStatus() throws IOException {

        int htw_id = 24;
        LocalDate heute = LocalDate.now();

        boolean result = true;

        try {
            DayStatus status = testAPI.getDayStatusFromCanteenByDate(24, LocalDate.now());
            if (status == null) result = false;
        } catch (Exception e) {
            e.fillInStackTrace();
            result = false;
        }

        assertThat(result, is(true));
    }*/
@Test
    public void testMenu() throws IOException {
    OpenMensaAPI openMensaAPI = new OpenMensaAPI();
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd . MM . yyyy");
    String currentDate = dateFormat.format(date);

    Menu result = openMensaAPI.getMenuFromCanteenByDate(24,currentDate);

    assertThat(result, notNullValue());
}



    @Test
    public void testMenu2() {
        int htw_id = 24;
        LocalDate heute = LocalDate.now();

        Menu m = null;// = new Menu();

        boolean result = true;
        try {
            Canteen htw = testAPI.getCanteenById(htw_id);
            m = testAPI.getMenuFromCanteenByDate(htw, "29-05-2018");
        }
        catch(IOException e){

        }

        for (Meal meal :
                m.getMeals())
        {
            System.out.println(meal.getNotes().toString());
        }

        assertThat(m.getMeals().size() > 0, is(true));
    }

}