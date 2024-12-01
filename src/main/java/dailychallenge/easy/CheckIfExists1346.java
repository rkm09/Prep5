package dailychallenge.easy;

import java.util.*;

public class CheckIfExists1346 {
    public static void main(String[] args) {
        int[] arr = {10,2,5,3};
        System.out.println(checkIfExist(arr));
    }

//    def; time: O(nlogn), space: O(n)
    public static boolean checkIfExist(int[] arr) {
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