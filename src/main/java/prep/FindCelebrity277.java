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
//    With each call to knows(...), we can conclusively determine that exactly 1 of the people is not a celebrity(line 23)!
//    which is why the brute force of O(n^2) can be avoided as the isCelebrity check need not be done for all n
//    line 24, we just find the possible candidate and then only for this possible candidate we do the isCelebrity check
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

/*
Suppose you are at a party with n people labeled from 0 to n - 1 and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know the celebrity, but the celebrity does not know any of them.
Now you want to find out who the celebrity is or verify that there is not one. You are only allowed to ask questions like: "Hi, A. Do you know B?" to get information about whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
You are given an integer n and a helper function bool knows(a, b) that tells you whether a knows b. Implement a function int findCelebrity(n). There will be exactly one celebrity if they are at the party.
Return the celebrity's label if there is a celebrity at the party. If there is no celebrity, return -1.
Note that the n x n 2D array graph given as input is not directly available to you, and instead only accessible through the helper function knows. graph[i][j] == 1 represents person i knows person j, wherease graph[i][j] == 0 represents person j does not know person i.
Example 1:
Input: graph = [[1,1,0],[0,1,0],[1,1,1]]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:
Input: graph = [[1,0,1],[1,1,0],[0,1,1]]
Output: -1
Explanation: There is no celebrity.

Constraints:
n == graph.length == graph[i].length
2 <= n <= 100
graph[i][j] is 0 or 1.
graph[i][i] == 1
Follow up: If the maximum number of allowed calls to the API knows is 3 * n, could you find a solution without exceeding the maximum number of calls?
 */