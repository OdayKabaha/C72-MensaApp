/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;
import java.time.LocalDate;
import OpenMensa.api.enums.DayStatusOpen;



public class DayStatus {

    private DayStatusOpen dayStatusOpen;

    public boolean isOpen() {
        return isOpen;
    }

    private boolean isOpen;

    public DayStatus(boolean isOpen){
        this.isOpen = isOpen;
        if (isOpen == true)
            this.dayStatusOpen = DayStatusOpen.OPEN;
        else this.dayStatusOpen = DayStatusOpen.CLOSED;
    }

    public DayStatus(DayStatusOpen dayStatusOpen) {
        this.dayStatusOpen = dayStatusOpen;
        if (this.dayStatusOpen == DayStatusOpen.OPEN)
            this.isOpen = true;
        else this.isOpen = false;
    }

    public DayStatusOpen getDayStatusOpen() {
        return dayStatusOpen;
    }
}
