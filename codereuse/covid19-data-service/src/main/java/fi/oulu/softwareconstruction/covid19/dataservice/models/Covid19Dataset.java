/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.models;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
public class Covid19Dataset {
    private Map<String, List<Covid19Item>> dataset;
    
    public Covid19Dataset() {
        
    }

    public Map<String, List<Covid19Item>> getDataset() {
        return dataset;
    }

    public void setDataset(Map<String, List<Covid19Item>> dataset) {
        this.dataset = dataset;
    }
    
    public Covid19Item getItem(String country) {
        List<Covid19Item> timeseries = this.dataset.get(country);
        return timeseries.get(0);
    }
    
    public Covid19Item getItem(String country, String date) {
        List<Covid19Item> timeseries = this.dataset.get(country);
        return timeseries.get(0);
    }
}
