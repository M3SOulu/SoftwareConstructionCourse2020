/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.models;

import java.util.Map;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
public class Covid19Item {
    private String country;
    private String date;
    private Integer confirmed = 0;
    private Integer deaths = 0;
    private Integer recovered = 0;

    public Covid19Item(String country) {
        this.country = country;
    }

    public Covid19Item(String country, String date) {
        this.country = country;
        this.date = date;
    }

    public Covid19Item(String country, Map<String, Object> item) {
        this.country = country;
        if (item.containsKey("date")) {
            this.date = (String) item.get("date");
        }
        if (item.containsKey("confirmed")) {
            this.confirmed = (Integer) item.get("confirmed");
        }
        if (item.containsKey("deaths")) {
            this.deaths = (Integer) item.get("deaths");
        }
        if (item.containsKey("recovered")) {
            this.recovered = (Integer) item.get("recovered");
        }
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public Integer getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }
    
    public Integer getActive() {
        return this.confirmed - (this.deaths + this.recovered);
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
