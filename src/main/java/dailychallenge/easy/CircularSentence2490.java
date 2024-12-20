package dailychallenge.easy;

public class CircularSentence2490 {
    public static void main(String[] args) {
        String sentence = "leetcode exercises sound delightful";
        System.out.println(isCircularSentence(sentence));
    }

//     space optimized; time: O(n), space: O(n)
    public static boolean isCircularSentence(String sentence) {
        int n = sentence.length();
        for(int i = 0 ; i < n ; i++) {
            if(sentence.charAt(i) == ' ' &&
                    sentence.charAt(i - 1) != sentence.charAt(i + 1))
                return false;
        }
        return sentence.charAt(0) == sentence.charAt(n - 1);
    }

//    string split; time: O(n), space: O(n)
    public static boolean isCircularSentence1(String sentence) {
        String[] words = sentence.split(" ");
        int n = words.length;
        char prev = words[n - 1].charAt(words[n - 1].length() - 1);
        for (String word : words) {
            char curr = word.charAt(0);
            if (prev != curr) return false;
            prev = word.charAt(word.length() - 1);
        }
        return true;
    }


//    def; split; time: O(n), space: O(n)
    public static boolean isCircularSentence2(String sentence) {
        String[] words = sentence.split(" ");
        int n = words.length;
        if(n == 1) return words[0].charAt(0) == words[0].charAt(words[0].length() - 1);
        for(int i = 1 ; i < n ; i++) {
            String word1 = words[i - 1];
            String word2 = words[i];
            if(i == 1) {
                String last = words[n - 1];
                if(word1.charAt(0) != last.charAt(last.length() - 1))
                    return false;
            }
            if(word1.charAt(word1.length() - 1) != word2.charAt(0))
                return false;
        }
        return true;
    }
}

/*
A sentence is a list of words that are separated by a single space with no leading or trailing spaces.
For example, "Hello World", "HELLO", "hello world hello world" are all sentences.
Words consist of only uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.
A sentence is circular if:
The last character of a word is equal to the first character of the next word.
The last character of the last word is equal to the first character of the first word.
For example, "leetcode exercises sound delightful", "eetcode", "leetcode eats soul" are all circular sentences. However, "Leetcode is cool", "happy Leetcode", "Leetcode" and "I like Leetcode" are not circular sentences.
Given a string sentence, return true if it is circular. Otherwise, return false.

Example 1:
Input: sentence = "leetcode exercises sound delightful"
Output: true
Explanation: The words in sentence are ["leetcode", "exercises", "sound", "delightful"].
- leetcode's last character is equal to exercises's first character.
- exercises's last character is equal to sound's first character.
- sound's last character is equal to delightful's first character.
- delightful's last character is equal to leetcode's first character.
The sentence is circular.
Example 2:
Input: sentence = "eetcode"
Output: true
Explanation: The words in sentence are ["eetcode"].
- eetcode's last character is equal to eetcode's first character.
The sentence is circular.
Example 3:
Input: sentence = "Leetcode is cool"
Output: false
Explanation: The words in sentence are ["Leetcode", "is", "cool"].
- Leetcode's last character is not equal to is's first character.
The sentence is not circular.


Constraints:
1 <= sentence.length <= 500
sentence consist of only lowercase and uppercase English letters and spaces.
The words in sentence are separated by a single space.
There are no leading or trailing spaces.
 */