/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.nordicservice.models;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
public class NordicCovid19ActiveItem {
    private String date;
    private int active;

    public NordicCovid19ActiveItem(String date, int active) {
        this.date = date;
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
