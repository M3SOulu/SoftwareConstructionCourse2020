/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.models;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
public class Covid19Item {
    private String country;
    private String date;
    private int confirmed = 0;
    private int deaths = 0;
    private int recovered = 0;

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

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
    
    public int getActive() {
        return this.confirmed - (this.deaths + this.recovered);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
