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
public class Covid19Item {
    private String country;
    private String date;
    private int active = 0;
    
    public Covid19Item() {
        
    }

    public Covid19Item(String country) {
        this.country = country;
    }

    public Covid19Item(String country, String date) {
        this.country = country;
        this.date = date;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String toString() {
        return this.country + " (" + this.date + "): " + this.active + " active";
    }
}
