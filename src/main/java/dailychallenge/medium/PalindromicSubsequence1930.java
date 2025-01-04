package dailychallenge.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PalindromicSubsequence1930 {
    public static void main(String[] args) {
        String s = "aabca";
        System.out.println(countPalindromicSubsequence(s));
    }

//    precompute first and last indices; time: O(n), space: O(1)
    public static int countPalindromicSubsequence(String s) {
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        for(int i = 0 ; i < s.length() ; i++) {
            int curr = s.charAt(i) - 'a';
            if(first[curr] == -1)
                first[curr] = i;
            last[curr] = i;
        }
        int ans = 0;
        for(int i = 0 ; i < 26 ; i++) {
            if(first[i] == -1) continue;
            Set<Character> between = new HashSet<>();
            for(int j = first[i] + 1 ; j < last[i] ; j++)
                between.add(s.charAt(j));
            ans += between.size();
        }
        return ans;
    }

//    count in-between letters; time: O(n), space: O(1)
    public static int countPalindromicSubsequence1(String s) {
        Set<Character> letters = new HashSet<>();
        for(char c : s.toCharArray())
            letters.add(c);
        int ans = 0;
        for(Character letter : letters) {
            int left = -1, right = 0;
            for(int i = 0 ; i < s.length() ; i++) {
                if(s.charAt(i) == letter) {
                    if(left == -1)
                        left = i;
                    right = i;
                }
            }
//            count the number of unique characters in between the two occurrences of letter
            Set<Character> between = new HashSet<>();
            for(int i = left + 1 ; i < right ; i++) {
                between.add(s.charAt(i));
            }
            ans += between.size();
        }
        return ans;
    }
}

/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
A palindrome is a string that reads the same forwards and backwards.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".
Example 1:
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
Example 3:
Input: s = "bbcbaba"
Output: 4
Explanation: The 4 palindromic subsequences of length 3 are:
- "bbb" (subsequence of "bbcbaba")
- "bcb" (subsequence of "bbcbaba")
- "bab" (subsequence of "bbcbaba")
- "aba" (subsequence of "bbcbaba")

Constraints:
3 <= s.length <= 105
s consists of only lowercase English letters.
 */


/*
There is only one possible form a palindrome with length 3 can take. The first and last character must be the same, and the character in the middle can be anything (including the same character as the first/last character).
The important thing to notice here is that the first and last characters must be the same. To solve this problem, we can focus on each letter of the alphabet letter and treat it as the first and last character. Then, we find how many characters we can put in between them to form a palindrome.
There may be many occurrences of a given letter in s. Which ones should we choose? We should choose the first occurrence of letter in s to be the first character in our palindrome, and the last occurrence of letter in s to be the last character in our palindrome. Why?
The problem wants us to find subsequences - so when we look for a character to put as the middle character in the palindrome, this character must also be in between our two occurrences in s. Thus, by choosing the first and last occurrence, we are maximizing the number of characters in between, and thus maximizing the number of potential palindromes we could form.
For each unique letter in s, we find i as the first index where letter occurs and j as the final index where letter occurs. Next, we look at all the characters between indices i and j (the range of [i + 1, j - 1]) and count how many unique letters there are. Each of these unique letters can form a palindrome by being between two letter.
Time complexity: O(n)
To create letters, we use O(n) time to iterate over s.
Next, we iterate over each letter in letters. Because s only contains lowercase letters of the English alphabet, there will be no more than 26 iterations.
At each iteration, we iterate over s to find i and j, which costs O(n). Next, we iterate between i and j, which could cost O(n) in the worst-case scenario.
Overall, each iteration costs O(n). This gives us a time complexity of O(26n)=O(n)
Space complexity: O(1)
letters and between cannot grow beyond a size of 26, since s only contains letters of the English alphabet.

Optimized approach:
We can slightly optimize the previous approach by pre-computing the indices i and j for each letter.
In the first approach, it costs O(n) to calculate i and j. While this does not affect the time complexity, it does add a large constant factor to our runtime. In this approach, we will spend O(n) once, then be able to retrieve i and j for every letter in O(1).
Let first be an array of length 26, where first[c] represents the first index the character c appears in s. Similarly, let last be an array of length 26, where last[c] represents the last index the character c appears in s. Because we need integer indices, we will map each character to its position in the alphabet.
Time complexity: O(n)
First, we calculate first and last by iterating over s, which costs O(n).
Next, we iterate over 26 alphabet positions. At each iteration, we iterate j over some indices, which in the worst-case scenario would cost O(n). Overall, each of the 26 iterations cost O(n), giving us a time complexity of O(26n)=O(n).
Space complexity: O(1)
first, last, and between all use constant space since s only contains letters in the English alphabet.
 */