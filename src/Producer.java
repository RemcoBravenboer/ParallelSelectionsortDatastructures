import java.util.Arrays;
import java.util.concurrent.*;

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
    private static final int[] NUMBERS_COUNT = {100000, 200000, 300000, 400000, 500000, 600000, 700000, 800000, 900000, 1000000};

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        int numberCount = 500000;
        queue = new ArrayBlockingQueue<>(numberCount);
        int [] array = Numbers.GenerateNumberArr(numberCount);
        long timeBefore = System.currentTimeMillis();
        Producer prod1 = new Producer(queue,array);
        Consumer cons1 = new Consumer(queue);
        executorService.submit(prod1);
        executorService.submit(cons1);
        executorService.shutdown();

        executorService.awaitTermination(1, TimeUnit.MINUTES);

        if(executorService.isTerminated()){
            System.out.println("Result: " + Arrays.toString(queue.element()));
            System.out.println("Time: " + (System.currentTimeMillis() - timeBefore) + "ms");
        }
    }
}

class Consumer implements Runnable{
    protected BlockingQueue<int[]> queue;
    private static int[][] splitArray;
    private static int THREADS = 2;


    public Consumer(BlockingQueue<int []> queue){
        this.queue = queue;
    }

    Sort sort = new Sort();

    @Override
    public void run() {
        int[] numbers = queue.element();
        splitArray = fillSplitArray(THREADS, numbers);
        System.out.println(Arrays.deepToString(splitArray));
        for (int i = 0; i < splitArray.length; i++) {
            splitArray[i] = sort.SelectionSort(splitArray[i]);
        }
        connectSplitArrays(numbers, splitArray);

        numbers = sort.SelectionSort(numbers);

        queue.add(numbers);
    }

    static void connectSplitArrays(int[] numbers, int[][] splitArray) {
        int q = 0;
        for (int i = 0; i < splitArray.length; i++) {
            for (int j = 0; j < splitArray[i].length; j++) {
                numbers[q] = splitArray[i][j];
                q++;
            }
        }
    }

    private int[][] fillSplitArray(int threads, int[] listToUse) {
        if (listToUse.length == 0) {
            return new int[0][0];
        }

        int splitLength = (int) Math.ceil((double) listToUse.length / (double) threads);
        int[][] splits = new int[threads][];

        int j = 0;
        int k = 0;
        for (int i = 0; i < listToUse.length; i++) {
            if (k == splitLength) {
                k = 0;
                j++;
            }
            if (splits[j] == null) {
                int remainingNumbers = listToUse.length - i;
                splits[j] = new int[Math.min(remainingNumbers, splitLength)];
            }
            splits[j][k++] = listToUse[i];

        }
        return splits;
    }
}