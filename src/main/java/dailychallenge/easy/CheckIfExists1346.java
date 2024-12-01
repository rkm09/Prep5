package dailychallenge.easy;

import java.util.*;

public class CheckIfExists1346 {
    public static void main(String[] args) {
        int[] arr = {10,2,5,3};
        System.out.println(checkIfExist(arr));
    }

//    hashset; time: O(n), space: O(n)
    public static boolean checkIfExist(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        for(int num : arr) {
            if(seen.contains(num * 2) || ((num % 2 == 0) && seen.contains(num / 2)))
                return true;
            seen.add(num);
        }
        return false;
    }

//    frequency map; time: O(n), space: O(n)
//    since this problem allows negative numbers, a frequency map is a better choice compared to freq array
    public static boolean checkIfExist1(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for(int num : arr)
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        for(int num : arr) {
            if(num != 0 && freqMap.containsKey(num * 2))
                return true;
            if(num == 0 && freqMap.get(num) > 1)
                return true;
        }
        return false;
    }


//    sorting + binary search; time: O(nlogn), space: O(n)
    public static boolean checkIfExist2(int[] arr) {
        Arrays.sort(arr);
        for(int i = 0 ; i < arr.length ; i++) {
            int target = arr[i] * 2;
            int index = binarySearch(arr, target);
            if(index >= 0 && index != i) {
                return true;
            }
        }
        return false;
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(arr[mid] == target)
                return mid;
            if(arr[mid] < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return -1;
    }

//    def; time: O(nlogn), space: O(n)
    public static boolean checkIfExist3(int[] arr) {
        Set<Integer> set = new HashSet<>();
        List<Integer> li = new ArrayList<>();
        for (int j : arr) li.add(j);
        li.sort(Comparator.comparingInt(Math::abs));
        for(int i = li.size() - 1; i >= 0 ; i--) {
            int num = li.get(i);
            if(set.contains(num * 2))
                return true;
            set.add(num);
        }
        return false;
    }
}

/*
Given an array arr of integers, check if there exist two indices i and j such that :
i != j
0 <= i, j < arr.length
arr[i] == 2 * arr[j]
Example 1:
Input: arr = [10,2,5,3]
Output: true
Explanation: For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
Example 2:
Input: arr = [3,1,7,11]
Output: false
Explanation: There is no i and j that satisfy the conditions.

Constraints:
2 <= arr.length <= 500
-103 <= arr[i] <= 103

 */