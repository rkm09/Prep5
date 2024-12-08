package dailychallenge.medium;


import common.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxTwoEvents2054 {
    public static void main(String[] args) {
        int[][] events = {{1,3,2},{4,5,2},{2,4,3}};
        System.out.println(maxTwoEvents(events));
    }


//    min heap; time: O(nlogn), space: O(n)
    public static int maxTwoEvents(int[][] events) {
//        priority queue holding (end time, value)
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(Comparator.comparingInt(Pair::getKey));
//        sort array based on start time
        Arrays.sort(events, Comparator.comparingInt(a -> a[0]));
        int maxSum = 0, maxVal = 0;
//        process each of the events
        for(int[] event : events) {
//            poll all valid events (that have ended before the current event) and take their maximum
            while(!pq.isEmpty() && pq.peek().getKey() < event[0]) {
                maxVal = Math.max(maxVal, pq.poll().getValue());
            }
//            maxSum vs (the best seen so far + the current event)
            maxSum = Math.max(maxSum, maxVal + event[2]);
            pq.offer(new Pair<>(event[1], event[2]));
        }
        return maxSum;
    }

//    brute force; TLE!!
    public static int maxTwoEventsN(int[][] events) {
        int maxSum = Integer.MIN_VALUE;
        for(int i = 0 ; i < events.length ; i++) {
            for(int j = i + 1 ; j < events.length ; j++) {
                maxSum = Math.max(maxSum, Math.max(events[i][2], events[j][2]));
                if(isNotOverlapping(events[i], events[j])) {
                    maxSum = Math.max(maxSum, events[i][2] + events[j][2]);
                }
            }
        }
        return maxSum;
    }

    private static boolean isNotOverlapping(int[] a, int[] b) {
        return ((a[1] < b[0] || b[1] < a[0]));
    }
}

/*
You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei]. The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei. You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
Return this maximum sum.
Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time. More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

Example 1:
Input: events = [[1,3,2],[4,5,2],[2,4,3]]
Output: 4
Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
Example 2:
Example 1 Diagram
Input: events = [[1,3,2],[4,5,2],[1,5,5]]
Output: 5
Explanation: Choose event 2 for a sum of 5.
Example 3:
Input: events = [[1,5,3],[1,5,1],[6,6,5]]
Output: 8
Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.

Constraints:
2 <= events.length <= 105
events[i].length == 3
1 <= startTimei <= endTimei <= 109
1 <= valuei <= 106
 */