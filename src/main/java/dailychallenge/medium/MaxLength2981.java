package dailychallenge.medium;

import common.Pair;

import java.util.HashMap;
import java.util.Map;

public class MaxLength2981 {
    public static void main(String[] args) {
        String s = "aaaa";
        System.out.println(maximumLength(s));
    }

//    hashmap; time: O(n^2), space: O(n^2)
    public static int maximumLength(String s) {
        Map<Pair<Character, Integer>, Integer> freqMap = new HashMap<>();
        for(int start = 0 ; start < s.length() ; start++) {
            char currentChar = s.charAt(start);
            int substringLength = 0;
            for(int end = start ; end < s.length() ; end++) {
                if(s.charAt(end) == currentChar) {
                    substringLength++;
                    Pair<Character, Integer> key = new Pair<>(currentChar, substringLength);
                    freqMap.put(key, freqMap.getOrDefault(key, 0) + 1);
                } else { // important step if not consecutive
                    break;
                }
            }
        }
        int maxLength = 0;
        for(Pair<Character, Integer> key : freqMap.keySet()) {
            int count = freqMap.get(key);
            int length = key.getValue();
            if(count >= 3 && length > maxLength)
                maxLength = length;
        }
        return maxLength == 0 ? -1 : maxLength;
    }
}

/*
You are given a string s that consists of lowercase English letters.
A string is called special if it is made up of only a single character. For example, the string "abc" is not special, whereas the strings "ddd", "zz", and "f" are special.
Return the length of the longest special substring of s which occurs at least thrice, or -1 if no special substring occurs at least thrice.
A substring is a contiguous non-empty sequence of characters within a string.
Example 1:
Input: s = "aaaa"
Output: 2
Explanation: The longest special substring which occurs thrice is "aa": substrings "aaaa", "aaaa", and "aaaa".
It can be shown that the maximum length achievable is 2.
Example 2:
Input: s = "abcdef"
Output: -1
Explanation: There exists no special substring which occurs at least thrice. Hence return -1.
Example 3:
Input: s = "abcaba"
Output: 1
Explanation: The longest special substring which occurs thrice is "a": substrings "abcaba", "abcaba", and "abcaba".
It can be shown that the maximum length achievable is 1.

Constraints:
3 <= s.length <= 50
s consists of only lowercase English letters.
 */

/*
Let n be the length of the string s.
Time Complexity: O(n^2)
The algorithm generates all substrings of the input string s using two nested loops. The outer loop runs n times. For each iteration of the outer loop, the inner loop iterates n - end times, where end is the index of the outer loop. This means the total number of iterations is the sum of the first n natural numbers, which equals n⋅(n+1)/2. Therefore, the time complexity for generating all substrings is O(n^2).
For each substring, the algorithm checks and updates the frequency of the pair in a map, which takes O(1) time.
Therefore, the overall time complexity of the algorithm is given by O(n^2).

Space complexity: O(n^2)
The algorithm uses a map to store all unique substrings and their frequencies. In the worst case, such as when all characters in the string are identical, the total number of substrings can go up to n⋅(n+1)/2.
Additionally, each substring requires space proportional to its length, leading to an overall space requirement of O(n^2) in the worst case.
Therefore, the total space complexity of the algorithm is O(n^2).
 */
