package lab3.strategy;

import java.util.HashMap;
import java.util.Map;

public class HashMapOccurrencesNumber implements OccurrencesNumber {

    @Override
    public Map<Integer, Integer> calculate(int[] arr) {
        Map<Integer, Integer> res = new HashMap<>();
        for (int el : arr) {
            if(res.containsKey(el)) {
                res.put(el, res.get(el)+1);
            } else {
                res.put(el, 1);
            }
        }
        return res;
    }
}
