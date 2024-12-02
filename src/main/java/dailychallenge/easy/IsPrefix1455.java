package dailychallenge.easy;

public class IsPrefix1455 {
    public static void main(String[] args) {
        String sentence = "hellohello hellohellohello", searchWord = "ell";
        System.out.println(isPrefixOfWord(sentence, searchWord));
    }

//    def; time: O(n + w.m), space: O(n)
//    n be the size of the input string sentence, m be the size of the input string searchWord, k be the average length of words in sentence, and w be the total number of words in sentence such that w⋅k=n.
    public static int isPrefixOfWord(String sentence, String searchWord) {
        String[] arr = sentence.split(" ");
        for(int i = 0 ; i < arr.length; i++) {
            if(arr[i].startsWith(searchWord))
                return i + 1;
        }
        return -1;
    }
}

/*
Given a sentence that consists of some words separated by a single space, and a searchWord, check if searchWord is a prefix of any word in sentence.
Return the index of the word in sentence (1-indexed) where searchWord is a prefix of this word. If searchWord is a prefix of more than one word, return the index of the first word (minimum index). If there is no such word return -1.
A prefix of a string s is any leading contiguous substring of s.
Example 1:
Input: sentence = "i love eating burger", searchWord = "burg"
Output: 4
Explanation: "burg" is prefix of "burger" which is the 4th word in the sentence.
Example 2:
Input: sentence = "this problem is an easy problem", searchWord = "pro"
Output: 2
Explanation: "pro" is prefix of "problem" which is the 2nd and the 6th word in the sentence, but we return 2 as it's the minimal index.
Example 3:
Input: sentence = "i am tired", searchWord = "you"
Output: -1
Explanation: "you" is not a prefix of any word in the sentence.

Constraints:
1 <= sentence.length <= 100
1 <= searchWord.length <= 10
sentence consists of lowercase English letters and spaces.
searchWord consists of lowercase English letters.
 */