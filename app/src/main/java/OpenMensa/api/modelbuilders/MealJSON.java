package OpenMensa.api.modelbuilders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.Meal;
import OpenMensa.api.model.Prices;

public final class MealJSON extends JsonConverter{


    public static Meal translate(String json) throws IOException{
        JsonObject object = JsonStringAsObject(json);
        return translate(object);
    }

    public static Meal translate(JsonObject object) throws IOException{
        Meal meal = new Meal();

        meal.setId( getFieldAsInt("id",object));
        meal.setName( getFieldAsString("name",object));
        meal.setCategory( getFieldAsString("category", object));
        meal.setNotes(getFieldAsStringList("notes", object));

        JsonObject priceObject = getFieldAsJsonObject("prices",object);
        Prices prices = new Prices();
        prices.setStudent(priceObject.get("students").getAsDouble());
        prices.setEmployee(priceObject.get("employees").getAsDouble());
        //prices.setPupil(priceObject.get("pupils").getAsDouble());
        prices.setOther(priceObject.get("others").getAsDouble());

        meal.setPrices(prices);

        return meal;
    }

    public static List<Meal> parse(String json)throws IOException{
        JsonObject[] arr = convertToJsonObjectArray(json);
        Meal[] meals = new Meal[arr.length];

        for (int i = 0; i< arr.length ; i++)
        {
            meals[i] = translate(arr[i]);
        }
        return Arrays.asList(meals);
    }
}
