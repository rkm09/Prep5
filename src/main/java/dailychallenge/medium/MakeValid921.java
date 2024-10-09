package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MakeValid921 {
    public static void main(String[] args) {
        String s = "())";
        System.out.println(minAddToMakeValid(s));
    }

//    counter; time: O(n), space: O(1)
    public static int minAddToMakeValid(String s) {
        int open = 0, close = 0;
        for(char c : s.toCharArray()) {
            if (c == '(')
                open++;
            else {
                if(open > 0)
                    open--;
                else
                    close++;
            }
        }
        return open + close;
    }

//    stack; def; time: O(n), space: O(n)
    public static int minAddToMakeValid1(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        int unbalanced = 0;
        for(char c : s.toCharArray()) {
            if(c == '(')
                stack.push(c);
            else {
                if(!stack.isEmpty())
                    stack.pop();
                else
                    unbalanced++;
            }
        }
        return stack.size() + unbalanced;
    }
}

/*
A parentheses string is valid if and only if:
It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.
For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Example 1:
Input: s = "())"
Output: 1
Example 2:
Input: s = "((("
Output: 3

Constraints:
1 <= s.length <= 1000
s[i] is either '(' or ')'.
 */