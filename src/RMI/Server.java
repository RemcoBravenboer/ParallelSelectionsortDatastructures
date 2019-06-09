package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

public class Server {

    public static void main(String[] args) {
        try {
            long before = System.currentTimeMillis();
            Random rd = new Random(); //
            int[] numbers = new int[200000];
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = rd.nextInt(100000 - 1) + 1;
            }
            Service stub = new SelectionSortRemote();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("reg", stub);

            numbers = stub.sort(numbers);
            printArray(numbers);
            System.out.println("Time: " + (System.currentTimeMillis() - before));

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    // Prints the array
    public static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
}
