/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.distributedsystems.DemoHTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;

import javax.json.*;

public class SimpleJSONClient {
    public static void main(String args[]) throws IOException {
        URL url = new URL("http://localhost:8080/hello");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        
        System.out.println("Respone code for " + url + ": " + con.getResponseCode());
        System.out.println("Result:");
        
        JsonReader input = Json.createReader(con.getInputStream());
        JsonObject object = input.readObject();
        System.out.println(object.getString("response"));
        input.close();
    }
}
