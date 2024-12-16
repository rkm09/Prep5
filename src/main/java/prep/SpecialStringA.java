package prep;

public class SpecialStringA {
    public static void main(String[] args) {
        char c = 'A';
        int val = c + 1;
       // System.out.println((char)val);
        String s = "abccde";
        System.out.println(getSpecialString(s));
    }
    private static String getSpecialString(String s) {
        int n = s.length();
        boolean special = false;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 1 ; i < n ; i++) {
            char prev = sb.charAt(i - 1);
            char curr = sb.charAt(i);
            char next;
            if(!special && prev == curr) {
                next = (char)(((curr - 'a') + 1) % 26 + 'a');
                special = true;
                sb.setCharAt(i, next);
                sb.setCharAt(++i, 'a');
            } else if(special){
                next = (char) (((prev - 'a') + 1) % 26 + 'a');
                sb.setCharAt(i, next);
            }
        }
        return sb.toString();
    }
}

/*
Given a string s of length n, generate a special string of length n that is lexicographically greater than s;
If multiple such strings are there, return the smallest one among them; return -1 if no such string is possible
String s = "abccde"
Output: "abcdab"
String s = "abbd"
Output: "abca"
 */