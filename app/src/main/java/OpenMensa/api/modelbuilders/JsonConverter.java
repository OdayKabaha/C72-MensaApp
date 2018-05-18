package OpenMensa.api.modelbuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.Meal;


public class JsonConverter {
    static Gson gson = new GsonBuilder().create();

    static public JsonObject JsonStringAsObject(String jsonString){

        JsonElement jsonElement = gson.fromJson(jsonString, JsonElement.class);
        JsonObject object = jsonElement.getAsJsonObject();
        return object;
    }

    static public String getFieldAsString(String id, JsonObject object){
        JsonElement element = object.get(id);
        String s = null;
        try {
            s = element.getAsString();
        }
        catch(Exception e) {}
        return s;
    }

    static public int getFieldAsInt(String id, JsonObject object){
        JsonElement element = object.get(id);
        int i = 0;
        try {
            i = element.getAsInt();
        }
        catch(Exception e) {}
        return i;
    }

    static public double getFieldAsDouble(String id, JsonObject object){
        JsonElement element = object.get(id);
        double i = 0;
        try {
            i = element.getAsDouble();
        }
        catch(Exception e) {}
        return i;
    }

    static public List<String> getFieldAsStringList(String id, JsonObject object){
        JsonArray jsonArray = object.get(id).getAsJsonArray();
        String[] arr = new String[jsonArray.size()];
        for (int i = 0; i < jsonArray.size() ; i++) {
            JsonElement element = jsonArray.get(i);
            arr[i] = (element.getAsString());
        }
        return Arrays.asList(arr);
    }

    static public double[] getFieldAsDoubleArray(String id, JsonObject object){
        JsonArray jsonArray = object.get(id).getAsJsonArray();
        double[] arr = new double[jsonArray.size()];
        for (int i = 0; i < jsonArray.size() ; i++) {
            JsonElement element = jsonArray.get(i);
            arr[i] = (element.getAsDouble());
        }
        return arr;
    }

    static public JsonObject getFieldAsJsonObject(String id, JsonObject object){
        JsonObject jOb = object.get(id).getAsJsonObject();
        return jOb;
    }

    static public JsonObject[] convertToJsonObjectArray(String json){

        Type collectionType = new TypeToken<Collection<JsonObject>>(){}.getType();
        Collection<JsonObject> objects = gson.fromJson(json, collectionType);

        JsonObject[] arr = new JsonObject[objects.size()];

        int i = 0;
        for (JsonObject object : objects)
        {
            arr[i] = object;
            i++;
        }
        return arr;
    }
}
