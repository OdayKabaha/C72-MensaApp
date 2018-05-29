package OpenMensa.api.helpers;

import org.xml.sax.ErrorHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import OpenMensa.api.OpenMensaAPI;
import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.Menu;

public class getMenuSchedule {

    public Menu today;
    public Menu tomorrow;
    public Menu in2days;

    private OpenMensaAPI api = new OpenMensaAPI();

    public getMenuSchedule(int canteenID){
        try{
            __init(api.getCanteenById(canteenID));
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    public getMenuSchedule(Canteen canteen){
        __init(canteen);
    }

    public void __init(Canteen canteen){




    }

    private void todaysDate(){
        Date c = Calendar.getInstance().getTime();


        //return c.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }






    public Menu getToday() {
        return today;
    }

    public Menu getTomorrow() {
        return tomorrow;
    }

    public Menu getIn2days() {
        return in2days;
    }



}
