/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.resources;

import fi.oulu.softwareconstruction.covid19.dataservice.models.Covid19Item;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/covid19")
public class Covid19Resource {
    
    @RequestMapping("/{country}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country) {
        return new Covid19Item(country);
    }
    
    @RequestMapping("/{country}/{date}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country, @PathVariable("date") String date) {
        return new Covid19Item(country, date);
    }
}
