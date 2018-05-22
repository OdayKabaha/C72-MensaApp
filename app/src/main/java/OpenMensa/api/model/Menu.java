package OpenMensa.api.model;

import java.time.LocalDate;
import java.util.List;

public class Menu {

    private LocalDate date;
    private Canteen canteen;
    private DayStatus status;
    private List<Meal> meals;

    public DayStatus getStatus() {
        return status;
    }

    public void setStatus(DayStatus status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

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


}
