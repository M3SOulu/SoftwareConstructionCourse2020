/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.covid19.nordicservice.models;

import java.util.Set;

/**
 *
 * @author Maëlick Claes <himself@maelick.net>
 */
public class Countries {
    private String name;
    private Set<String> codes;
    
    public Countries() {
        
    }
    
    public Countries(String name, Set<String> codes) {
        this.name = name;
        this.codes = codes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getCodes() {
        return codes;
    }

    public void setCodes(Set<String> codes) {
        this.codes = codes;
    }
    
    public String toString() {
        return name + ": " + codes.toString();
    }
}