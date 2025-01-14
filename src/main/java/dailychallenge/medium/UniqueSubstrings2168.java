package dailychallenge.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UniqueSubstrings2168 {
    public static void main(String[] args) {
        String s = "1212";
        System.out.println(equalDigitFrequency(s));
    }

    public static int equalDigitFrequency(String s) {
        int n = s.length();
        int count = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            char c = s.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            StringBuilder sb = new StringBuilder(String.valueOf(c));
            for(int j = i + 1 ; j < n ; j++) {
                char c1 = s.charAt(j);

            }
        }
        System.out.println(freqMap);
        return count;
    }
}

/*
Given a digit string s, return the number of unique substrings of s where every digit appears the same number of times.
Example 1:
Input: s = "1212"
Output: 5
Explanation: The substrings that meet the requirements are "1", "2", "12", "21", "1212".
Note that although the substring "12" appears twice, it is only counted once.
Example 2:
Input: s = "12321"
Output: 9
Explanation: The substrings that meet the requirements are "1", "2", "3", "12", "23", "32", "21", "123", "321".

Constraints:
1 <= s.length <= 1000
s consists of digits.
 */
