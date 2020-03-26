/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.nordicservice.resources;

import fi.oulu.softwareconstruction.covid19.nordicservice.models.Covid19Item;
import fi.oulu.softwareconstruction.covid19.nordicservice.models.Countries;
import fi.oulu.softwareconstruction.covid19.nordicservice.models.Country;
import fi.oulu.softwareconstruction.covid19.nordicservice.models.NordicCovid19ActiveItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestClientException;
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
        Countries countries = fetchCountries();
        int active = 0;
        for (String countryCode: countries.getCodes()) {
            Country country = fetchCountryCode(countryCode);
            active += fetchData(country, date).getActive();
        }
        return new NordicCovid19ActiveItem(date, active);
    }

    private Covid19Item fetchData(Country country, String date) throws RestClientException {
        final String url = "http://covid19-data-service/covid19/" + country.getName() + "/" + date;
        System.out.println("Get data for " + country.getName() + " (" + date + ")");
        Covid19Item item = restTemplate.getForObject(url, Covid19Item.class);
        System.out.println(item);
        return item;
    }

    private Country fetchCountryCode(String countryCode) throws RestClientException {
        System.out.println("Get country name for " + countryCode);
        final String url = "http://covid19-country-service/country/" + countryCode;
        return restTemplate.getForObject(url, Country.class);
    }

    private Countries fetchCountries() throws RestClientException {
        final String url = "http://covid19-country-service/countries/nordics";
        Countries countries = restTemplate.getForObject(url, Countries.class);
        System.out.println("Got countries: " + countries);
        return countries;
    }
}
