package prep;


import common.Pair;

import java.util.HashMap;
import java.util.Map;

public class FindCelebrity277 extends Relation {
    Map<Pair<Integer, Integer>, Boolean> cache = new HashMap<>();
    int numberOfPeople;
    public static void main(String[] args) {
        FindCelebrity277 c = new FindCelebrity277();
        System.out.println(c.findCelebrity(3));
    }

//    logical deduction with caching; time: O(n), space: O(n)
    public int findCelebrity(int n) {
        this.numberOfPeople = n;
        int candidateCelebrity = 0;
        for(int i = 1 ; i < numberOfPeople ; i++) {
            if(knows(candidateCelebrity, i)) {
                candidateCelebrity = i;
            }
            if(isCelebrity(candidateCelebrity))
                return candidateCelebrity;
        }
        return -1;
    }

    public boolean isCelebrity(int i) {
        for(int j = 0 ; j < numberOfPeople ; j++) {
            if(i == j) continue;  // don't ask if they know themselves :)
            if(knows(i,j) || !knows(j,i))
                return false;
        }
        return true;
    }

    @Override
    boolean knows(int a, int b) {
        if(!cache.containsKey(new Pair<>(a,b))) {
            cache.put(new Pair<>(a,b), super.knows(a,b));
        }
        return cache.get(new Pair<>(a,b));
    }
}

class Relation {
    boolean knows(int a, int b) {
        if(a == 0 && b == 1) return true;
        if(a == 1 && b == 0) return false;
        if(a == 1 && b == 2) return false;
        if(a == 2 && b == 1) return true;
        return false;
    }
}