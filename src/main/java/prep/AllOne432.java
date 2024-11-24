package prep;

import java.util.HashMap;
import java.util.Map;

public class AllOne432 {
}

class AllOne {
    Map<String, Integer> freqMap;
    public AllOne() {
        freqMap = new HashMap<>();
    }

    public void inc(String key) {
        freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
    }

    public void dec(String key) {
        freqMap.put(key, freqMap.get(key) - 1);
    }

    public String getMaxKey() {
        int max = 0;
        for(String key : freqMap.keySet()) {
            max = Math.max(max, freqMap.get(key));
        }
        for(String key : freqMap.keySet()) {
            if(freqMap.get(key) == max)
                return key;
        }
        return "";
    }

    public String getMinKey() {
        int min = Integer.MAX_VALUE;
        for(String key : freqMap.keySet()) {
            min = Math.min(min, freqMap.get(key));
        }
        for(String key : freqMap.keySet()) {
            if(freqMap.get(key) == min)
                return key;
        }
        return "";
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */