/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;

import java.util.List;

public class Meal {

    private int id;
    private String name;
    private List<String> notes;
    private Prices prices;
    private String category;

    public int getId() {
        return id;
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

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Prices getPrices() {
        return prices;
    }

    public void setPrices(double[] prices) {
        this.prices = new Prices(prices);
    }

    public void setPrices(Prices prices) {
        this.prices = prices;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String toString(){
        try{
            String ret = " id: "+ this.id + "\n name: "+ this.name + "\n category: "+ this.category;
            ret += "\n Notes \n [ ";
            for (String s : this.notes)
            {
                ret += "\n \t "+s;
            }
            ret += "\n ]";
            ret += "prices: \n "+this.prices.toString()+"\n";
            return ret;
        }
        catch(Exception e){ return ""; }

    }
}
