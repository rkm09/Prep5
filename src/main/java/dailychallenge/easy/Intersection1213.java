package dailychallenge.easy;

import java.util.*;

public class Intersection1213 {
    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,2,5,7,9};
        int[] arr3 = {1,3,4,5,8};
        System.out.println(arraysIntersection(arr1, arr2, arr3));
    }

//    three pointers; time: O(n), space: O(1)
//    since input is already sorted, we don't need to maintain a frequency map;
//    - each time, we should increment the pointer that points to the smallest number, i.e., min(arr1[p1], arr2[p2], arr3[p3]) forward;
//    - if the numbers pointed to by p1, p2, and p3 are the same, we should then store it and move all three pointers forward.
//    we don't have to move the pointer pointing to the smallest number - we only need to move the pointer pointing to a smaller number.
//    In this way, we avoid comparing three numbers and finding the smallest one before deciding which one to move.
    public static List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();
        int p1 = 0, p2 = 0, p3 = 0;
        int n1 = arr1.length, n2 = arr2.length, n3 = arr3.length;
        while(p1 < n1 && p2 < n2 && p3 < n3) {
            if(arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                result.add(arr1[p1]);
                p1++; p2++; p3++;
            } else if(arr1[p1] < arr2[p2]) {
                p1++;
            } else if(arr2[p2] < arr3[p3]) {
                p2++;
            } else {
                p3++;
            }
        }
        return result;
    }

//    brute force with TreeMap; time: O(n), space: O(n)
    public static List<Integer> arraysIntersection1(int[] arr1, int[] arr2, int[] arr3) {
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