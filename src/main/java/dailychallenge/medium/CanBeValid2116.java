package dailychallenge.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class CanBeValid2116 {
    public static void main(String[] args) {
        String s = "))()))";
        String locked = "010100";
        String s1 = "()))(()(()()()()(((())())((()((())";
        String locked1 = "1100000000000010000100001000001101";
        //System.out.println(canBeValid1(s1, locked1)); // true
        System.out.println(canBeValid(s, locked));
    }

//    stack; time: O(n), space: O(n)
    public static boolean canBeValid(String s, String locked) {
        int n = s.length();
//        if length of string is odd, return false
        if(n % 2 != 0) return false;
        Deque<Integer> unlocked = new ArrayDeque<>();
        Deque<Integer> openBrackets = new ArrayDeque<>();
//        iterate through the string to handle '(' and ')'
//        note order is important, unlocked '0' is more powerful, so you check on that first for pushing, and last for popping
        for(int i = 0 ; i < n ; i++) {
            if(locked.charAt(i) == '0')
                unlocked.push(i);
            else if(s.charAt(i) == '(')
                openBrackets.push(i);
            else {
                if(s.charAt(i) == ')') {
                    if(!openBrackets.isEmpty())
                        openBrackets.pop();
                    else if(!unlocked.isEmpty())
                        unlocked.pop();
                    else
                        return false;
                }
            }
        }
//        match remaining open brackets with unlocked characters
        while(!openBrackets.isEmpty() && !unlocked.isEmpty() && openBrackets.peek() < unlocked.peek()) {
            openBrackets.pop();
            unlocked.pop();
        }
        return openBrackets.isEmpty();
    }

//    constant space; time: O(n), space: O(1)
    public static boolean canBeValid1(String s, String locked) {
        int openBrackets = 0, unlocked = 0, n = s.length();
        if(n % 2 != 0) return false;
        for(int i = 0 ; i < n ; i++)
        {
            if(locked.charAt(i) == '0')
                unlocked++;
            else if(s.charAt(i) == '(')
                openBrackets++;
            else {
                if(s.charAt(i) == ')') {
                    if(openBrackets > 0)
                        openBrackets--;
                    else if(unlocked > 0)
                        unlocked--;
                    else
                        return false;
                }
            }
        }
        int balance = 0;
        for(int i = n - 1 ; i >= 0 ; i--) {
            if(locked.charAt(i) == '0') {
                balance--;
                unlocked--;
            } else if(s.charAt(i) == '(') {
                balance++;
                openBrackets--;
            } else if(s.charAt(i) == ')') {
                balance--;
            }
            if(balance > 0) return false;
            if(unlocked == 0 && openBrackets == 0) break;
        }
        return openBrackets <= 0;
    }
}

/*
A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given a parentheses string s and a string locked, both of length n. locked is a binary string consisting only of '0's and '1's. For each index i of locked,
If locked[i] is '1', you cannot change s[i].
But if locked[i] is '0', you can change s[i] to either '(' or ')'.
Return true if you can make s a valid parentheses string. Otherwise, return false.

Example 1:
Input: s = "))()))", locked = "010100"
Output: true
Explanation: locked[1] == '1' and locked[3] == '1', so we cannot change s[1] or s[3].
We change s[0] and s[4] to '(' while leaving s[2] and s[5] unchanged to make s valid.
Example 2:
Input: s = "()()", locked = "0000"
Output: true
Explanation: We do not need to make any changes because s is already valid.
Example 3:
Input: s = ")", locked = "0"
Output: false
Explanation: locked permits us to change s[0].
Changing s[0] to either '(' or ')' will not make s valid.

Constraints:
n == s.length == locked.length
1 <= n <= 105
s[i] is either '(' or ')'.
locked[i] is either '0' or '1'.
 */

/*
constant space:
The stack indices are required when matching the remaining opening brackets with the unlocked characters.
To address this, we could explore a trick to match the brackets using only the counts of the unpaired opening brackets and unlocked characters.
Since we want to balance the remaining opening brackets, note that the unlocked characters towards the end of the string can be converted into closing brackets to pair them up. This allows us to iterate from the end of the string s while maintaining a balance variable to check whether the parentheses are balanced.
We use the integer counters openBrackets and unlocked from the previous steps:
If we encounter an unlocked character, we can treat it as a closing bracket.
If the balance variable indicates that the string is unbalanced at any point, we return false.
Finally, if all the openBrackets are balanced by the end of the iteration, we can return true. Otherwise, we return false.
 */