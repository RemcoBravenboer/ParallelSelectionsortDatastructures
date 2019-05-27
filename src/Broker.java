import java.util.concurrent.BlockingQueue;

public class Broker implements Runnable{
    protected BlockingQueue<Integer> input;
    protected BlockingQueue<Integer> output;

    public Broker(BlockingQueue<Integer>input, BlockingQueue<Integer>output ){
        this.input = input;
        this.output = output;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int value = input.take();
                int newValue = value-50; // take out commission
                output.put(newValue);

                System.out.println(" Broker takes "+value + " and puts "+ newValue);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
