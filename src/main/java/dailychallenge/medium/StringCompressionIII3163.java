package dailychallenge.medium;

public class StringCompressionIII3163 {
    public static void main(String[] args) {
        String word = "abcde";
        System.out.println(compressedString(word));
    }

//    def;
    public static String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int cnt = 1, n = word.length();
        if(n == 1) return n + word;
        char prev, curr;
        for(int i = 1 ; i < n ; i++) {
            prev = word.charAt(i - 1);
            curr = word.charAt(i);
            if(prev != curr) {
                sb.append(cnt);
                sb.append(prev);
                cnt = 1;
            } else {
                cnt++;
                if(cnt == 10) {
                    sb.append(cnt - 1);
                    sb.append(prev);
                    cnt = 1;
                }
            }
            if(i == n - 1) {
                sb.append(cnt);
                sb.append(curr);
            }
        }
        return sb.toString();
    }
}

/*
Given a string word, compress it using the following algorithm:
Begin with an empty string comp. While word is not empty, use the following operation:
Remove a maximum length prefix of word made of a single character c repeating at most 9 times.
Append the length of the prefix followed by c to comp.
Return the string comp.

Example 1:
Input: word = "abcde"
Output: "1a1b1c1d1e"
Explanation:
Initially, comp = "". Apply the operation 5 times, choosing "a", "b", "c", "d", and "e" as the prefix in each operation.
For each prefix, append "1" followed by the character to comp.
Example 2:
Input: word = "aaaaaaaaaaaaaabb"
Output: "9a5a2b"
Explanation:
Initially, comp = "". Apply the operation 3 times, choosing "aaaaaaaaa", "aaaaa", and "bb" as the prefix in each operation.
For prefix "aaaaaaaaa", append "9" followed by "a" to comp.
For prefix "aaaaa", append "5" followed by "a" to comp.
For prefix "bb", append "2" followed by "b" to comp.

Constraints:
1 <= word.length <= 2 * 105
word consists only of lowercase English letters.
 */