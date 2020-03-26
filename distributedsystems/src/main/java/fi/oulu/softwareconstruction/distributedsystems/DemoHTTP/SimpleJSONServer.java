package fi.oulu.softwareconstruction.distributedsystems.DemoHTTP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;

class MyJSONServer implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        JsonObject value = Json.createObjectBuilder()
                .add("response", "Hello World!")
                .build();
        
        t.sendResponseHeaders(200, 0);
        JsonWriter output = Json.createWriter(t.getResponseBody());
        System.out.println(value.toString());
        output.writeObject(value);
        output.close();
    }
}

public class SimpleJSONServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/hello", new MyJSONServer());
        server.start();
    }
}
