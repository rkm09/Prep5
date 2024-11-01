package dailychallenge.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class MakeFancy1957 {
    public static void main(String[] args) {
        String s = "aaabaaaa";
        System.out.println(makeFancyString(s));
    }

//    time: O(n), space: O(n)
    public static String makeFancyString(String s) {
        char prev = s.charAt(0);
        StringBuilder sb = new StringBuilder();
        sb.append(prev);
        int frequency = 1;
        for(int i =  1 ; i < s.length() ; i++) {
            char curr = s.charAt(i);
            if(curr == prev) {
                frequency++;
            } else {
                frequency = 1;
                prev = curr;
            }
            if(frequency < 3)
                sb.append(curr);
        }
        return sb.toString();
    }

//    def; stack; time: O(n), space: O(n)
    public static String makeFancyString1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            stack.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            int times = 1;
            char c1 = stack.pop();
            sb.append(c1);
            while(!stack.isEmpty() && c1 == stack.peek()) {
                times++;
                c1 = stack.pop();
                if(times == 3) {
                    times--;
                    continue;
                }
                sb.append(c1);
            }
        }
        return sb.toString();
    }
}

/*
A fancy string is a string where no three consecutive characters are equal.
Given a string s, delete the minimum possible number of characters from s to make it fancy.
Return the final string after the deletion. It can be shown that the answer will always be unique.


Example 1:
Input: s = "leeetcode"
Output: "leetcode"
Explanation:
Remove an 'e' from the first group of 'e's to create "leetcode".
No three consecutive characters are equal, so return "leetcode".
Example 2:
Input: s = "aaabaaaa"
Output: "aabaa"
Explanation:
Remove an 'a' from the first group of 'a's to create "aabaaaa".
Remove two 'a's from the second group of 'a's to create "aabaa".
No three consecutive characters are equal, so return "aabaa".
Example 3:
Input: s = "aab"
Output: "aab"
Explanation: No three consecutive characters are equal, so return "aab".

Constraints:
1 <= s.length <= 105
s consists only of lowercase English letters.
 */