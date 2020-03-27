/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.resources;

import fi.oulu.softwareconstruction.covid19.dataservice.models.Covid19Item;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/covid19")
public class Covid19Resource {
    @Autowired
    private RestTemplate restTemplate;
    
    private Map<String, List<Covid19Item>> dataset;
    
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    private Map<String, List<Covid19Item>> fetchDataset() {
        final String url = "https://pomber.github.io/covid19/timeseries.json";
        System.out.println("Fetch dataset from " + url);
        Map<String, List<Covid19Item>> result = parseDataset(restTemplate.getForObject(url, Map.class));
        System.out.println(result.size() + " items parsed");
        return result;
    }

    private Map<String, List<Covid19Item>> parseDataset(Map<String, List<Map<String, Object>>> data) {
        Map<String, List<Covid19Item>> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry<String, List<Map<String, Object>>> entry: data.entrySet()) {
            List<Covid19Item> list = new LinkedList<>();
            for (Map<String, Object> item: entry.getValue()) {
                Covid19Item itemObject = new Covid19Item(entry.getKey(), item);
                if (itemObject.getConfirmed() != null &&
                    itemObject.getDeaths() != null &&
                    itemObject.getRecovered() != null)
                list.add(itemObject);
            }
            result.put(entry.getKey(), list);
        }
        return result;
    }
    
    @Scheduled(fixedRate = 3600 * 1000)
    public void updateDataset() {
        this.dataset = this.fetchDataset();
    }
    
    @RequestMapping("/{country}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country) {
        if (this.dataset.containsKey(country)) {
            List<Covid19Item> countryItems = this.dataset.get(country);
            if (countryItems.size() > 0) {
                return countryItems.get(countryItems.size() - 1);
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No data found for " + country
        );
    }
    
    @RequestMapping("/{country}/{date}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country, @PathVariable("date") String date) {
        if (this.dataset.containsKey(country)) {
            for (Covid19Item item: this.dataset.get(country)) {
                if (item.getDate().equals(date)) {
                    return item;
                }
            }
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Invalid date " + date + " for " + country
            );
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No data found for " + country
            );
        }
    }
}
