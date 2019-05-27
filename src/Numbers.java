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

}
