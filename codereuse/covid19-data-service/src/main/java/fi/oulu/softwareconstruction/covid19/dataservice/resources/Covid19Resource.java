/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.dataservice.resources;

import com.mongodb.MongoClient;
import fi.oulu.softwareconstruction.covid19.dataservice.models.Covid19Item;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Collation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
@RestController
@RequestMapping("/covid19")
public class Covid19Resource {
    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoTemplate mongoTemplate;
    
    private Map<String, List<Covid19Item>> dataset;
    
    @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
    }
    
    @Bean
    MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient, "covid19");
    }
    
    @RequestMapping("/{country}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country) {
        Query query = new Query()
                .addCriteria(Criteria.where("country").is(country))
                .with(Sort.by(Direction.DESC, "date"))
                .collation(Collation.of("en").strength(1));
        Covid19Item result = mongoTemplate().findOne(query, Covid19Item.class);
        if (result == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No data found for " + country
            );
        } else {
            return result;
        }
    }
    
    @RequestMapping("/{country}/{date}")
    public Covid19Item getCovid19Data(@PathVariable("country") String country, @PathVariable("date") String date) {
        Query query = new Query()
                .addCriteria(Criteria.where("country").is(country))
                .addCriteria(Criteria.where("date").is(date))
                .collation(Collation.of("en").strength(1));
        Covid19Item result = mongoTemplate().findOne(query, Covid19Item.class);
        if (result == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No data found for " + country + " " + date
            );
        } else {
            return result;
        }
    }
}
