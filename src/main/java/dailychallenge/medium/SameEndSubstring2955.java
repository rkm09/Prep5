package dailychallenge.medium;

import java.util.Arrays;

public class SameEndSubstring2955 {
    public static void main(String[] args) {
        String s = "abcaab";
        int[][] queries = {{0,0},{1,4},{2,5},{0,5}};
        System.out.println(Arrays.toString(sameEndSubstringCount(s, queries)));
    }

//    prefix sum; time: O(n + q), space: O(n) [q - number of queries, n - length]
    public static int[] sameEndSubstringCount(String s, int[][] queries) {
        int n = s.length();
//        2d array to store the prefix sum of character frequencies for each character from 'a' to 'z'
        int[][] charFreqPrefixSum = new int[26][n];
//        fill the frequency array
        for(int i = 0 ; i < n ; i++)
            charFreqPrefixSum[s.charAt(i) - 'a'][i]++;
//        convert the frequency array to a prefix sum array
        for(int i = 0 ; i < 26 ; i++) {
            for(int j = 1 ; j < n ; j++) {
                charFreqPrefixSum[i][j] += charFreqPrefixSum[i][j - 1];
            }
        }

        int[] results = new int[queries.length];
//        process each query
        for(int i = 0 ; i < queries.length ; i++) {
            int leftIndex = queries[i][0];
            int rightIndex = queries[i][1];
            int sameEndSubstringsCnt = 0;
//            for each character calculate the frequency of occurrences within the query range
            for(int charIndex = 0 ; charIndex < 26 ; charIndex++) {
//                calculate the frequency within the query range
                int leftFreq = (leftIndex == 0) ? 0 : charFreqPrefixSum[charIndex][leftIndex - 1];
                int rightFreq = charFreqPrefixSum[charIndex][rightIndex];
                int frequencyInRange = rightFreq - leftFreq;
//                calculate the number of same end substrings for this character
                sameEndSubstringsCnt += (frequencyInRange * (frequencyInRange + 1)) / 2;
            }
            results[i] = sameEndSubstringsCnt;
        }

        return results;
    }

//    def; brute force; TLE
    public static int[] sameEndSubstringCount1(String s, int[][] queries) {
        int[] result = new int[queries.length];
        int k = 0;
        for(int[] query : queries) {
            int l = query[0], r = query[1];
            int count = 0;
            for(int i = l ; i <= r ; i++) {
                for(int j = l ; j <= i ; j++) {
                    if(s.charAt(j) == s.charAt(i))
                        count++;
                }
            }
            result[k++] = count;
        }
        return result;
    }
}

/*
You are given a 0-indexed string s, and a 2D array of integers queries, where queries[i] = [li, ri] indicates a substring of s starting from the index li and ending at the index ri (both inclusive), i.e. s[li..ri].
Return an array ans where ans[i] is the number of same-end substrings of queries[i].
A 0-indexed string t of length n is called same-end if it has the same character at both of its ends, i.e., t[0] == t[n - 1].
A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: s = "abcaab", queries = [[0,0],[1,4],[2,5],[0,5]]
Output: [1,5,5,10]
Explanation: Here is the same-end substrings of each query:
1st query: s[0..0] is "a" which has 1 same-end substring: "a".
2nd query: s[1..4] is "bcaa" which has 5 same-end substrings: "bcaa", "bcaa", "bcaa", "bcaa", "bcaa".
3rd query: s[2..5] is "caab" which has 5 same-end substrings: "caab", "caab", "caab", "caab", "caab".
4th query: s[0..5] is "abcaab" which has 10 same-end substrings: "abcaab", "abcaab", "abcaab", "abcaab", "abcaab", "abcaab", "abcaab", "abcaab", "abcaab", "abcaab".
Example 2:
Input: s = "abcd", queries = [[0,3]]
Output: [4]
Explanation: The only query is s[0..3] which is "abcd". It has 4 same-end substrings: "abcd", "abcd", "abcd", "abcd".

Constraints:
2 <= s.length <= 3 * 104
s consists only of lowercase English letters.
1 <= queries.length <= 3 * 104
queries[i] = [li, ri]
0 <= li <= ri < s.length

We don't actually need to know the exact positions of the characters to count the number of substrings that have the same character repeated within the range.
Instead, we just need to know how often that character appears.
One of the best ways to efficiently find the frequency of something in a range is by using a prefix sum array.
A prefix sum array is like a running total; it gives you the sum of all elements up to and including that index.
So, if we make an array that tracks how often a certain character shows up in a string, the prefix sum for that array
will give us the total occurrences of that character up to any point in the string.
To do this, we'll first make frequency arrays for all 26 letters of the alphabet. Each position in these arrays represents
a spot in the string. We'll put a 1 if the character appears there or a 0 if it doesn’t.
Once we have those frequency arrays, we’ll turn them into prefix sum arrays. This just means we add up all the values
before each position, so now every position tells us how many times that character has appeared up to that point in
the string. With that done, we can go through our queries. For each query, we can easily find how often a character appears between two positions by subtracting the prefix sum at the left boundary from the prefix sum at the right boundary. Using that frequency,
we can then calculate how many substrings are possible for that character and do the same for all the characters.
 */
