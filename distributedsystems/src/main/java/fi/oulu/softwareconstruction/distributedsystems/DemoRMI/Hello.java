package fi.oulu.softwareconstruction.distributedsystems.DemoRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello(String name) throws RemoteException;
}