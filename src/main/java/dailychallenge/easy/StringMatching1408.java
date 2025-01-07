package dailychallenge.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class StringMatching1408 {
    public static void main(String[] args) {
        String[] words = {"mass","as","hero","superhero"};
        String[] words1 = {"leetcoder","leetcode","od","hamlet","am"};
        System.out.println(stringMatching(words1));
    }

    public static List<String> stringMatching(String[] words) {
        
    }

//    def; brute force;
    public static List<String> stringMatchingN(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> res = new ArrayList<>();
        for(int i = 0 ; i < words.length ; i++) {
            String current = words[i];
            for(int j = i + 1 ; j < words.length ; j++) {
                if(current.length() == words[j].length()) continue;
                if(words[j].contains(current)) {
                    res.add(current); break;
                }
            }
        }
        return res;
    }
}

/*
Given an array of string words, return all strings in words that is a substring of another word. You can return the answer in any order.
A substring is a contiguous sequence of characters within a string
Example 1:
Input: words = ["mass","as","hero","superhero"]
Output: ["as","hero"]
Explanation: "as" is substring of "mass" and "hero" is substring of "superhero".
["hero","as"] is also a valid answer.
Example 2:
Input: words = ["leetcode","et","code"]
Output: ["et","code"]
Explanation: "et", "code" are substring of "leetcode".
Example 3:
Input: words = ["blue","green","bu"]
Output: []
Explanation: No string of words is substring of another string.

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 30
words[i] contains only lowercase English letters.
All the strings of words are unique.
 */