package fi.oulu.softwareconstruction.distributedsystems.DemoHTTP;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

class MyHTMLServer implements HttpHandler {
    private final String rootDir;
    
    public MyHTMLServer(String rootDir) {
        this.rootDir = rootDir;
    }
    
    @Override
    public void handle(HttpExchange request) throws IOException {
        System.out.println("Handling client");
        System.out.println(request.getRequestMethod());
        
        String requested = request.getRequestURI().getPath();
        File file = new File(this.rootDir + requested);
        System.out.println(requested + " was requested");
        
        if (file.exists()) {
            System.out.println(file + " exists");
            request.sendResponseHeaders(200, 0);
            OutputStream output = request.getResponseBody();
            Files.copy(file.toPath(), output);
            output.close();
            System.out.println(file + " sent to client");
        } else {
            System.out.println(file + " doesn't exists, sending error 404");
            request.sendResponseHeaders(404, 0);
            request.close();
        }
    }
}

class HelloServer implements HttpHandler {
    @Override
    public void handle(HttpExchange request) throws IOException {
        System.out.println("Handling client");
        System.out.println(request.getRequestMethod());
        //System.out.println(request.getRequestHeaders().keySet());
        //System.out.println(request.getRequestHeaders().values());
        
        String requested = request.getRequestURI().getPath();
        System.out.println(requested + " was requested");
        
        request.sendResponseHeaders(200, 0);
        PrintStream output = new PrintStream(request.getResponseBody());
        output.println("Hello " + requested.substring(7) + "!");
        output.close();
    }
}

public class SimpleHTMLServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", new MyHTMLServer(System.getProperty("user.dir")));
        server.createContext("/hello/", new HelloServer());
        server.start();
    }
}
