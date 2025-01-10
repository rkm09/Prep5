package dailychallenge.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordSubsets916 {
    public static void main(String[] args) {
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"e","o"};
        String[] words22 = {"lo","eo"};
        System.out.println(wordSubsets(words1,words22));
    }

//    reduce to single word in b; time: O(a + b), space: O(a + b) [fast]
    public static List<String> wordSubsets(String[] words1, String[] words2) {
        int[] words2Max = count("");
        for(String word : words2) {
            int[] words2Count = count(word);
            for(int i = 0 ; i < 26 ; i++) {
//                consider the max frequency of a character in any word
                words2Max[i] = Math.max(words2Max[i], words2Count[i]);
            }
        }
        List<String> res = new ArrayList<>();
        search: for(String word : words1) {
            int[] words1Count = count(word);
            for(int i = 0 ; i < 26 ; i++) {
                if(words2Max[i] > words1Count[i]) continue search;
            }
            res.add(word);
        }
        return res;
    }

    private static int[] count(String word) {
        int[] ans = new int[26];
        for(char c : word.toCharArray())
            ans[c - 'a']++;
        return ans;
    }

//    def using map;
    public static List<String> wordSubsets1(String[] words1, String[] words2) {
        Map<Character, Integer> words2MaxMap = new HashMap<>();
        for(String word : words2) {
            Map<Character, Integer> words2Map = new HashMap<>();
            for(char c : word.toCharArray()) {
                int count = words2Map.getOrDefault(c, 0) + 1;
                words2Map.put(c, count);
                words2MaxMap.put(c, Math.max(words2MaxMap.getOrDefault(c, 0), count));
            }
        }
        List<String> res = new ArrayList<>();
        search: for(String word : words1) {
            Map<Character, Integer> words1Map = new HashMap<>();
            for(char c : word.toCharArray()) {
                words1Map.put(c, words1Map.getOrDefault(c, 0) + 1);
            }
            for(char key : words2MaxMap.keySet()) {
                int words2Count = words2MaxMap.get(key);
                int words1Count = words1Map.getOrDefault(key, 0);
                if(words2Count > words1Count) continue search;
            }
            res.add(word);
        }
        return res;
    }
}

/*
You are given two string arrays words1 and words2.
A string b is a subset of string a if every letter in b occurs in a including multiplicity.
For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.
Return an array of all the universal strings in words1. You may return the answer in any order.
Example 1:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]
Example 2:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["l","e"]
Output: ["apple","google","leetcode"]

Constraints:
1 <= words1.length, words2.length <= 104
1 <= words1[i].length, words2[i].length <= 10
words1[i] and words2[i] consist only of lowercase English letters.
All the strings of words1 are unique.
 */
