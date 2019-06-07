package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try{
            RemoteInterface stub = new SayHelloRemote();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("reg",stub);
            System.out.println("Succes!");
//            Naming.rebind("rmi://localhost:8080/test",stub);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
