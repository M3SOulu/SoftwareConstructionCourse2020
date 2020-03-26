/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.oulu.softwareconstruction.distributedsystems.DemoRMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class App {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1888);
            Hello stub = (Hello) registry.lookup("Hello");
            String response = stub.sayHello("World");
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}