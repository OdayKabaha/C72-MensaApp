package OpenMensa.api.modelbuilders;

import OpenMensa.api.enums.MealCategory;

public final class CategoryTranslator {
    public static MealCategory translate(String s){
        MealCategory category = null;
        switch(s){
            case "Vorspeisen":
                category = MealCategory.Vorspeise; return category;
            case "Salate":
                category = MealCategory.Salat; return category;
            case "Aktionen":
                category = MealCategory.Aktion; return category;
            case "Essen":
                category = MealCategory.Hauptgericht; return category;
            case "Beilagen":
                category = MealCategory.Beilage; return category;
            case "Desserts":
                category = MealCategory.Dessert; return category;
            default: return null;
        }
    }

    public static String toString(MealCategory m){
        MealCategory category = null;
        switch(m){
            case Vorspeise:
                return "Vorspeise";
            case Salat:
                return "Salat";
            case Aktion:
                return "Aktion";
            case Hauptgericht:
                return "Hauptgericht";
            case Beilage:
                return "Beilage";
            case Dessert:
                return "Dessert";
            default: return "";
        }
    }
}
