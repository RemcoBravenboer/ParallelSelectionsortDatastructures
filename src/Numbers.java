import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class Numbers {
    static List<Integer> GenerateNumber(int numberCount) {
        List<Integer> temp = new ArrayList<>();
        for (int i = numberCount; i > 0; i--) {
            temp.add(i);
        }
        return temp;
    }

    static int[] GenerateNumberArr(int numberCount) {
        int[] temp = new int[numberCount];
        for (int i = numberCount; i > 0; i--) {
            temp[numberCount - i] = i;
        }

        return temp;
    }

}
