/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.countryservice.resources;

import fi.oulu.softwareconstruction.covid19.countryservice.models.Countries;
import java.util.Arrays;
import java.util.HashSet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/countries")
public class CountriesResource {
    
    @RequestMapping("/{name}")
    public Countries getCountries(@PathVariable("name") String name) {
        if (name.equals("nordics")) {
            String[] codes = {"fi", "no", "se"};
            return new Countries("nordics", new HashSet<>(Arrays.asList(codes)));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Unkown country set '" + name + "'"
            );
        }
    }
}
