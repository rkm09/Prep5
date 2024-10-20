package dailychallenge.hard;

import java.util.ArrayDeque;
import java.util.Deque;

public class ParseBoolean1106 {
    public static void main(String[] args) {
        String expression = "&(|(f))";
        System.out.println(parseBoolExpr(expression));
    }

//    stack; time: O(n), space: O(n)
    public static boolean parseBoolExpr(String expression) {
        Deque<Character> stack = new ArrayDeque<>();
//        traverse through the expression
        for(char currentChar : expression.toCharArray()) {
            if(currentChar == '(' || currentChar == ',')
                continue; // skip open parenthesis or comma
//            push operators or boolean values to the stack
            if(currentChar == 't' || currentChar == 'f'
            || currentChar == '!' || currentChar == '&' || currentChar == '|')
                stack.push(currentChar);
//            handle closing parenthesis and evaluate the sub expression
            else {
                boolean hasTrue = false, hasFalse = false;
//                process the values inside the parenthesis
                while(!stack.isEmpty() && stack.peek() != '!' && stack.peek() != '&' && stack.peek() != '|') {
                    char topValue = stack.pop();
                    if(topValue == 't')
                        hasTrue = true;
                    else if(topValue == 'f')
                        hasFalse = true;
                }
//                pop the operator and evaluate the sub expression
                char op = stack.pop();
                if(op == '!')
                    stack.push(hasTrue ? 'f' : 't');
                else if(op == '&')
                    stack.push(hasFalse ? 'f' : 't');
                else
                    stack.push(hasTrue ? 't' : 'f');
            }
        }
//        final result is at the top of the stack
        return stack.peek() == 't';
    }
}

/*
A boolean expression is an expression that evaluates to either true or false. It can be in one of the following shapes:
't' that evaluates to true.
'f' that evaluates to false.
'!(subExpr)' that evaluates to the logical NOT of the inner expression subExpr.
'&(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical AND of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
'|(subExpr1, subExpr2, ..., subExprn)' that evaluates to the logical OR of the inner expressions subExpr1, subExpr2, ..., subExprn where n >= 1.
Given a string expression that represents a boolean expression, return the evaluation of that expression.
It is guaranteed that the given expression is valid and follows the given rules.

Example 1:
Input: expression = "&(|(f))"
Output: false
Explanation:
First, evaluate |(f) --> f. The expression is now "&(f)".
Then, evaluate &(f) --> f. The expression is now "f".
Finally, return false.
Example 2:
Input: expression = "|(f,f,f,t)"
Output: true
Explanation: The evaluation of (false OR false OR false OR true) is true.
Example 3:
Input: expression = "!(&(f,t))"
Output: true
Explanation:
First, evaluate &(f,t) --> (false AND true) --> false --> f. The expression is now "!(f)".
Then, evaluate !(f) --> NOT false --> true. We return true.

Constraints:
1 <= expression.length <= 2 * 104
expression[i] is one following characters: '(', ')', '&', '|', '!', 't', 'f', and ','.

 */