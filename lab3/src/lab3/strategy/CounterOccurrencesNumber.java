package lab3.strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CounterOccurrencesNumber implements OccurrencesNumber{

    @Override
    public Map<Integer, Integer> calculate(int[] arr) {
        Map<Integer, Integer> res = new HashMap<>();
        Arrays.sort(arr);
        int counter = 1;
        int prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == prev) {
                counter ++;
            } else {
                res.put(prev, counter);
                counter = 1;
            }
            prev = arr[i];
        }
        return res;
    }

}
