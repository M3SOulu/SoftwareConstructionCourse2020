/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.countryservice.resources;

import fi.oulu.softwareconstruction.covid19.countryservice.models.Countries;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/countries")
public class CountriesResource {
    
    @RequestMapping("/{name}")
    public Collection<String> getCountries(@PathVariable("name") String name) {
        if (name.equals("nordics")) {
            String[] codes = {"fi", "no", "se"};
            return new HashSet<>(Arrays.asList(codes));
        } else {
            throw new IllegalArgumentException("Unkown country set '" + name + "'");
        }
    }
}
