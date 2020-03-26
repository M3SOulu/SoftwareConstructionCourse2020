/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.countryservice.resources;

import fi.oulu.softwareconstruction.covid19.countryservice.models.Country;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/country")
public class CountryResource {
    
    @RequestMapping("/{code}")
    public Country getCountryInfo(@PathVariable("code") String code) {
        final Country country = new Country(code);
        switch(code) {
            case "fi":
                country.setName("Finland");
                break;
            case "se":
                country.setName("Sweden");
                break;
            case "no":
                country.setName("Norway");
                break;
            default:
                throw new IllegalArgumentException(
                        "Country code '" + code +
                        "' unkown or not handled");
        }
        return country;
    }
}
