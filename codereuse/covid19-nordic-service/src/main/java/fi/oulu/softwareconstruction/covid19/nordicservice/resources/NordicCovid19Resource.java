/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.nordicservice.resources;

import fi.oulu.softwareconstruction.covid19.nordicservice.models.Countries;
import fi.oulu.softwareconstruction.covid19.nordicservice.models.Country;
import fi.oulu.softwareconstruction.covid19.nordicservice.models.NordicCovid19ActiveItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/active")
public class NordicCovid19Resource {
    @Autowired
    private RestTemplate restTemplate;
    
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    @RequestMapping("/{date}")
    public NordicCovid19ActiveItem getNordicCovid19Active(@PathVariable("date") String date) {
        Countries countries = restTemplate.getForObject("http://covid19-country-service/countries/nordics", Countries.class);
        System.out.println("Got countries: " + countries);
        for (String countryCode: countries.getCodes()) {
            System.out.println("Get country name for " + countryCode);
            Country country = restTemplate.getForObject("http://covid19-country-service/country/" + countryCode, Country.class);
            System.out.println("Get data for " + country.getName() + " (" + date + ")");
        }
        return new NordicCovid19ActiveItem(date, 0);
    }
}
