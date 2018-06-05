package OpenMensa.api.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Menu {

    private String date; // String date statt Localdate lokaldate
    private Canteen canteen;
    private DayStatus status;
    private List<Meal> meals;

    public DayStatus getStatus() {
        return status;
    }

    public void setStatus(DayStatus status) {
        this.status = status;
    }

    public String getDate() { // String  statt Localdate
        Date d;
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    } // String date statt Localdate lokaldate

    public Canteen getCanteen() {
        return canteen;
    }

    public void setCanteen(Canteen canteen) {
        this.canteen = canteen;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public String toString(){

        String s = "Speiseplan für "+this.canteen.getName()+" am "+this.date.toString();
        s+="\n\n";
        for (Meal m :
                this.meals) {
            s+= m.toString();
            s+= "\n";
        }
        return s;
    }

    public List<Meal> meals1 = getMeals();




}
