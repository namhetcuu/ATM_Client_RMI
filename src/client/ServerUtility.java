package client;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Services.ITinhToan;

public class ServerUtility {

    public static ITinhToan getServerInstance() throws Exception {
    	Registry registry = LocateRegistry.getRegistry("localhost",1099);
    	return (ITinhToan) registry.lookup("TinhToan");

    }
}