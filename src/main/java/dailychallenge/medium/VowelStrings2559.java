package dailychallenge.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VowelStrings2559 {
    public static void main(String[] args) {
        int[][] queries = {{0,2},{1,4},{1,1}};
        String[] words = {"aba","bcb","ece","aa","e"};
        System.out.println(Arrays.toString(vowelStrings(words,queries)));
    }

//    prefix sum; time: O(m + n), space: O(m)  [m - size of words, n - length of queries]
    public static int[] vowelStrings(String[] words, int[][] queries) {
        int n = queries.length, idx = 0;
        int[] ans = new int[n];
        Set<Character> vowels = new HashSet<>(List.of('a','e','i','o','u'));
        int[] prefixSum = new int[words.length];
        int sum = 0;
        for(int i = 0 ; i < words.length ; i++) {
            String currentWord = words[i];
            if(vowels.contains(currentWord.charAt(0)) && vowels.contains(currentWord.charAt(currentWord.length() - 1)))
                sum++;
            prefixSum[i] = sum;
        }
        for(int[] query : queries) {
            int l = query[0], r = query[1];
            ans[idx++] = prefixSum[r] - (l == 0 ? 0 : prefixSum[l - 1]);
        }
        return ans;
    }

//    TLE! (def)
    public static int[] vowelStringsX(String[] words, int[][] queries) {
        Set<Character> vowelSet = new HashSet<>(List.of('a','e','i','o','u'));
        Set<Integer> indices = new HashSet<>();
        for(int i = 0 ; i < words.length ; i++) {
            int len = words[i].length() - 1;
            if(vowelSet.contains(words[i].charAt(0)) && vowelSet.contains(words[i].charAt(len)))
                indices.add(i);
        }
        int idx = 0;
        int[] res = new int[queries.length];
        for(int[] query : queries) {
            int l = query[0], r = query[1];
            int count = 0;
            while(l <= r) {
                if(indices.contains(l++)) count++;
            }
            res[idx++] = count;
        }
        return res;
    }
}

/*
You are given a 0-indexed array of strings words and a 2D array of integers queries.
Each query queries[i] = [li, ri] asks us to find the number of strings present in the range li to ri (both inclusive) of words that start and end with a vowel.
Return an array ans of size queries.length, where ans[i] is the answer to the ith query.
Note that the vowel letters are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: words = ["aba","bcb","ece","aa","e"], queries = [[0,2],[1,4],[1,1]]
Output: [2,3,0]
Explanation: The strings starting and ending with a vowel are "aba", "ece", "aa" and "e".
The answer to the query [0,2] is 2 (strings "aba" and "ece").
to query [1,4] is 3 (strings "ece", "aa", "e").
to query [1,1] is 0.
We return [2,3,0].
Example 2:
Input: words = ["a","e","i"], queries = [[0,2],[0,1],[2,2]]
Output: [3,2,1]
Explanation: Every string satisfies the conditions, so we return [3,2,1].

Constraints:
1 <= words.length <= 105
1 <= words[i].length <= 40
words[i] consists only of lowercase English letters.
sum(words[i].length) <= 3 * 105
1 <= queries.length <= 105
0 <= li <= ri < words.length
 */

/*
A brute force approach to calculate the answer for each query [l, r] would involve iterating through the subarray words[l:r] and counting how many vowel strings we find. We can use a set to containing all vowels (a, e, i, o, u) to quickly check if a string is a vowel string in constant time, O(1).
However, this approach is slow as it requires us to iterate through a portion of words for every query. If many queries contain a long range, this will be an expensive operation. Furthermore, a lot of work is repeated since many elements will be visited many times across queries.
For a more optimized approach, we can first perform some precomputations on words. Specifically, we can create a prefix sum array prefixSum to store the cumulative counts of vowel strings in words. prefixSum[i] would contain the total number of vowel strings from the first element of the array up to index i (the prefix array words[0:i]). Populating this prefixSum array would only take one linear scan across words as we maintain a cumulative sum while iterating through words.
Having this prefixSum array will allow us to answer each query very quickly. The key insight here is that the number of vowel strings that fall between a query range [l, r] can be found by subtracting the cumulative sum up to index l-1 from the cumulative sum up to index r: prefixSum[r] - prefixSum[l - 1].
Why subtract prefixSum[l - 1]?
Note that we look at the lower boundary l - 1 instead of l because the range is inclusive. The prefix sum array represents the cumulative count of vowel strings up to each index. By subtracting prefixSum[l - 1], we ignore all the vowel strings that have appeared before index l in our count and include only those within the range [l, r].
 */