package OpenMensa.api.modelbuilders;

import com.google.gson.JsonObject;

import java.io.IOException;

import OpenMensa.api.model.Canteen;
import OpenMensa.api.model.DayStatus;

public final class DayStatusJSON extends JsonConverter{


    public static DayStatus translate(String json) throws IOException{
        JsonObject object = JsonStringAsObject(json);
        return translate(object);
    }

    public static DayStatus translate(JsonObject object) throws IOException {

        String status = getFieldAsString("closed",object);

        if (status == "false") return new DayStatus(false);
        else if (status == "true") return new DayStatus(true);
        else return null;
    }
}
