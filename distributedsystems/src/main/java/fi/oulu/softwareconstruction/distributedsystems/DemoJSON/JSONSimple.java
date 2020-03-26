/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.distributedsystems.DemoJSON;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.json.*;

/**
 *
 * @author mclaes
 */
public class JSONSimple {
    public static void main(String args[]) throws FileNotFoundException {
        FileInputStream file = new FileInputStream("example.json");
        JsonReader rdr = Json.createReader(file);
        JsonObject obj = rdr.readObject();
        System.out.println(obj);
        System.out.println(obj.getString("firstName"));
        for (JsonObject phone: obj.getJsonArray("phoneNumbers").getValuesAs(JsonObject.class)) {
            System.out.println(phone.getString("type") + ": " + phone.getString("number"));
        }
        
        obj = Json.createObjectBuilder()
                .add("firstName", "Jane")
                .add("lastName", "Doe")
                .add("age", 42)
                .build();
        FileOutputStream fileOutput = new FileOutputStream("output.json");
        JsonWriter writer = Json.createWriter(fileOutput);
        writer.write(obj);
        writer.close();
    }
}
