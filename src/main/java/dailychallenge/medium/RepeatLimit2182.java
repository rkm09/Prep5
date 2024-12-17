package dailychallenge.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class RepeatLimit2182 {
    public static void main(String[] args) {
        String s = "cczazcc";
        System.out.println(repeatLimitedString(s, 3));
    }

//    heap optimized greedy character frequency distribution
//    time: O(nlogk), space: O(n) [n - length of s, k - number of unique characters in s]
    public static String repeatLimitedString(String s, int repeatLimit) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char c : s.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a,b) -> Character.compare(b,a));
        for(char key : freqMap.keySet())
            maxHeap.offer(key);
        StringBuilder result = new StringBuilder();
        while(!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            int count = freqMap.get(ch);
            int use = Math.min(count, repeatLimit);
            for(int i = 0 ; i < use ; i++)
                result.append(ch);
            freqMap.put(ch, count - use);
            if(freqMap.get(ch) > 0 && !maxHeap.isEmpty()) {
                char nextCh = maxHeap.poll();
                result.append(nextCh);
                freqMap.put(nextCh, freqMap.get(nextCh) - 1);
                if(freqMap.get(nextCh) > 0)
                    maxHeap.offer(nextCh);
                maxHeap.offer(ch);
            }
        }
        return result.toString();
    }
}

/*
You are given a string s and an integer repeatLimit. Construct a new string repeatLimitedString using the characters of s such that no letter appears more than repeatLimit times in a row. You do not have to use all characters from s.
Return the lexicographically largest repeatLimitedString possible.
A string a is lexicographically larger than a string b if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b. If the first min(a.length, b.length) characters do not differ, then the longer string is the lexicographically larger one.

Example 1:
Input: s = "cczazcc", repeatLimit = 3
Output: "zzcccac"
Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
The letter 'a' appears at most 1 time in a row.
The letter 'c' appears at most 3 times in a row.
The letter 'z' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row, so it is not a valid repeatLimitedString.
Example 2:
Input: s = "aababab", repeatLimit = 2
Output: "bbabaa"
Explanation: We use only some of the characters from s to construct the repeatLimitedString "bbabaa".
The letter 'a' appears at most 2 times in a row.
The letter 'b' appears at most 2 times in a row.
Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
The string is the lexicographically largest repeatLimitedString possible so we return "bbabaa".
Note that the string "bbabaaa" is lexicographically larger but the letter 'a' appears more than 2 times in a row, so it is not a valid repeatLimitedString.

Constraints:
1 <= repeatLimit <= s.length <= 105
s consists of lowercase English letters.
 */

/*
Time: O(nlogk)
The time complexity of this approach is dominated by the operations on the heap, which is used to efficiently access and modify the most frequent characters. The size of the heap is bounded by the number of unique characters, denoted as K, so the heap operations (push and pop) take O(logK) time.
In the worst case, we perform two heap operations for every character in the string, resulting in O(N) heap operations. Each heap operation involves pushing or popping an element, which takes O(logK) time.
Therefore, the overall time complexity of the solution is O(N⋅logK).
 */