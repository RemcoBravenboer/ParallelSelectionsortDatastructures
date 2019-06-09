package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
    int[] sort(int[] arr) throws RemoteException;
}
