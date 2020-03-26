/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.nordicservice.resources;

import fi.oulu.softwareconstruction.covid19.nordicservice.models.NordicCovid19ActiveItem;
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
    
    @RequestMapping("/{date}")
    public NordicCovid19ActiveItem getNordicCovid19Active(@PathVariable("date") String date) {
        RestTemplate restTemplate = new RestTemplate();
        
        Countries countries = restTemplate.getForObject("http://localhost:8080/countries/nordics", Countries.class);
        return new NordicCovid19ActiveItem(date, 0);
    }
}
