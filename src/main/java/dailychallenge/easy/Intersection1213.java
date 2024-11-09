package dailychallenge.easy;

import java.util.*;

public class Intersection1213 {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,2,5,7,9};
        int[] arr3 = {1,3,4,5,8};
        System.out.println(arraysIntersection(arr1, arr2, arr3));
    }

//    brute force with TreeMap; time: O(n), space: O(n)
    public static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
//        note: we need to take treemap instead to maintain order
        Map<Integer, Integer> counter = new TreeMap<>();
        List<Integer> result = new ArrayList<>();
        for(int a : arr1)
            counter.put(a, counter.getOrDefault(a, 0) + 1);
        for(int a : arr2)
            counter.put(a, counter.getOrDefault(a, 0) + 1);
        for(int a : arr3)
            counter.put(a, counter.getOrDefault(a, 0) + 1);
        for(int key : counter.keySet()) {
            if(counter.get(key) == 3)
                result.add(key);
        }
        return result;
    }
}

/*
Given three integer arrays arr1, arr2 and arr3 sorted in strictly increasing order, return a sorted array of only the integers that appeared in all three arrays.
Example 1:
Input: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
Output: [1,5]
Explanation: Only 1 and 5 appeared in the three arrays.
Example 2:
Input: arr1 = [197,418,523,876,1356], arr2 = [501,880,1593,1710,1870], arr3 = [521,682,1337,1395,1764]
Output: []

Constraints:
1 <= arr1.length, arr2.length, arr3.length <= 1000
1 <= arr1[i], arr2[i], arr3[i] <= 2000
 */