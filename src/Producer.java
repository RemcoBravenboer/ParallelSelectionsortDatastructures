import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    protected static BlockingQueue<int[]> queue;
    private int[] oneSideOfTheArray;

    public Producer(BlockingQueue<int[]> queue, int[] oneSideOfTheArray) {
        this.queue = queue;
        this.oneSideOfTheArray = oneSideOfTheArray;
    }

    @Override
    public void run() {
        try {
            queue.put(this.oneSideOfTheArray);
            System.out.println("Producer #" + Arrays.toString(this.oneSideOfTheArray));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        queue = new ArrayBlockingQueue<>(100);
        int [] array = new int[]{1,9,8,7,9,4,2,5,45,78,4,6,3,1,4,5,2,3,5};
        Producer prod1 = new Producer(queue,array);
        Consumer cons1 = new Consumer(queue);
        prod1.run();
        cons1.run();
    }

}

class Consumer implements Runnable{
    protected BlockingQueue<int[]> queue;

    public Consumer(BlockingQueue<int []> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
            //queue.take();
            System.out.println("Consumer takes " + Arrays.toString(queue.element()));

    }
}
