package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        final String host = "localhost";
        try {
//            LocateRegistry.createRegistry(8080);
            Registry registry = LocateRegistry.getRegistry(host, 8080);
            RemoteInterface stub = (RemoteInterface) registry.lookup("reg");
            System.out.println(stub.sayHello());
            System.out.println("Success? ");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Fail!");
        }
    }
}
