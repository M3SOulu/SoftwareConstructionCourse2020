package fi.oulu.softwareconstruction.distributedsystems.DemoHTTP;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class SimpleWebClient {
    public static void main(String args[]) throws IOException {
        URL url = new URL("https://www.oulu.fi/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.connect();
        System.out.println("Respone code for " + url + ": " + con.getResponseCode());
        InputStreamReader input = new InputStreamReader(con.getInputStream());
        BufferedReader buffer = new BufferedReader(input);

        System.out.println("Result:");
        for (String line = buffer.readLine(); line != null; line = buffer.readLine()) {
            System.out.println(line);
        }
        input.close();
    }
}