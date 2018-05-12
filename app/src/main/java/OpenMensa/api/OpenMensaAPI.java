package OpenMensa.api;

import java.io.IOException;
import java.util.List;

import OpenMensa.api.dataprovider.OpenMensaDataProvider;

public class OpenMensaAPI {
    private OpenMensaDataProvider dataProvider;

    //constructor
    public OpenMensaAPI(OpenMensaDataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    /**
     * Gets a list if all canteens.
     *
     * @return The list of canteens.
     * @throws IOException On failing to retrieve data from the API.
     */
    public String getCanteens() throws IOException {
        String json = dataProvider.queryAPI("/canteens");
        //List<CanteenPojo> canteenPojos = JsonConverter.canteensFromJson(json);
        //return CanteenConverter.convertList(canteenPojos);
        return json;
    }
}
