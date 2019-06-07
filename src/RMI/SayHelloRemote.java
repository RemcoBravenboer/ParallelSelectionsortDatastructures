package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SayHelloRemote extends UnicastRemoteObject implements RemoteInterface {
    SayHelloRemote() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello";
    }
}
