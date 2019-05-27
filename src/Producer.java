import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    protected BlockingQueue<Integer> queue;

    public Producer(BlockingQueue<Integer> queue){
        this.queue = queue;
    }

    @Override
    public void run() {

    }
}
