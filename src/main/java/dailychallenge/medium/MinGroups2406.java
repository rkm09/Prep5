package dailychallenge.medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinGroups2406 {
    public static void main(String[] args) {
        int[][] intervals = {{5,10},{6,8},{1,5},{2,3},{1,10}};
        System.out.println(minGroups(intervals));
    }

//    sorting and prefix sum; time: O(nlogn), space: O(n)
//    sufficient to calculate the max overlapping intervals, the non overlapping can fit in one of these groups
    public static int minGroups(int[][] intervals) {
//        convert intervals as {start, 1} and {end + 1, -1} ; "end + 1" since end is of inclusive type
        List<int[]> events = new ArrayList<>();
        for(int[] interval : intervals) {
            events.add(new int[] {interval[0], 1});
            events.add(new int[] {interval[1] + 1, -1});
        }
//        sort events first by time then by type
        events.sort((a, b) -> {
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[0], b[0]);
            }
        });
        int concurrentIntervals = 0;
        int maxConcurrentIntervals = 0;
//        sweep through the events
        for(int[] event : events) {
//            track currently active intervals
            concurrentIntervals += event[1];
//            update max
            maxConcurrentIntervals = Math.max(maxConcurrentIntervals, concurrentIntervals);
        }
        return maxConcurrentIntervals;
    }
}

/*
You are given a 2D integer array intervals where intervals[i] = [lefti, righti] represents the inclusive interval [lefti, righti].
You have to divide the intervals into one or more groups such that each interval is in exactly one group, and no two intervals that are in the same group intersect each other.
Return the minimum number of groups you need to make.
Two intervals intersect if there is at least one common number between them. For example, the intervals [1, 5] and [5, 8] intersect.

Example 1:
Input: intervals = [[5,10],[6,8],[1,5],[2,3],[1,10]]
Output: 3
Explanation: We can divide the intervals into the following groups:
- Group 1: [1, 5], [6, 8].
- Group 2: [2, 3], [5, 10].
- Group 3: [1, 10].
It can be proven that it is not possible to divide the intervals into fewer than 3 groups.
Example 2:
Input: intervals = [[1,3],[5,6],[8,10],[11,13]]
Output: 1
Explanation: None of the intervals overlap, so we can put all of them in one group.

Constraints:
1 <= intervals.length <= 105
intervals[i].length == 2
1 <= lefti <= righti <= 106
 */