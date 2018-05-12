/* Copyright (c) 2017, Lukas Tobler
 * GNU Lesser General Public License v3.0 (see LICENSE or https://www.gnu.org/licenses/lgpl-3.0.txt)
 */

package OpenMensa.api.model;

import java.util.Currency;

public class Prices {

    private Currency currency;
    private double student;
    private double employee;
    private double pupil;
    private double other;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getStudent() {
        return student;
    }

    public void setStudent(double student) {
        this.student = student;
    }

    public double getEmployee() {
        return employee;
    }

    public void setEmployee(double employee) {
        this.employee = employee;
    }

    public double getPupil() {
        return pupil;
    }

    public void setPupil(double pupil) {
        this.pupil = pupil;
    }

    public double getOther() {
        return other;
    }

    public void setOther(double other) {
        this.other = other;
    }
}
