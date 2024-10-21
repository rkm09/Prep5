package dailychallenge.medium;

import java.util.HashSet;
import java.util.Set;

public class MaxUnique1593 {
    public static void main(String[] args) {
        String s = "ababccc";
        MaxUnique1593 m = new MaxUnique1593();
        System.out.println(m.maxUniqueSplit(s));
    }

//    backtracking + pruning; time: O(n.2^n), space: O(n)
    public int maxUniqueSplit(String s) {
        Set<String> seen = new HashSet<>();
        int[] maxCount = new int[1];
        backTrack(s, 0, seen, 0, maxCount);
        return maxCount[0];
    }

    private void backTrack(String s, int start, Set<String> seen, int count, int[] maxCount) {
//        Prune: if the current count + remaining characters can't exceed maxCount, return
        if((count + (s.length() - start)) < maxCount[0])
            return;
//        base case: if we reach the end of the string, update maxCount
        if(start == s.length()) {
            maxCount[0] = Math.max(maxCount[0], count);
            return;
        }
//        try every possible substring starting from 'start'
        for(int end = start + 1 ; end <= s.length() ; end++) {
            String substring = s.substring(start, end);
//            if the substring is unique
            if(!seen.contains(substring)) {
                seen.add(substring);
//                recursively count unique positions from the next position
                backTrack(s, end, seen, count + 1, maxCount);
//                backtrack; remove the substring from the seen set
                seen.remove(substring);
            }
        }
    }
}

/*
Given a string s, return the maximum number of unique substrings that the given string can be split into.
You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string. However, you must split the substrings such that all of them are unique.
A substring is a contiguous sequence of characters within a string.
Example 1:
Input: s = "ababccc"
Output: 5
Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid as you have 'a' and 'b' multiple times.
Example 2:
Input: s = "aba"
Output: 2
Explanation: One way to split maximally is ['a', 'ba'].
Example 3:
Input: s = "aa"
Output: 1
Explanation: It is impossible to split the string any further.

Constraints:
1 <= s.length <= 16
s contains only lower case English letters.

time complexity: pruning;
The algorithm uses backtracking to explore all possible unique substrings.
In the worst case, it may try every substring starting from each position in the string, which is exponential.
For each starting index start, the algorithm can create up to n substrings in the worst case (from start to the end of the string).
Thus, for each of the n starting positions, the algorithm potentially explores O(n) choices, leading to O(n⋅2^n) possibilities.
We might generate up to 2^n unique combinations of substrings, so the impact on the overall time complexity is encompassed in the O(n⋅2 ^n) term.

 */