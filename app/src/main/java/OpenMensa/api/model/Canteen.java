/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;

public class Canteen {


    private int id;
    private String city;
    private String name;
    private String address;
    private GeographicCoordinates coordinates;

    public int getId() {
        return id;
    }

    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCoordinates(double[] arr) {
        this.coordinates = new GeographicCoordinates(arr[0],arr[1]);
    }

    public String toString(){
        return " id: "+this.id +"\n name: "+this.name+"\n city: "+this.city+"\n adress: "+this.address+ "\n coordinates: "+this.coordinates.toString();
    }
}
