/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.dataprovider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import OpenMensa.api.dataprovider.OpenMensaDataProvider;

public class OpenMensaOrg implements OpenMensaDataProvider {

    private static final String API_URL = "http://openmensa.org/api/v2";

    public String queryAPI(String query) throws IOException {
        URL url = new URL(API_URL + query);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) {
            throw new IOException("Server responded with: " + responseCode + " " + connection.getResponseMessage());
        }

        InputStream input = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        input.close();
        return response.toString();
    }
}
