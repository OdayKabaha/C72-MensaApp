/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;
import java.time.LocalDate;
import OpenMensa.api.enums.DayStatusOpen;



public class DayStatus {

    private LocalDate date;
    private DayStatusOpen dayStatusOpen;

    public DayStatus(LocalDate date, DayStatusOpen dayStatusOpen) {
        this.date = date;
        this.dayStatusOpen = dayStatusOpen;
    }

    public LocalDate getDate() {
        return date;
    }

    public DayStatusOpen getDayStatusOpen() {
        return dayStatusOpen;
    }
}
