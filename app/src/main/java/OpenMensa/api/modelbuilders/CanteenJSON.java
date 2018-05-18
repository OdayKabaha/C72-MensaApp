package OpenMensa.api.modelbuilders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import OpenMensa.api.model.Canteen;

public final class CanteenJSON extends JsonConverter{

    public static Canteen translate(String json) throws IOException{
        JsonObject object = JsonStringAsObject(json);
        return translate(object);
    }

    public static Canteen translate(JsonObject object) throws IOException{
        Canteen canteen = new Canteen();

        canteen.setId( getFieldAsInt("id",object));
        canteen.setName( getFieldAsString("name",object));
        canteen.setCity( getFieldAsString("city", object));
        canteen.setAddress(getFieldAsString("address", object));
        canteen.setCoordinates(getFieldAsDoubleArray("coordinates", object));

        return canteen;
    }

    public static List<Canteen> parse(String json)throws IOException{
        JsonObject[] arr = convertToJsonObjectArray(json);
        Canteen[] canteens = new Canteen[arr.length];

        for (int i = 0; i< arr.length ; i++)
        {
            canteens[i] = translate(arr[i]);
        }
        return Arrays.asList(canteens);
    }
}
