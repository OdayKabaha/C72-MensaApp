/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;

public class GeographicCoordinates {

    private double latitude;
    private double longitude;

    public GeographicCoordinates(double latitude, double longitude) throws IllegalArgumentException {
        if (latitude > 180.0 || latitude < -180.0) {
            throw new IllegalArgumentException("Invalid latitude: must be in range [-180.0,180.0]");
        } else if (longitude > 180.0 || longitude < -180.0) {
            throw new IllegalArgumentException("Invalid longitude: must be in range [-180.0,180.0]");
        }

        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
